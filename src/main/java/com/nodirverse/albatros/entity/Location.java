package com.nodirverse.albatros.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location extends BaseEntity{
    private double latitude;
    private double longitude;
}
