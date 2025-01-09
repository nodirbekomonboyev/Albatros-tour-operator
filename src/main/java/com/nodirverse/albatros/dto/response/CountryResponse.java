package com.nodirverse.albatros.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryResponse {
    private UUID id;
    private String name;
    private String city;
    private String imageUrl;
    private Boolean isSeasonal;
}
