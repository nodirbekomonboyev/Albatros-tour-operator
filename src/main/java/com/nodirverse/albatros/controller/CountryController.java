package com.nodirverse.albatros.controller;

import com.nodirverse.albatros.config.FileStorageProperties;
import com.nodirverse.albatros.dto.request.CountryCreateRequest;
import com.nodirverse.albatros.dto.response.CountryResponse;
import com.nodirverse.albatros.entity.Country;
import com.nodirverse.albatros.service.CountryService;
import com.nodirverse.albatros.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    private final FileStorageService fileStorageService;
    private final FileStorageProperties fileStorageProperties;

    @PostMapping("create")
    public ResponseEntity<String> createCountry(@RequestBody CountryCreateRequest request){
        return ResponseEntity.ok(countryService.create(request));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<CountryResponse>> getAllCountries(){
        return ResponseEntity.ok(countryService.getAll());
    }


    @PutMapping("update")
    public ResponseEntity<String> updateCountry(
            @RequestParam(value = "id") UUID id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "isSeasonal", required = false) Boolean isSeasonal
    ){
        return ResponseEntity.ok(countryService.update(id, name, city, isSeasonal));
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteCountry(@RequestParam(value = "id") UUID id){
        return ResponseEntity.ok(countryService.delete(id));
    }

    @PostMapping(value = "/{id}/upload-image",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<String> uploadImage(
            @PathVariable UUID id,
            @RequestParam(value = "images") MultipartFile file
    ) {
        String imageUrl = fileStorageService.storeFile(file);
        countryService.updateImageUrl(id, imageUrl);

        return ResponseEntity.ok(imageUrl);
    }
}
