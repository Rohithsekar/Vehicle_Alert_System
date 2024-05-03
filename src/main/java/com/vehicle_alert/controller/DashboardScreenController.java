package com.vehicle_alert.controller;


import com.vehicle_alert.dto.APIResponse;
import com.vehicle_alert.dto.JourneyPath;
import com.vehicle_alert.interfaces.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DashboardScreenController {

    private DashboardService dashboardService;

    public DashboardScreenController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dropdown")
    public ResponseEntity<APIResponse> dropdownMenu(){
        return dashboardService.fetchDropdownItems();
    }


    @PostMapping("/trip")
    public ResponseEntity<APIResponse> postTripPathDetails(@RequestBody JourneyPath journeyPath){

        return dashboardService.postTripPath(journeyPath);
    }
}
