package com.vehicle_alert.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vehicle_alert.entity.MobileLocationData;
import com.vehicle_alert.interfaces.WebsocketService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.Instant;

@Service
public class WebSocketServiceImpl implements WebsocketService {

    @Override
    public void processPayload(WebSocketSession session, TextMessage message) throws IOException {

        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        MobileLocationData mobileLocation = parsePayload(jsonObject);
    }

    private MobileLocationData parsePayload(JSONObject payload) {
        MobileLocationData location = new MobileLocationData();
        double latitude = Double.parseDouble(payload.get("latitude").toString());
        double longitude = Double.parseDouble(payload.get("longitude").toString());
        String deviceId = payload.get("deviceId").toString();
        String mobileId = payload.get("mobileId").toString();

        int tripId = Integer.parseInt(payload.get("tripId").toString());
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setDeviceID(deviceId);
        location.setTripID(tripId);
        location.setMobileId(mobileId);
        location.setTimestamp(Instant.now().getEpochSecond());

        return location;
    }
}
