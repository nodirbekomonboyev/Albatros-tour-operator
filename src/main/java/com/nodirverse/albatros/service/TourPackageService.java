package com.nodirverse.albatros.service;

import com.nodirverse.albatros.entity.TourPackage;
import com.nodirverse.albatros.entity.enums.Country;
import com.nodirverse.albatros.entity.enums.DepartureCity;
import com.nodirverse.albatros.entity.enums.Nutrition;
import com.nodirverse.albatros.entity.enums.Transport;
import com.nodirverse.albatros.entity.request.TourPackageRequest;
import com.nodirverse.albatros.entity.response.TourPackageResponse;
import com.nodirverse.albatros.exception.DataNotFoundException;
import com.nodirverse.albatros.repository.TourPackageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TourPackageService {

    private final ModelMapper modelMapper;
    private final TourPackageRepository tourPackageRepository;
    public TourPackageResponse create(TourPackageRequest request) {
        TourPackage tourPackage = modelMapper.map(request, TourPackage.class);
        tourPackageRepository.save(tourPackage);
        return modelMapper.map(tourPackage, TourPackageResponse.class);
    }

    public List<TourPackageResponse> getAll() {
        List<TourPackage> list = tourPackageRepository.findAll();
        List<TourPackageResponse> responses = new ArrayList<>();
        list.forEach((tour) -> responses.add(modelMapper.map(tour, TourPackageResponse.class)));
        return responses;
    }


    public String update(UUID id, LocalDate ticketDate, DepartureCity departureCity, Country country, Integer nights,
                         String hotel, Integer place, Nutrition nutrition, Integer price, Transport transport
    ) {
        TourPackage tourPackage = tourPackageRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("tour not found!")
        );
        if(ticketDate != null){
            tourPackage.setTicketDate(ticketDate);
        }
        if(departureCity != null){
            tourPackage.setDepartureCity(departureCity);
        }
        if(country != null){
            tourPackage.setCountry(country);
        }
        if(nights != null){
            tourPackage.setNights(nights);
        }
        if(hotel != null){
            tourPackage.setHotel(hotel);
        }
        if(place != null){
            tourPackage.setPlace(place);
        }
        if(nutrition != null){
            tourPackage.setNutrition(nutrition);
        }
        if(price != null){
            tourPackage.setPrice(price);
        }
        if(transport != null){
            tourPackage.setTransport(transport);
        }
        tourPackageRepository.save(tourPackage);
        return "Tour package successfully updated!";
    }
}
