package com.nodirverse.albatros.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountCreateRequest {
    private UUID tourPackageId;
    private Integer newPrice;
    private String description;
}
