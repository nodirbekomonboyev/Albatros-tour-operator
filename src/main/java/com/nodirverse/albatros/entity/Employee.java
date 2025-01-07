package com.nodirverse.albatros.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee extends  BaseEntity {
    private String name;
    private String surname;
    private String picture;
    private String phoneNumber;
    private String position;
}
