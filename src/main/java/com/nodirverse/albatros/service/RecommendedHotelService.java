package com.nodirverse.albatros.service;

import com.nodirverse.albatros.dto.response.HotelResponse;
import com.nodirverse.albatros.entity.Hotel;
import com.nodirverse.albatros.entity.RecommendedHotel;
import com.nodirverse.albatros.exception.DataAlreadyExistsException;
import com.nodirverse.albatros.exception.DataNotFoundException;
import com.nodirverse.albatros.repository.HotelRepository;
import com.nodirverse.albatros.repository.RecommendedHotelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RecommendedHotelService {
    private final HotelRepository hotelRepository;
    private final RecommendedHotelRepository recommendedHotelRepository;
    private final ModelMapper modelMapper;

    public String create(UUID hotelId){
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(hotel.isPresent()){
            Optional<RecommendedHotel> recommendedHotel = recommendedHotelRepository.findByHotel_Id(hotelId);
            if(recommendedHotel.isPresent()){
                throw new DataAlreadyExistsException("Recommendation already exists");
            }
            recommendedHotelRepository.save(new RecommendedHotel(hotel.get()));
            return "Recommendation has been created";
        }
        throw new DataNotFoundException("Hotel does not exist");
    }

    public List<HotelResponse> getAll(){
        List<HotelResponse> list = new ArrayList<>();
        recommendedHotelRepository.findAll().forEach(
                (recommendedHotel) -> {
                   Hotel hotel = recommendedHotel.getHotel();
                   list.add(modelMapper.map(hotel, HotelResponse.class));
                }
        );
        return list;
    }

    public String delete(UUID hotelId){
        recommendedHotelRepository.deleteByHotel_Id(hotelId);
        return "Hotel has been deleted";
    }
}
