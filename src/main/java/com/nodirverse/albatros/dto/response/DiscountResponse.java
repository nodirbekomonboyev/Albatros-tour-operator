package com.nodirverse.albatros.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DiscountResponse {
    private UUID id;
    private TourPackageResponse tourPackageResponse;
    private Integer newPrice;
    private String description;
    private String imageUrl;
}
