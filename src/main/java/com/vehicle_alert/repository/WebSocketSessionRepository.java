package com.vehicle_alert.repository;

import com.vehicle_alert.entity.WebSocketSessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebSocketSessionRepository extends JpaRepository<WebSocketSessionInfo,Integer> {
}
