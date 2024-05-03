package com.vehicle_alert.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JourneyLatLngDTO {

    private Graticule graticule;
//    private Long journeyId;
    private Double checkPointLat;
    private Double checkPointLng;
}
