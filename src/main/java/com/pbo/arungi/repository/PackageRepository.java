package com.pbo.arungi.Repository;

import com.pbo.arungi.Model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<TravelPackage, Long> {
}

