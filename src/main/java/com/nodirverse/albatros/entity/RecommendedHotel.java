package com.nodirverse.albatros.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "recommended_hotel")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecommendedHotel extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
