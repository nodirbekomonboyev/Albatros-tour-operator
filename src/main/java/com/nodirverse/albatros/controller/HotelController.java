package com.nodirverse.albatros.controller;

import com.nodirverse.albatros.dto.request.HotelCreateRequest;
import com.nodirverse.albatros.dto.response.HotelResponse;
import com.nodirverse.albatros.entity.Country;
import com.nodirverse.albatros.entity.enums.Category;
import com.nodirverse.albatros.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hotel")
@RequiredArgsConstructor
public class HotelController{
    private final HotelService hotelService;

    @PostMapping("create")
    public ResponseEntity<String> createHotel(@RequestBody HotelCreateRequest request){
        return ResponseEntity.ok(hotelService.create(request));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<HotelResponse>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getAll());
    }

    @GetMapping("get-premiums")
    public ResponseEntity<List<HotelResponse>> getAllPremiums(){
        return ResponseEntity.ok(hotelService.getAllByCategory(Category.FIFTH));
    }

    @PutMapping("update")
    public ResponseEntity<String> updateHotel(
            @RequestParam(value = "id") UUID id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "country", required = false) Country country,
            @RequestParam(value = "category", required = false) Category category,
            @RequestParam(value = "image", required = false) String image
    ){
        return ResponseEntity.ok(hotelService.update(id, name, country, category, image));
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteHotel(@RequestParam(value = "id") UUID id){
        return ResponseEntity.ok(hotelService.delete(id));
    }
}
