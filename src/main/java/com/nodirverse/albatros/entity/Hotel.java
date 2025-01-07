package com.nodirverse.albatros.entity;

import com.nodirverse.albatros.entity.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "hotel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hotel extends BaseEntity {
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    private Category category;
    private String image;
}

