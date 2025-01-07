package com.nodirverse.albatros.repository;

import com.nodirverse.albatros.entity.Hotel;
import com.nodirverse.albatros.entity.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    List<Hotel> findAllByCategory(Category category);
}
