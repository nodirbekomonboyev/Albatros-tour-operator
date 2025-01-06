package com.nodirverse.albatros.entity;

import com.nodirverse.albatros.entity.enums.Category;
import jakarta.persistence.Entity;
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
    private Category category;
    private String image;
}

