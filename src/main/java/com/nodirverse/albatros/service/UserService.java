package com.nodirverse.albatros.service;

import com.nodirverse.albatros.dto.request.*;
import com.nodirverse.albatros.entity.UserEntity;
import com.nodirverse.albatros.entity.VerificationCode;
import com.nodirverse.albatros.dto.response.JwtResponse;
import com.nodirverse.albatros.entity.enums.UserRole;
import com.nodirverse.albatros.entity.enums.UserStatus;
import com.nodirverse.albatros.exception.DataAlreadyExistsException;
import com.nodirverse.albatros.exception.DataNotFoundException;
import com.nodirverse.albatros.exception.WrongPasswordException;
import com.nodirverse.albatros.repository.UserRepository;
import com.nodirverse.albatros.service.jwt.JwtUtil;
import com.nodirverse.albatros.service.jwt.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final VerificationCodeService verificationCodeService;
    private final LoginAttemptService loginAttemptService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final MailMessageService mailMessageService;


    public JwtResponse signIn(AuthRequest request, String ip) {
        UserEntity user = userRepository.findUserEntityByEmail(request.getEmail())
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            List<String> tokens = jwtUtil.generateToken(user);
            return new JwtResponse(tokens.get(0),tokens.get(1));
        }
        loginAttemptService.loginFailed(ip);
        throw new WrongPasswordException("password didn't match");
    }

    public JwtResponse tokenRefresh(TokenRefreshRequest request) {
        String id = jwtUtil.extractToken(request.getRefreshToken()).getBody().getSubject();
        UserEntity user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DataNotFoundException("user not found!!!"));
        if(jwtUtil.checkRefreshToken(user, request.getRefreshToken())){
            List<String> tokens = jwtUtil.generateToken(user);
            return new JwtResponse(tokens.get(0), tokens.get(1));
        }
        throw new BadCredentialsException("refresh token didn't match");
    }

    public String signUp(SignUpRequest request) {
        Optional<UserEntity> userEntity = userRepository.findUserEntityByEmail(request.getEmail());
        UserEntity user;
        if (userEntity.isPresent()) {
            if (userEntity.get().getStatus() == UserStatus.UNBLOCK){
                throw new DataAlreadyExistsException("User already exists");
            }
            user = userEntity.get();

        } else {
            user = modelMapper.map(request, UserEntity.class);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.BLOCK);
        userRepository.save(user);
        MailMessageRequest mailMessageRequest = new MailMessageRequest();
        Random random = new Random();
        int code = random.nextInt(100000, 999999);
        mailMessageRequest.setReceiver(request.getEmail());
        mailMessageRequest.setSubject("Verification");
        mailMessageRequest.setMessage("Verification code: " + code);
        mailMessageService.sendMessage(mailMessageRequest);
        VerificationCode verificationCode = new VerificationCode(
                request.getEmail(),
                String.valueOf(code),
                LocalDateTime.now().plusMinutes(2)
        );
        Optional<VerificationCode> oldVerificationCode = verificationCodeService.get(request.getEmail());
        oldVerificationCode.ifPresent(verificationCodeService::delete);
        verificationCodeService.save(verificationCode);
        return "verification code sent";
    }

    public JwtResponse verification(VerificationRequest request) {
        VerificationCode verificationCode = verificationCodeService.get(request.getEmail()).orElseThrow(
                () -> new DataNotFoundException("verification code not found!")
        );
        if(!Objects.equals(verificationCode.getCode(), request.getCode())){
            throw new WrongPasswordException("verification code didn't match!");
        }
        if(verificationCode.getExpire().isBefore(LocalDateTime.now())){
            throw new WrongPasswordException("verification code is expired!");
        }
        UserEntity user = userRepository.findUserEntityByEmail(request.getEmail()).orElseThrow(
                () -> new DataNotFoundException("user not found!")
        );
        user.setStatus(UserStatus.UNBLOCK);
        userRepository.save(user);
        List<String> tokens = jwtUtil.generateToken(user);
        return new JwtResponse(tokens.get(0), tokens.get(1));
    }
}