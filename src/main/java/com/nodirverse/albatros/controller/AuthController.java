package com.nodirverse.albatros.controller;


import com.nodirverse.albatros.entity.dto.request.AuthRequest;
import com.nodirverse.albatros.entity.dto.request.SignUpRequest;
import com.nodirverse.albatros.entity.dto.request.TokenRefreshRequest;
import com.nodirverse.albatros.entity.dto.request.VerificationRequest;
import com.nodirverse.albatros.entity.dto.response.JwtResponse;
import com.nodirverse.albatros.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(userService.signUp(request));
    }

    @PostMapping("/verification")
    public ResponseEntity<JwtResponse> verification(@Valid @RequestBody VerificationRequest request){
        return ResponseEntity.ok(userService.verification(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(userService.signIn(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest request){
        return ResponseEntity.ok(userService.tokenRefresh(request));
    }
}
