package com.nodirverse.albatros.service;

import com.nodirverse.albatros.dto.request.CountryCreateRequest;
import com.nodirverse.albatros.dto.response.CountryResponse;
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
public class CountryService {
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    public String create(CountryCreateRequest request) {
        countryRepository.save(modelMapper.map(request, Country.class));
        return "Country creation successful";
    }

    public List<CountryResponse> getAll() {
        List<CountryResponse> list = new ArrayList<>();
        countryRepository.findAll().forEach(country -> {
            CountryResponse countryResponse = modelMapper.map(country, CountryResponse.class);
            countryResponse.setId(country.getId());
            list.add(countryResponse);
        });
        return list;
    }


    public String update(UUID id, String name, String city, String picture, Boolean isSeasonal) {
        Country country = countryRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("country not found!")
        );

        if(name != null){
            country.setName(name);
        }
        if(country != null){
            country.setCity(city);
        }
        if(picture != null){
            country.setPicture(picture);
        }
        if(isSeasonal != null){
            country.setIsSeasonal(isSeasonal);
        }

        countryRepository.save(country);
        return "Country updated";
    }

    public String delete(UUID id) {
        countryRepository.deleteById(id);
        return "Country deleted";
    }

    public List<CountryResponse> getAllSeasonal() {
        List<CountryResponse> list = new ArrayList<>();
        countryRepository.findCountriesByIsSeasonalTrue().forEach(country -> {
            CountryResponse countryResponse = modelMapper.map(country, CountryResponse.class);
            countryResponse.setId(country.getId());
            list.add(countryResponse);
        });
        return list;
    }
}
