package com.nodirverse.albatros.service;


import com.nodirverse.albatros.dto.response.LocationResponse;
import com.nodirverse.albatros.entity.Location;
import com.nodirverse.albatros.exception.DataNotFoundException;
import com.nodirverse.albatros.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;

    public String create(double latitude, double longitude) {
        locationRepository.save(new Location(latitude, longitude));
        return "Location created";
    }

    public List<LocationResponse> get(){
        List<LocationResponse> list = new ArrayList<>();
        locationRepository.findAll()
                .forEach(location -> {
                    LocationResponse response = modelMapper.map(location, LocationResponse.class);
                    response.setId(location.getId());
                    list.add(response);
                });
        return list;
    }

    public String update(UUID id, double latitude, double longitude) {
        Location location = locationRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Location not found")
        );
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationRepository.save(location);
        return "Location updated";
    }
    public String delete(UUID id) {
        locationRepository.deleteById(id);
        return "Location deleted";
    }
}
