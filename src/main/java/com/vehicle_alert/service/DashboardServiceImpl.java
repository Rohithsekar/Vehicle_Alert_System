package com.vehicle_alert.service;

import com.uber.h3core.H3Core;
import com.vehicle_alert.Constants.Constants;
import com.vehicle_alert.dto.APIResponse;
import com.vehicle_alert.dto.JourneyLatLngDTO;
import com.vehicle_alert.entity.Location;
import com.vehicle_alert.interfaces.DashboardService;
import com.vehicle_alert.repository.LocationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final LocationRepository locationRepository;

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

    @Override
    public ResponseEntity<APIResponse> journeyStartsMovement(JourneyLatLngDTO journeyLatLngDTO) {
        try {
            Double currentLatitude = journeyLatLngDTO.getGraticule().getLatitude();
            Double currentLongitude = journeyLatLngDTO.getGraticule().getLongitude();
//            Long journeyId = journeyLatLngDTO.getJourneyId();

            Double checkPointLat = journeyLatLngDTO.getCheckPointLat();
            Double checkPointLng = journeyLatLngDTO.getCheckPointLng();


            H3Core h3Core = H3Core.newInstance();
            long currentH3Index = h3Core.latLngToCell(currentLatitude, currentLongitude, 11);
            long checkPointH3Index = h3Core.latLngToCell(checkPointLat, checkPointLng, 11);

           boolean areNeighborCells = h3Core.areNeighborCells(currentH3Index, checkPointH3Index);

           return ResponseEntity.ok().body(new APIResponse(Constants.SUCCESS, "Check done successfully", areNeighborCells));

        } catch (Exception e) {
            System.err.println("Exception is " + e.getMessage());
            return ResponseEntity.internalServerError().body(new APIResponse(Constants.ERROR, "We are facing some issue. Please try again later"));
        }

    }
}
