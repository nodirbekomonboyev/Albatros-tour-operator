package com.nodirverse.albatros.controller;

import com.nodirverse.albatros.dto.request.EmployeeCreateRequest;
import com.nodirverse.albatros.dto.request.HotelCreateRequest;
import com.nodirverse.albatros.dto.response.EmployeeResponse;
import com.nodirverse.albatros.dto.response.HotelResponse;
import com.nodirverse.albatros.entity.Country;
import com.nodirverse.albatros.entity.Hotel;
import com.nodirverse.albatros.entity.enums.Category;
import com.nodirverse.albatros.entity.enums.DepartureCity;
import com.nodirverse.albatros.entity.enums.Nutrition;
import com.nodirverse.albatros.entity.enums.Transport;
import com.nodirverse.albatros.dto.request.TourPackageRequest;
import com.nodirverse.albatros.dto.response.TourPackageResponse;
import com.nodirverse.albatros.service.HotelService;
import com.nodirverse.albatros.service.TourPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/v1/tour")
@RequiredArgsConstructor
public class TourController {

    private final TourPackageService tourPackageService;

    @PostMapping("/create-tour-package")
    public ResponseEntity<String> createTourPackage(@RequestBody TourPackageRequest request){
        return ResponseEntity.ok(tourPackageService.create(request));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TourPackageResponse>> getTourPackages() {
        return ResponseEntity.ok(tourPackageService.getAll());
    }

    @GetMapping("/get-page")
    public ResponseEntity<Map<String, Object>> getTourPackages(
            @RequestParam(required = false) DepartureCity departureCity,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Nutrition nutrition,
            @RequestParam(required = false) String hotel,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Transport transport,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize)
    {
        Map<String, Object> response;

        if (departureCity != null || country != null || nutrition != null || hotel != null || startDate != null || endDate != null || transport != null) {
            response = tourPackageService.getFilteredTourPage(departureCity, country, nutrition, hotel, startDate, endDate, transport, pageNumber, pageSize);
        } else {
            response = tourPackageService.getTourPage(pageNumber, pageSize);
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-by-id")
    public ResponseEntity<String> updateTourPackage(
            @RequestParam(value = "id") UUID id,
            @RequestParam(value = "ticketDate", required = false) LocalDate ticketDate,
            @RequestParam(value = "departureCity",required = false) DepartureCity departureCity,
            @RequestParam(value = "countryId",required = false) UUID country,
            @RequestParam(value = "nights",required = false) Integer nights,
            @RequestParam(value = "hotelId",required = false) UUID hotel,
            @RequestParam(value = "place",required = false) Integer place,
            @RequestParam(value = "nutrition",required = false) Nutrition nutrition,
            @RequestParam(value = "price",required = false) Integer price,
            @RequestParam(value = "transport",required = false) Transport transport
    ){
        return ResponseEntity.ok(
                tourPackageService.update(
                        id, ticketDate,departureCity, country, nights, hotel, place, nutrition, price, transport
                ));
    }

    @DeleteMapping("delete-by-id{tourId}")
    public ResponseEntity<String> deleteById(@PathVariable UUID tourId){
        return ResponseEntity.ok(tourPackageService.delete(tourId));
    }
}
