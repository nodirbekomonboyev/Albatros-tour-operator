package com.nodirverse.albatros.dto.response;

import com.nodirverse.albatros.entity.enums.DepartureCity;
import com.nodirverse.albatros.entity.enums.Nutrition;
import com.nodirverse.albatros.entity.enums.Transport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourPackageResponse {
    private UUID id;
    private LocalDate ticketDate;
    private DepartureCity departureCity;
    private String country;
    private Integer nights;
    private String hotel;
    private Integer place;
    private Nutrition nutrition;
    private Integer price;
    private Transport transport;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
