package com.nodirverse.albatros.service;

import com.nodirverse.albatros.dto.request.HotelCreateRequest;
import com.nodirverse.albatros.dto.response.HotelResponse;
import com.nodirverse.albatros.entity.Country;
import com.nodirverse.albatros.entity.Hotel;
import com.nodirverse.albatros.entity.enums.Category;
import com.nodirverse.albatros.exception.DataNotFoundException;
import com.nodirverse.albatros.repository.CountryRepository;
import com.nodirverse.albatros.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    public String create(HotelCreateRequest request) {
        Country byId = countryRepository.findById(request.getCountryId()).orElseThrow(
                () -> new DataNotFoundException("Country not found")
        );
        Hotel hotel = modelMapper.map(request, Hotel.class);
        hotel.setCountry(byId);
        hotelRepository.save(hotel);
        return "Hotel creation successful";
    }

    public List<HotelResponse> getAll() {
        List<HotelResponse> list = new ArrayList<>();
        hotelRepository.findAll().forEach(hotel -> {
            HotelResponse hotelResponse = modelMapper.map(hotel, HotelResponse.class);
            hotelResponse.setId(hotel.getId());
            list.add(hotelResponse);
        });
        return list;
    }

    public List<HotelResponse> getAllByCategory(Category category) {
        List<HotelResponse> list = new ArrayList<>();
        hotelRepository.findAllByCategory(category).forEach(hotel -> {
            HotelResponse hotelResponse = modelMapper.map(hotel, HotelResponse.class);
            hotelResponse.setId(hotel.getId());
            list.add(hotelResponse);
        });
        return list;
    }


    public String update(UUID id, String name, UUID countryId, Category category, String image) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("hotel not found!")
        );


        if(name != null){
            hotel.setName(name);
        }
        if(countryId != null){
            Country byId = countryRepository.findById(countryId).orElseThrow(
                    () -> new DataNotFoundException("Country not found")
            );
            hotel.setCountry(byId);
        }
        if(category != null){
            hotel.setCategory(category);
        }
        if(image != null){
            hotel.setImageUrl(image);
        }

        hotelRepository.save(hotel);
        return "Hotel updated";
    }

    public String delete(UUID id) {
        hotelRepository.deleteById(id);
        return "Hotel deleted";
    }

    public void updateImageUrl(UUID id, String imageUrl) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));

        hotel.setImageUrl(imageUrl);
        hotelRepository.save(hotel);
    }

    public Hotel getHotelById(UUID id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
    }
}
