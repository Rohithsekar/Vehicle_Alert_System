package com.vehicle_alert.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;


//Graticule is a container term encompassing both latitude and longitude
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Graticule {

    private Double latitude;
    private Double longitude;

    @JsonIgnore
    private Long h3Index;

    @JsonIgnore
    private Integer h3resolution;

    public Graticule(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
