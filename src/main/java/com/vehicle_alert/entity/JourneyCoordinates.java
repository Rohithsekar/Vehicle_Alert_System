package com.vehicle_alert.entity;

import com.vehicle_alert.dto.Graticule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "journey_coordinates")
public class JourneyCoordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source")
    private String source;

    @Column(name = "sourceLat")
    private Double sourceLat;

    @Column(name = "sourceLng")
    private Double sourceLng;

    @Column(name = "destination")
    private String destination;

    @Column(name = "destinationLat")
    private Double destinationLat;

    @Column(name = "destinationLng")
    private Double destinationLng;

    // Annotate the collection of embedded objects
    @ElementCollection
    @CollectionTable(name = "journey_graticules", joinColumns = @JoinColumn(name = "journey_id"))
    private List<Graticule> graticuleList;
}
