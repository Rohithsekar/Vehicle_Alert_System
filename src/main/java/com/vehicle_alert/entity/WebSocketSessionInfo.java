package com.vehicle_alert.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "socket_session_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketSessionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "local_address")
    private String localAddress;

    @Column(name = "connected_time")
    private Long connectedTime;

}
