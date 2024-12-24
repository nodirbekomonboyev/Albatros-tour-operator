package com.nodirverse.albatros.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "question")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Question extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String question;
    @Column(nullable = false)
    private String phoneNumber;
}
