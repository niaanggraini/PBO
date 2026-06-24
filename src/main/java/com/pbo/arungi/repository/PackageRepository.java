package com.pbo.arungi.repository;

import com.pbo.arungi.model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackageRepository
        extends JpaRepository<TravelPackage, Long> {

    List<TravelPackage> findByDestination(
            String destination);

    @Query("""
        SELECT p
        FROM TravelPackage p
        WHERE
        (:destination IS NULL OR :destination = '' OR p.destination = :destination)
        AND
        (:duration IS NULL OR p.duration = :duration)
        AND
        (:minPrice IS NULL OR p.price >= :minPrice)
        AND
        (:maxPrice IS NULL OR p.price <= :maxPrice)
    """)
    List<TravelPackage> filterPackages(

            @Param("destination")
            String destination,

            @Param("duration")
            Integer duration,

            @Param("minPrice")
            Double minPrice,

            @Param("maxPrice")
            Double maxPrice
    );

}