package com.vehicle_alert.entity;


import com.vehicle_alert.dto.Graticule;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "location")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Embedded()
    private Graticule graticule;

    public Location(String address, Graticule graticule) {
        this.address = address;
        this.graticule = graticule;

    }
}
