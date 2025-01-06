package com.nodirverse.albatros.controller;

import com.nodirverse.albatros.dto.request.CountryCreateRequest;
import com.nodirverse.albatros.dto.response.CountryResponse;
import com.nodirverse.albatros.dto.response.HotelResponse;
import com.nodirverse.albatros.entity.Country;
import com.nodirverse.albatros.entity.enums.Category;
import com.nodirverse.albatros.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PostMapping("create")
    public ResponseEntity<String> createCountry(@RequestBody CountryCreateRequest request){
        return ResponseEntity.ok(countryService.create(request));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<CountryResponse>> getAllCountries(){
        return ResponseEntity.ok(countryService.getAll());
    }

    @GetMapping("seasonal-countries")
    public ResponseEntity<List<CountryResponse>> SeasonalCountries(){
        return ResponseEntity.ok(countryService.getAllSeasonal());
    }

    @PutMapping("update")
    public ResponseEntity<String> updateCountry(
            @RequestParam(value = "id") UUID id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "picture", required = false) String picture,
            @RequestParam(value = "isSeasonal", required = false) Boolean isSeasonal
    ){
        return ResponseEntity.ok(countryService.update(id, name, city, picture, isSeasonal));
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteCountry(@RequestParam(value = "id") UUID id){
        return ResponseEntity.ok(countryService.delete(id));
    }
}
