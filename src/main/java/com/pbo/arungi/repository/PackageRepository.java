package com.pbo.arungi.repository;

import com.pbo.arungi.model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<TravelPackage, Long> {
}