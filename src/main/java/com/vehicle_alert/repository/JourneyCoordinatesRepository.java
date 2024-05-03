package com.vehicle_alert.repository;

import com.vehicle_alert.entity.JourneyCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JourneyCoordinatesRepository extends JpaRepository<JourneyCoordinates, Long> {

    Optional<JourneyCoordinates> findBySourceAndDestination(String source, String destination);
}
