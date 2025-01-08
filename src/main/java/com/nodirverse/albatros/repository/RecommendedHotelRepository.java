package com.nodirverse.albatros.repository;

import com.nodirverse.albatros.entity.RecommendedHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecommendedHotelRepository extends JpaRepository<RecommendedHotel, UUID> {
    Optional<RecommendedHotel> findByHotel_Id(UUID hotelId);

    void deleteByHotel_Id(UUID hotelId);
}
