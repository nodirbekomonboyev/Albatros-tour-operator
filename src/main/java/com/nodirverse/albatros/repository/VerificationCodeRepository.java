package com.nodirverse.albatros.repository;

import com.nodirverse.albatros.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface VerificationCodeRepository extends JpaRepository<VerificationCode, UUID> {
    Optional<VerificationCode> findByEmail(String email);

    void deleteByEmail(String email);
}
