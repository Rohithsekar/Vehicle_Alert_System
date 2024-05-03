package com.vehicle_alert.dto;

import com.vehicle_alert.entity.JourneyCoordinates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JourneyCoordinatesDTO {
    private JourneyCoordinates journeyCoordinates;
    private boolean isReversed;
}
