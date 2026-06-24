package com.pbo.arungi.repository;

import com.pbo.arungi.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepository
        extends JpaRepository<Destination, Long> {

    List<Destination>
    findByNameContainingIgnoreCase(String keyword);

    List<Destination>
    findByCategory(String category);

}