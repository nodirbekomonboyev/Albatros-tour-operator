package com.nodirverse.albatros.dto.request;

import com.nodirverse.albatros.entity.enums.DepartureCity;
import com.nodirverse.albatros.entity.enums.Nutrition;
import com.nodirverse.albatros.entity.enums.Transport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourPackageRequest {
    private LocalDate ticketDate;
    private DepartureCity departureCity;
    private String country;
    private Integer nights;
    private String hotel;
    private Integer place;
    private Nutrition nutrition;
    private Integer price;
    private Transport transport;
}
