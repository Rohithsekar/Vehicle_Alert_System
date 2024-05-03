package com.vehicle_alert.controller;


import com.vehicle_alert.dto.APIResponse;
import com.vehicle_alert.interfaces.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DashboardScreenController {

    private DashboardService dashboardService;

    public DashboardScreenController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<APIResponse> dropdownMenu(){
        return dashboardService.fetchDropdownItems();
    }
}
