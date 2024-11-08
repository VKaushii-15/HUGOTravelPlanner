package com.example.HUGOTravelPlanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlaceAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private String name;
    private String placeId;
    private String category;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "PlaceAttribute{" +
                ", name='" + name + '\'' +
                ", placeId='" + placeId + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
