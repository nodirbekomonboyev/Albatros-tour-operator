package com.nodirverse.albatros.controller;


import com.nodirverse.albatros.dto.response.HotelResponse;
import com.nodirverse.albatros.dto.response.LocationResponse;
import com.nodirverse.albatros.entity.enums.Category;
import com.nodirverse.albatros.service.HotelService;
import com.nodirverse.albatros.service.LocationService;
import com.nodirverse.albatros.service.RecommendedHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeController {

    private final HotelService hotelService;
    private final LocationService locationService;
    private final RecommendedHotelService recommendedHotelService;

    @PostMapping("create-recommendation")
    public ResponseEntity<String> createRecommendation(@RequestParam(value = "hotelId") UUID hotelId){
        return ResponseEntity.ok(recommendedHotelService.create(hotelId));
    }

    @PostMapping("create-location")
    public ResponseEntity<String> createLocation(
            @RequestParam(value = "latitude") double latitude,
            @RequestParam(value = "longitude") double longitude
    ){
        return ResponseEntity.ok(locationService.create(latitude, longitude));
    }

    @GetMapping("get-premiums")
    public ResponseEntity<List<HotelResponse>> getAllPremiums(){
        return ResponseEntity.ok(hotelService.getAllByCategory(Category.FIFTH));
    }

    @GetMapping("get-recommendation")
    public ResponseEntity<List<HotelResponse>> getAllRecommendation(){
        return ResponseEntity.ok(recommendedHotelService.getAll());
    }

    @GetMapping("get-location")
    public ResponseEntity<List<LocationResponse>> getAllLocations(){
        return ResponseEntity.ok(locationService.get());
    }

    @PutMapping("update-location")
    public ResponseEntity<String> updateLocation(
            @RequestParam(value = "id") UUID id,
            @RequestParam(value = "latitude") double latitude,
            @RequestParam(value = "longitude") double longitude
    ){
        return ResponseEntity.ok(locationService.update(id, latitude, longitude));
    }

    @DeleteMapping("delete-recommendation")
    public ResponseEntity<String> deleteRecommendation(@RequestParam(value = "hotelId") UUID hotelId){
        return ResponseEntity.ok(recommendedHotelService.delete(hotelId));
    }

    @DeleteMapping("delete-location")
    public ResponseEntity<String> deleteLocation(@RequestParam(value = "id") UUID id){
        return ResponseEntity.ok(locationService.delete(id));
    }
}
