package com.example.HUGOTravelPlanner.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
@Entity

public class DayAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_attribute_id")  // Foreign key column
    private List<PlaceAttribute> places_attributes;

    // Getters and Setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<PlaceAttribute> getPlaces_attributes() {
        return places_attributes;
    }

    public void setPlaces_attributes(List<PlaceAttribute> places_attributes) {
        this.places_attributes = places_attributes;
    }

    @Override
    public String toString() {
        return "DayAttribute{" +
                "date='" + date + '\'' +
                ", places_attributes=" + places_attributes +
                '}';
    }
}
