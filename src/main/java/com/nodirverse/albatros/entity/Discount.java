package com.nodirverse.albatros.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity(name = "discount")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Discount extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "tour_package_id")
    private TourPackage tourPackage;
    private Integer newPrice;
    private String description;
}
