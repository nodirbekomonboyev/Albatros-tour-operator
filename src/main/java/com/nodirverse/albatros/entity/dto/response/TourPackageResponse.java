package com.nodirverse.albatros.entity.dto.response;

import com.nodirverse.albatros.entity.enums.Country;
import com.nodirverse.albatros.entity.enums.DepartureCity;
import com.nodirverse.albatros.entity.enums.Nutrition;
import com.nodirverse.albatros.entity.enums.Transport;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private Country country;
    private Integer nights;
    private String hotel;
    private Integer place;
    private Nutrition nutrition;
    private Integer price;
    private Transport transport;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
