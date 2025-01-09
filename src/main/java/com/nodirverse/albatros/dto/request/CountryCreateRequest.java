package com.nodirverse.albatros.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryCreateRequest {
    private String name;
    private String city;
    private Boolean isSeasonal;
}
