package com.example.HUGOTravelPlanner.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Embeddable
@Table(name = "trips")
public class Trip {
    @Id
    private Long id;
    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "latitude")
    private Double lat;

    @Column(name = "longitude")
    private Double lng;

    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL)
    @CollectionTable(name = "trip_days", joinColumns = @JoinColumn(name = "trip_id"))
    private List<DayAttribute> days_attributes;

    // Getters and Setters

    public List<DayAttribute> getDays_attributes() {
        return days_attributes;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public void setDays_attributes(List<DayAttribute> days_attributes) {
        this.days_attributes = days_attributes;
    }
}
