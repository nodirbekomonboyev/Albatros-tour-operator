package com.nodirverse.albatros.specification;

import org.springframework.data.jpa.domain.Specification;
import com.nodirverse.albatros.entity.TourPackage;
import com.nodirverse.albatros.entity.enums.*;

import java.time.LocalDate;

public class TourPackageSpecification {

    public static Specification<TourPackage> hasDepartureCity(DepartureCity departureCity) {
        return (root, query, criteriaBuilder) ->
                departureCity == null ? null : criteriaBuilder.equal(root.get("departureCity"), departureCity);
    }

    public static Specification<TourPackage> hasCountry(String countryName) {
        return (root, query, criteriaBuilder) -> {
            if (countryName == null) return null;
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("country").get("name")),
                    "%" + countryName.toLowerCase() + "%"
            );
        };
    }

    public static Specification<TourPackage> hasHotel(String hotelName) {
        return (root, query, criteriaBuilder) -> {
            if (hotelName == null) return null;
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("hotel").get("name")),
                    "%" + hotelName.toLowerCase() + "%"
            );
        };
    }

    public static Specification<TourPackage> hasNutrition(Nutrition nutrition) {
        return (root, query, criteriaBuilder) ->
                nutrition == null ? null : criteriaBuilder.equal(root.get("nutrition"), nutrition);
    }

    public static Specification<TourPackage> hasTicketDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null || endDate == null) return null;
            return criteriaBuilder.between(root.get("ticketDate"), startDate, endDate);
        };
    }

    public static Specification<TourPackage> hasTransport(Transport transport) {
        return (root, query, criteriaBuilder) ->
                transport == null ? null : criteriaBuilder.equal(root.get("transport"), transport);
    }
}
