package com.vehicle_alert.service;

import com.uber.h3core.H3Core;
import com.vehicle_alert.Constants.Constants;
import com.vehicle_alert.dto.APIResponse;
import com.vehicle_alert.dto.Graticule;
import com.vehicle_alert.dto.JourneyPath;
import com.vehicle_alert.entity.JourneyCoordinates;
import com.vehicle_alert.entity.Location;
import com.vehicle_alert.interfaces.DashboardService;
import com.vehicle_alert.repository.LocationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {


    private LocationRepository locationRepository;

    public DashboardServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public ResponseEntity<APIResponse> fetchDropdownItems() {

        List<Location> locationList = locationRepository.findAll();

        return ResponseEntity.ok().body(new APIResponse(Constants.SUCCESS, "Successfully fetched locations", locationList));
    }

    @Override
    public ResponseEntity<APIResponse> postTripPath(String source, String destination) {





        return null;

    }
}
