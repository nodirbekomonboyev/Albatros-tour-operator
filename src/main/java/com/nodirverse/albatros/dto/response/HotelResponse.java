package com.nodirverse.albatros.dto.response;

import com.nodirverse.albatros.entity.Country;
import com.nodirverse.albatros.entity.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelResponse {
    private UUID id;
    private String name;
    private Country country;
    private Category category;
    private String image;
    private Boolean isPremium;
}
