package com.nodirverse.albatros.repository;

import com.nodirverse.albatros.entity.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TourPackageRepository extends JpaRepository<TourPackage, UUID> {
}
