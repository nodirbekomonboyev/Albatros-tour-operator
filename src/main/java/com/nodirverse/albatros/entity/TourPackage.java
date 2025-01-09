package com.nodirverse.albatros.entity;

import com.nodirverse.albatros.entity.enums.DepartureCity;
import com.nodirverse.albatros.entity.enums.Nutrition;
import com.nodirverse.albatros.entity.enums.Transport;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;



@Entity(name = "tour_package")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TourPackage extends BaseEntity{

    @Column(nullable = false)
    private LocalDate ticketDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DepartureCity departureCity;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    private Integer nights;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    private Integer place;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Nutrition nutrition;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Transport transport;
}
