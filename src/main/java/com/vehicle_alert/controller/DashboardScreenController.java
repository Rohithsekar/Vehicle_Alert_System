package com.vehicle_alert.controller;


import com.vehicle_alert.dto.*;
import com.vehicle_alert.interfaces.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class DashboardScreenController {

    private DashboardService dashboardService;

    public DashboardScreenController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }




    public ResponseEntity<APIResponse> login(@RequestBody LoginDTO loginDTO){
        return dashboardService.login(loginDTO);
    }
    /**
     *
     * @return APIResponse object containing the locations list
     */
    @GetMapping("/dropdown")
    public ResponseEntity<APIResponse> dropdownMenu(){
        return dashboardService.fetchDropdownItems();
    }


    /**
     *
     * @param source & destination contains the source and destination customer selected
     * @return APIResponse object containing the acknowledgement message
     */
    @PostMapping("/trip")
    public ResponseEntity<APIResponse> postTripPathDetails(
            @RequestParam String source,
            @RequestParam String destination){
        return dashboardService.postTripPath(source,destination);
    }

    @PostMapping("/alert")
    public ResponseEntity<APIResponse> journeyStartsMovement(@RequestBody JourneyLatLngDTO journeyLatLngDTO){

        return dashboardService.journeyStartsMovement(journeyLatLngDTO);
    }
}
