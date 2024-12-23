package com.nodirverse.albatros.entity;

import jakarta.persistence.Entity;
import lombok.*;
import java.time.LocalDateTime;

@Entity(name = "verification_code")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VerificationCode extends BaseEntity {
    private String email;
    private String code;
    private LocalDateTime expire;
}
