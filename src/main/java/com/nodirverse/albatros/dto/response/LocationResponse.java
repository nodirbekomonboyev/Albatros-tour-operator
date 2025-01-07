package com.nodirverse.albatros.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationResponse {
    private UUID id;
    private double latitude;
    private double longitude;
}
