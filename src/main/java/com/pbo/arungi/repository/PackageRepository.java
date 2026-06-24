package com.pbo.arungi.repository;

import com.pbo.arungi.model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository
        extends JpaRepository<TravelPackage, Long> {

    List<TravelPackage> findByDestination(String destination);

}