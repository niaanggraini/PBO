package com.pbo.arungi.service;

import com.pbo.arungi.model.TravelPackage;
import com.pbo.arungi.repository.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    private final PackageRepository packageRepository;

    public PackageService(
            PackageRepository packageRepository) {

        this.packageRepository = packageRepository;
    }

    public List<TravelPackage> getAllPackages() {

        return packageRepository.findAll();

    }

    public List<TravelPackage> getPackagesByDestination(
            String destination) {

        return packageRepository
                .findByDestination(destination);

    }

    public void savePackage(
            TravelPackage travelPackage) {

        packageRepository.save(travelPackage);

    }

    public TravelPackage getPackageById(
            Long id) {

        return packageRepository
                .findById(id)
                .orElse(null);

    }

    public List<TravelPackage> filterPackages(

            String destination,

            Integer duration,

            Double minPrice,

            Double maxPrice) {

        return packageRepository
                .filterPackages(
                        destination,
                        duration,
                        minPrice,
                        maxPrice);
    }
}