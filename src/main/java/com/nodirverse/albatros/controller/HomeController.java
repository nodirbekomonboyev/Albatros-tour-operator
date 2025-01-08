package com.nodirverse.albatros.controller;


import com.nodirverse.albatros.dto.request.DiscountCreateRequest;
import com.nodirverse.albatros.dto.request.NewsCreateRequest;
import com.nodirverse.albatros.dto.response.DiscountResponse;
import com.nodirverse.albatros.dto.response.HotelResponse;
import com.nodirverse.albatros.dto.response.LocationResponse;
import com.nodirverse.albatros.dto.response.NewsResponse;
import com.nodirverse.albatros.entity.enums.Category;
import com.nodirverse.albatros.service.*;
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
    private final NewsService newsService;
    private final DiscountService discountService;


    @PostMapping("create-news")
    public ResponseEntity<String> createDiscount(@RequestBody DiscountCreateRequest request){
        return ResponseEntity.ok(discountService.create(request));
    }

    @PostMapping("create-news")
    public ResponseEntity<String> createNews(@RequestBody NewsCreateRequest request){
        return ResponseEntity.ok(newsService.create(request));
    }

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

    @GetMapping("get-discounts")
    public ResponseEntity<List<DiscountResponse>> getAllDiscounts(){
        return ResponseEntity.ok(discountService.getAll());
    }

    @GetMapping("get-news")
    public ResponseEntity<List<NewsResponse>> getAllNews(){
        return ResponseEntity.ok(newsService.getAll());
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

    @PutMapping("update-news")
    public ResponseEntity<String> updateNews(
            @RequestParam(value = "id") UUID id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content
    ){
        return ResponseEntity.ok(newsService.update(id, title, text, link, content));
    }

    @PutMapping("update-location")
    public ResponseEntity<String> updateLocation(
            @RequestParam(value = "id") UUID id,
            @RequestParam(value = "latitude") double latitude,
            @RequestParam(value = "longitude") double longitude
    ){
        return ResponseEntity.ok(locationService.update(id, latitude, longitude));
    }

    @DeleteMapping("delete-discount")
    public ResponseEntity<String> deleteDiscount(@RequestParam(value = "id") UUID id){
        return ResponseEntity.ok(discountService.delete(id));
    }

    @DeleteMapping("delete-news")
    public ResponseEntity<String> deleteNews(@RequestParam(value = "id") UUID id){
        return ResponseEntity.ok(newsService.delete(id));
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
