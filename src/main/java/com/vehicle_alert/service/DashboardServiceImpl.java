package com.vehicle_alert.service;

import com.vehicle_alert.dto.APIResponse;
import com.vehicle_alert.interfaces.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Override
    public ResponseEntity<APIResponse> fetchDropdownItems() {

        return null;
    }
}
