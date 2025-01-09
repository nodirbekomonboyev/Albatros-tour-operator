package com.nodirverse.albatros.entity;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "country")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country extends BaseEntity {
    private String name;
    private String city;
    private String imageUrl;
    private Boolean isSeasonal;
}
