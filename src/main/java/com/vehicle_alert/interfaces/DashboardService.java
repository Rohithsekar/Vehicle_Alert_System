package com.vehicle_alert.interfaces;

import com.vehicle_alert.dto.APIResponse;
import org.springframework.http.ResponseEntity;

public interface DashboardService {

    ResponseEntity<APIResponse> fetchDropdownItems();
}
