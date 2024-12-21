package com.nodirverse.albatros.controller;

import com.nodirverse.albatros.entity.TourPackage;
import com.nodirverse.albatros.entity.enums.Country;
import com.nodirverse.albatros.entity.enums.DepartureCity;
import com.nodirverse.albatros.entity.enums.Nutrition;
import com.nodirverse.albatros.entity.enums.Transport;
import com.nodirverse.albatros.entity.request.TourPackageRequest;
import com.nodirverse.albatros.entity.response.TourPackageResponse;
import com.nodirverse.albatros.service.TourPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/v1/tour")
@RequiredArgsConstructor
public class TourController {

    private final TourPackageService tourPackageService;

    @PostMapping("/create-tour-package")
    public ResponseEntity<TourPackageResponse> createDiscount(@RequestBody TourPackageRequest request){
        return ResponseEntity.ok(tourPackageService.create(request));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TourPackageResponse>> getTourPackages() {
        return ResponseEntity.ok(tourPackageService.getAll());
    }

    @PostMapping("/update-by-id")
    public ResponseEntity<String> updateTourPackage(
            @RequestParam(value = "id") UUID id,
            @RequestParam(value = "ticketDate", required = false) LocalDate ticketDate,
            @RequestParam(value = "departureCity",required = false) DepartureCity departureCity,
            @RequestParam(value = "country",required = false) Country country,
            @RequestParam(value = "nights",required = false) Integer nights,
            @RequestParam(value = "hotel",required = false) String hotel,
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


}
