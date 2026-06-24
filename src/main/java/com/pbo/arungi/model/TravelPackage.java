package com.pbo.arungi.model;

import jakarta.persistence.*;

@Entity
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String destination;

    private String description;

    private double price;

    private String image;

    private int duration;

    private String packageType;

    public TravelPackage() {
    }

    public String getPackageType() {
    return packageType;
    }

    public void setPackageType(String packageType) {
    this.packageType = packageType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDuration() {
    return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
}