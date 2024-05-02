package com.vehicle_alert.interfaces;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;


public interface WebsocketService {
    void processPayload(WebSocketSession session, TextMessage message) throws IOException;
}
