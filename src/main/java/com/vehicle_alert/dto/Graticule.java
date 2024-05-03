package com.vehicle_alert.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Graticule {

    private Double latitude;
    private Double longitude;
    private Long h3Index;
    private Integer h3resolution;

    public Graticule(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
