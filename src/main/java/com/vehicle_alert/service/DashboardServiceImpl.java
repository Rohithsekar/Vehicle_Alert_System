package com.vehicle_alert.service;

import com.uber.h3core.H3Core;
import com.vehicle_alert.constants.Constants;
import com.vehicle_alert.dto.*;
import com.vehicle_alert.entity.JourneyCoordinates;
import com.vehicle_alert.entity.Location;
import com.vehicle_alert.interfaces.DashboardService;
import com.vehicle_alert.query.CriteriaQuery;
import com.vehicle_alert.repository.LocationRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final LocationRepository locationRepository;

    private RequestMeta requestMeta;
    @PersistenceContext
    private EntityManager entityManager;

    public DashboardServiceImpl(LocationRepository locationRepository,
                                RequestMeta requestMeta) {
        this.locationRepository = locationRepository;
        this.requestMeta = requestMeta;
    }

    @Override
    public ResponseEntity<APIResponse> login(LoginDTO loginDTO) {

        requestMeta.setUsername(loginDTO.getUsername());
        return ResponseEntity.ok(new APIResponse(Constants.SUCCESS, "logged in successfully"));
    }

    @Override
    public ResponseEntity<APIResponse> fetchDropdownItems() {

        List<Location> locationList = locationRepository.findAll();

        return ResponseEntity.ok().body(new APIResponse(Constants.SUCCESS, "Successfully fetched locations", locationList));
    }

    @Override
    public ResponseEntity<APIResponse> postTripPath(String source, String destination) {


        try {
            JourneyCoordinatesDTO journeyCoordinatesDTO = CriteriaQuery.fetchJourneyRecord(entityManager, source, destination);
            JourneyCoordinates journeyCoordinates = journeyCoordinatesDTO.getJourneyCoordinates();
            if (journeyCoordinatesDTO.isReversed()) {
                // If isReversed is true, reverse the graticuleList
                journeyCoordinates.setSource(source);
                journeyCoordinates.setDestination(destination);
                List<Graticule> graticuleList = journeyCoordinates.getGraticuleList();
                Collections.reverse(graticuleList);

                journeyCoordinates.setGraticuleList(graticuleList);

            }


            List<Graticule> graticuleList = journeyCoordinates.getGraticuleList();
            // Find the starting index for the lower half of the list
            int halfWayIndex = graticuleList.size() / 2;

            // Generate a random index from the halfway point to the end of the list
            Random random = new Random();
            int randomIndex = random.nextInt(graticuleList.size() - halfWayIndex) + halfWayIndex;

            Graticule graticule = graticuleList.get(randomIndex);

            journeyCoordinatesDTO.setCheckpoint(graticule);

            return ResponseEntity.ok().body(new APIResponse(Constants.SUCCESS, "Successfully fetched journey details", journeyCoordinatesDTO));
        } catch (NoResultException e) {
            System.err.println(" NoResultException: " + e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(new APIResponse(Constants.ERROR, "Journey not found"));
        }

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
            long currentH3Index = h3Core.latLngToCell(currentLatitude, currentLongitude, 10);
            long checkPointH3Index = h3Core.latLngToCell(checkPointLat, checkPointLng, 10);

            System.out.println("curentH3Index is " + currentH3Index + " and checkPointH3Index is "+ checkPointH3Index);

            if(currentH3Index==checkPointH3Index){
                System.out.println(" true ");
            }

            boolean areNeighborCells = h3Core.areNeighborCells(currentH3Index, checkPointH3Index);


            return ResponseEntity.ok().body(new APIResponse(Constants.SUCCESS, "Check done successfully", areNeighborCells));

        } catch (Exception e) {
            System.err.println("Exception is " + e.getMessage());
            return ResponseEntity.internalServerError().body(new APIResponse(Constants.ERROR, "We are facing some issue. Please try again later"));
        }

    }
}
