package com.nodirverse.albatros.repository;

import com.nodirverse.albatros.entity.TourPackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.UUID;

public interface TourPackageRepository extends JpaRepository<TourPackage, UUID>, JpaSpecificationExecutor<TourPackage> {
    Page<TourPackage> findTourPackagesByOrderByTicketDate(Pageable pageable);

}
