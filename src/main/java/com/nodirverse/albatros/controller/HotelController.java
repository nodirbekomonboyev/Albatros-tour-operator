package com.nodirverse.albatros.controller;

import com.nodirverse.albatros.config.FileStorageProperties;
import com.nodirverse.albatros.dto.request.HotelCreateRequest;
import com.nodirverse.albatros.dto.response.HotelResponse;
import com.nodirverse.albatros.entity.Country;
import com.nodirverse.albatros.entity.Hotel;
import com.nodirverse.albatros.entity.enums.Category;
import com.nodirverse.albatros.service.FileStorageService;
import com.nodirverse.albatros.service.HotelService;
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
@RequestMapping("/api/v1/hotel")
@RequiredArgsConstructor
public class HotelController{
    private final HotelService hotelService;
    private final FileStorageService fileStorageService;

    @PostMapping("create")
    public ResponseEntity<String> createHotel(@RequestBody HotelCreateRequest request){
        return ResponseEntity.ok(hotelService.create(request));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<HotelResponse>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getAll());
    }


    @PutMapping("update")
    public ResponseEntity<String> updateHotel(
            @RequestParam(value = "id") UUID id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "country", required = false) Country country,
            @RequestParam(value = "category", required = false) Category category,
            @RequestParam(value = "images", required = false) String image
    ){
        return ResponseEntity.ok(hotelService.update(id, name, country, category, image));
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteHotel(@RequestParam(value = "id") UUID id){
        return ResponseEntity.ok(hotelService.delete(id));
    }

    @PostMapping(value = "/{id}/upload-image",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<String> uploadImage(
            @PathVariable UUID id,
            @RequestParam(value = "images") MultipartFile file
    ) {
        String imageUrl = fileStorageService.storeFile(file);
        hotelService.updateImageUrl(id, imageUrl);

        return ResponseEntity.ok(imageUrl);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getImage(@PathVariable UUID id) {
        Hotel hotel = hotelService.getHotelById(id);
        String imageUrl = hotel.getImage();

        String uploadDir = new FileStorageProperties().getUploadDir();

        Path imagePath = Paths.get(uploadDir)
                .toAbsolutePath().normalize().resolve(imageUrl.replace("/images/", ""));
        if (!Files.exists(imagePath)) {
            throw new RuntimeException("Fayl topilmadi: " + imageUrl);
        }

        try {
            Resource resource = new UrlResource(imagePath.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Tasvirni yuklashda xatolik yuz berdi: " + imageUrl, e);
        }
    }

}
