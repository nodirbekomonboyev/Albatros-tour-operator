package com.nodirverse.albatros.service;

import com.nodirverse.albatros.dto.response.CountryResponse;
import com.nodirverse.albatros.dto.response.HotelResponse;
import com.nodirverse.albatros.entity.Country;
import com.nodirverse.albatros.entity.Hotel;
import com.nodirverse.albatros.entity.TourPackage;
import com.nodirverse.albatros.entity.enums.DepartureCity;
import com.nodirverse.albatros.entity.enums.Nutrition;
import com.nodirverse.albatros.entity.enums.Transport;
import com.nodirverse.albatros.dto.request.TourPackageRequest;
import com.nodirverse.albatros.dto.response.TourPackageResponse;
import com.nodirverse.albatros.exception.DataNotFoundException;
import com.nodirverse.albatros.repository.CountryRepository;
import com.nodirverse.albatros.repository.HotelRepository;
import com.nodirverse.albatros.repository.TourPackageRepository;
import com.nodirverse.albatros.specification.TourPackageSpecification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TourPackageService {

    private final ModelMapper modelMapper;
    private final TourPackageRepository tourPackageRepository;
    private final HotelRepository hotelRepository;
    private final CountryRepository countryRepository;


    public String create(TourPackageRequest request) {
        TourPackage tourPackage = modelMapper.map(request, TourPackage.class);
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(
                () -> new DataNotFoundException("Hotel not found")
        );
        tourPackage.setHotel(hotel);
        Country country = countryRepository.findById(request.getCountryId()).orElseThrow(
                () -> new DataNotFoundException("Country not found")
        );
        tourPackage.setCountry(country);
        tourPackageRepository.save(tourPackage);
        return "Tour package created!";
    }

    public List<TourPackageResponse> getAll() {
        List<TourPackage> tourPackages = tourPackageRepository.findAll();
        List<TourPackageResponse> responses = new ArrayList<>();
        tourPackages.forEach(
                (tour) -> {
                    TourPackageResponse response = modelMapper.map(tour, TourPackageResponse.class);
                    response.setCountry(tour.getCountry().getName());
                    response.setHotelName(tour.getHotel().getName());
                    responses.add(response);

                });
        return responses;
    }


    public String update(UUID id, LocalDate ticketDate, DepartureCity departureCity, UUID countryId, Integer nights,
                         UUID hotelId, Integer place, Nutrition nutrition, Integer price, Transport transport
    ) {
        TourPackage tourPackage = tourPackageRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("tour not found!")
        );

        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(
                () -> new DataNotFoundException("hotel not found!")
        );

        Country country = countryRepository.findById(countryId).orElseThrow(
                () -> new DataNotFoundException("country not found!")
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

    public Map<String, Object> getTourPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<TourPackage> tourPage = tourPackageRepository.findTourPackagesByOrderByTicketDate(pageable);
        return getStringObjectMap(tourPage);
    }

    public Map<String, Object> getFilteredTourPage(
            DepartureCity departureCity,
            String country,
            Nutrition nutrition,
            String hotel,
            LocalDate startDate,
            LocalDate endDate,
            Transport transport,
            int pageNumber,
            int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Specification<TourPackage> spec = Specification.where(TourPackageSpecification.hasDepartureCity(departureCity))
                .and(TourPackageSpecification.hasCountry(country))
                .and(TourPackageSpecification.hasNutrition(nutrition))
                .and(TourPackageSpecification.hasHotel(hotel))
                .and(TourPackageSpecification.hasTicketDateBetween(startDate, endDate))
                .and(TourPackageSpecification.hasTransport(transport));

        Page<TourPackage> tourPage = tourPackageRepository.findAll(spec, pageable);

        return getStringObjectMap(tourPage);
    }

    private Map<String, Object> getStringObjectMap(Page<TourPackage> tourPage) {
        List<TourPackageResponse> tourPackageResponses = new ArrayList<>();
        for (TourPackage tourPackage : tourPage.getContent()) {
            TourPackageResponse tourPackageResponse = modelMapper.map(tourPackage, TourPackageResponse.class);
            tourPackageResponse.setHotelName(tourPackage.getHotel().getName());
            tourPackageResponse.setCountry(tourPackage.getCountry().getName());
            tourPackageResponses.add(tourPackageResponse);
        }
        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("pageNumber", tourPage.getNumber() + 1);
        responseMap.put("totalPages", tourPage.getTotalPages());
        responseMap.put("totalCount", tourPage.getTotalElements());
        responseMap.put("pageSize", tourPage.getSize());
        responseMap.put("hasPreviousPage", tourPage.hasPrevious());
        responseMap.put("hasNextPage", tourPage.hasNext());
        responseMap.put("data", tourPackageResponses);
        return responseMap;
    }


    public String delete(UUID tourId) {
        tourPackageRepository.deleteById(tourId);
        return "Tour Package deleted";
    }
}
