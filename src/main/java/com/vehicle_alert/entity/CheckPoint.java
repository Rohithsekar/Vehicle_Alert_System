package com.vehicle_alert.entity;

import com.vehicle_alert.dto.Graticule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "check_point")
public class CheckPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_point_id")
    private Long id;
    @Column(name = "check_point_type")
    private String checkPointType;

    @Embedded
    private Graticule graticule;
}
