package com.vehicle_alert.controller;


import com.vehicle_alert.criteria_query.CommonCriteriaBuilder;
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

    /**
     * @return APIResponse object containing the locations list
     */
    @GetMapping("/dropdown")
    public ResponseEntity<APIResponse> dropdownMenu() {
        return dashboardService.fetchDropdownItems();
    }


    /**
     * @param source & destination contains the source and destination customer selected
     * @return APIResponse object containing the acknowledgement message
     */
    @PostMapping("/trip")
    public ResponseEntity<APIResponse> postTripPathDetails(
            @RequestParam String source,
            @RequestParam String destination
    ) {
        return dashboardService.postTripPath(source, destination);
    }

    @GetMapping("/get/journey")
    public ResponseEntity<APIResponse> getJourneyDetails(@RequestParam String providedSource,
                                                         @RequestParam String provideDestination) {
        return dashboardService.fetchJourneyDetails(providedSource, provideDestination);
    }
}
