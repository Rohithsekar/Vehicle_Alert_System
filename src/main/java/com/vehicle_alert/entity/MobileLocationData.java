package com.vehicle_alert.entity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mobilelocationdata")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MobileLocationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double latitude;
    private Double longitude;
    private String deviceID;
    private Integer tripID;
    private Long timestamp;
    private String mobileId;
}