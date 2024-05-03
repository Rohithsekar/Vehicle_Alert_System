package com.vehicle_alert.interfaces;

import com.vehicle_alert.dto.*;
import org.springframework.http.ResponseEntity;

public interface DashboardService {

    ResponseEntity<APIResponse> login(LoginDTO loginDTO);

    ResponseEntity<APIResponse> fetchDropdownItems();

    ResponseEntity<APIResponse> postTripPath(String source, String destination);

    ResponseEntity<APIResponse> journeyStartsMovement(JourneyLatLngDTO journeyLatLngDTO);
}
