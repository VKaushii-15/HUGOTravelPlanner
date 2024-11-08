package com.example.HUGOTravelPlanner.repository;

import com.example.HUGOTravelPlanner.model.Trip;
import com.example.HUGOTravelPlanner.model.User;

import java.util.List;

//import jakarta.transaction.Transactional;

//import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "INSERT IGNORE INTO user_details (username, phone_number, email) VALUES (:username, :phoneNumber, :email)", nativeQuery = true)
    void insertIgnoreUser(@Param("username") String username, @Param("phoneNumber") String phoneNumber, @Param("email") String email);
    
    @Modifying
    @Query(value = "USE APP",nativeQuery = true)
    void useapp();

    /*@Modifying
    @Query(value = "CREATE DATABASE IF NOT EXISTS :phonenumber",nativeQuery = true)
    void createItenerary(@Param("phonenumber") String email);

    @Modifying
    @Query(value = "USE :phonenumber",nativeQuery = true)
    void useemail(@Param("phonenumber") String email);*/

    @Modifying
    @Query(value = "CREATE TABLE IF NOT EXISTS trips(City VARCHAR(255) PRIMARY KEY NOT NULL , latitude DECIMAL(10,8) NOT NULL , longitude DECIMAL (11,8) NOT NULL , days JSON NOT NULL)",nativeQuery = true)
    void existItenerary();

    //@Modifying
    //@Query(value = "DESCRIBE trips",nativeQuery = true)
    //void describetrips();
    
    @Modifying
    @Query(value = "INSERT INTO trips (City, latitude, longitude, days) VALUES (:city, :latitude, :longitude, :days)", nativeQuery = true)
    void insertItenerary(@Param("city")String city, @Param("latitude")Double latitude, @Param("longitude")Double longitude, @Param("days")String days);

    @Modifying
    @Query(value = "SELECT * FROM trips", nativeQuery = true)
    List<Trip> findAllTrips();

}