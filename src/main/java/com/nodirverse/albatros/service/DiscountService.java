package com.nodirverse.albatros.service;

import com.nodirverse.albatros.dto.request.DiscountCreateRequest;
import com.nodirverse.albatros.dto.response.DiscountResponse;
import com.nodirverse.albatros.dto.response.TourPackageResponse;
import com.nodirverse.albatros.entity.Discount;
import com.nodirverse.albatros.entity.TourPackage;
import com.nodirverse.albatros.exception.DataNotFoundException;
import com.nodirverse.albatros.repository.DiscountRepository;
import com.nodirverse.albatros.repository.TourPackageRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final TourPackageRepository tourPackageRepository;
    private final ModelMapper modelMapper;

    public String create(DiscountCreateRequest request){
        TourPackage tourPackage = tourPackageRepository.findById(request.getTourPackageId()).orElseThrow(
                () -> new DataNotFoundException("Tour package not found!")
        );
        Discount discount = Discount.builder()
                .tourPackage(tourPackage)
                .newPrice(request.getNewPrice())
                .description(request.getDescription())
                .build();

        discountRepository.save(discount);
        return "Discount created!";
    }

    public List<DiscountResponse> getAll() {
        List<Discount> list = discountRepository.findAll();
        List<DiscountResponse> responses = new ArrayList<>();
        list.forEach(
                discount -> {
                    TourPackageResponse tourPackageResponse = modelMapper.map(discount.getTourPackage(), TourPackageResponse.class );
                    DiscountResponse discountResponse = DiscountResponse.builder()
                            .id(discount.getId())
                            .tourPackageResponse(tourPackageResponse)
                            .newPrice(discount.getNewPrice())
                            .description(discount.getDescription())
                            .build();
                    responses.add(discountResponse);
                }
        );
        return responses;
    }

    public String delete(UUID id){
        discountRepository.deleteById(id);
        return "Discount deleted!";
    }
}
