package com.example.HUGOTravelPlanner.controller;
import java.util.List;
import com.example.HUGOTravelPlanner.model.DayAttribute;
import com.example.HUGOTravelPlanner.model.Trip;
import com.example.HUGOTravelPlanner.repository.UserRepository;
//import com.example.HUGOTravelPlanner.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

//import aj.org.objectweb.asm.TypeReference;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TripController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = userRepository.findAllTrips(); // Assuming you have a method like this in your repository
        return ResponseEntity.ok(trips);
    }

    @Transactional
    @PostMapping("/trips")
    public ResponseEntity<String> createTrip(@RequestBody Trip tripData) {
        try {
            Trip trip = new Trip();

            trip.setCity(tripData.getCity());
            trip.setLat(tripData.getLat());
            trip.setLng(tripData.getLng());
            ObjectMapper objectMapper = new ObjectMapper();
            String daysJson = objectMapper.writeValueAsString(tripData.getDays_attributes());
            List<DayAttribute> daysList = objectMapper.readValue(daysJson, new TypeReference<List<DayAttribute>>() {});
            trip.setDays_attributes(daysList);

            // Log received trip data
            System.out.println("City: " + trip.getCity());
            System.out.println("Latitude: " + trip.getLat());
            System.out.println("Longitude: " + trip.getLng());
            System.out.println("Days: " + daysList);

            userRepository.insertItenerary(trip.getCity() , trip.getLat() , trip.getLng() , daysJson);

            return ResponseEntity.ok("Itinerary inserted");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving trip data");
        }
    }
}


