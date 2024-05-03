package com.vehicle_alert.repository;

import com.vehicle_alert.entity.JourneyCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyCoordinatesRepository extends JpaRepository<JourneyCoordinates, Long> {
}
