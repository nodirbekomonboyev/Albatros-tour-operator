package com.nodirverse.albatros.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @CreationTimestamp
    protected LocalDateTime createdDate;

    @UpdateTimestamp
    protected LocalDateTime updatedDate;

}
