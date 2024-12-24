package com.nodirverse.albatros.service;

import com.nodirverse.albatros.entity.VerificationCode;
import com.nodirverse.albatros.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerificationCodeService {
    private final VerificationCodeRepository verificationCodeRepository;
    public void save(VerificationCode verificationCode){
        verificationCodeRepository.save(verificationCode);
    }

    public Optional<VerificationCode> get(String email){
        return verificationCodeRepository.findByEmail(email);
    }

    public void delete(VerificationCode verificationCode){
        verificationCodeRepository.delete(verificationCode);
    }

    public void deleteByEmail(String email){
        verificationCodeRepository.deleteByEmail(email);
    }
}
