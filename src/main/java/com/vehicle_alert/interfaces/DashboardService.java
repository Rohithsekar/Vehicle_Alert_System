package com.vehicle_alert.interfaces;

import com.vehicle_alert.dto.APIResponse;
import com.vehicle_alert.dto.JourneyPath;
import org.springframework.http.ResponseEntity;

public interface DashboardService {

    ResponseEntity<APIResponse> fetchDropdownItems();

    ResponseEntity<APIResponse> postTripPath(JourneyPath journeyPath);
}
