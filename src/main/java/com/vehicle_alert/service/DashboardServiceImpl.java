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
    public ResponseEntity<APIResponse> postTripPath(JourneyPath journeyPath) {

        try {

            //convert journey coordinates into h3 indices and save it to database

            String source = journeyPath.getSource();
            String destination = journeyPath.getDestination();
            Double sourceLat = journeyPath.getSourceLat();
            Double sourcelLng = journeyPath.getSourceLng();
            Double destinationLat = journeyPath.getDestinationLat();
            Double destinationLng = journeyPath.getDestinationLng();

            List<Graticule> graticuleList = journeyPath.getGraticuleList();

            Integer res = 11;
            H3Core h3Core = H3Core.newInstance();

            List<Long> h3Indices = new ArrayList<>();

            graticuleList.forEach(graticule -> {

                Long h3Index = h3Core.latLngToCell(graticule.getLatitude(), graticule.getLongitude(), res);

                graticule.setH3Index(h3Index);
                graticule.setH3resolution(res);


            });

            JourneyCoordinates journeyCoordinates = new JourneyCoordinates();

            journeyCoordinates.setSource(source);
            journeyCoordinates.setDestination(destination);
            journeyCoordinates.setSourceLat(sourceLat);
            journeyCoordinates.setSourceLng(sourcelLng);
            journeyCoordinates.setDestinationLat(destinationLat);
            journeyCoordinates.setDestinationLng(destinationLng);
            journeyCoordinates.setGraticuleList(graticuleList);



        } catch (IOException e) {
            System.err.println("IOException happened in post trip path method:" + e.getMessage());
        }

        return null;

    }
}
