package com.vehicle_alert.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JourneyPath {

    private String source;
    private Double sourceLat;
    private Double sourceLng;
    private String destination;
    private Double destinationLat;
    private Double destinationLng;
    private List<Graticule> graticuleList;
}
