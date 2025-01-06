package com.nodirverse.albatros.dto.request;

import com.nodirverse.albatros.entity.Country;
import com.nodirverse.albatros.entity.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelCreateRequest {
    private String name;
    private UUID countryId;
    private Category category;
}
