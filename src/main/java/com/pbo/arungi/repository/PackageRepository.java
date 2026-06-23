package com.pbo.arungi.Repository;

import com.pbo.arungi.Model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository
        extends JpaRepository<TravelPackage, Long> {

    List<TravelPackage> findByDestination(String destination);

}