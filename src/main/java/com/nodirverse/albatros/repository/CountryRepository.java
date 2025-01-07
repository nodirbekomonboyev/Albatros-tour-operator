package com.nodirverse.albatros.repository;

import com.nodirverse.albatros.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {
    List<Country> findCountriesByIsSeasonalTrue();
}
