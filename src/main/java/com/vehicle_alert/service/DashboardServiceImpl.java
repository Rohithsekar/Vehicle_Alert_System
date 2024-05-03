package com.vehicle_alert.service;

import com.vehicle_alert.Constants.Constants;
import com.vehicle_alert.criteria_query.CommonCriteriaBuilder;
import com.vehicle_alert.dto.APIResponse;
import com.vehicle_alert.dto.Graticule;
import com.vehicle_alert.dto.JourneyCoordinatesDTO;
import com.vehicle_alert.entity.JourneyCoordinates;
import com.vehicle_alert.entity.Location;
import com.vehicle_alert.interfaces.DashboardService;
import com.vehicle_alert.repository.LocationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {


    private LocationRepository locationRepository;
    @PersistenceContext
    private EntityManager entityManager;

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
    public ResponseEntity<APIResponse> fetchJourneyDetails(String providedSource, String providedDestination) {

        try{
            JourneyCoordinatesDTO journeyCoordinatesDTO = CommonCriteriaBuilder.fetchJourneyRecord(entityManager, providedSource, providedDestination);
            JourneyCoordinates journeyCoordinates = journeyCoordinatesDTO.getJourneyCoordinates();
            if (journeyCoordinatesDTO.isReversed()) {
                // If isReversed is true, reverse the graticuleList
                journeyCoordinates.setSource(providedSource);
                journeyCoordinates.setDestination(providedDestination);
                List<Graticule> graticuleList = journeyCoordinates.getGraticuleList();
                Collections.reverse(graticuleList);
                journeyCoordinates.setGraticuleList(graticuleList);
            }
            return ResponseEntity.ok().body(new APIResponse(Constants.SUCCESS, "Successfully fetched journey details", journeyCoordinatesDTO));
        }catch (NoResultException e){
            System.err.println(" NoResultException: " + e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(new APIResponse(Constants.ERROR, "Journey not found"));
        }

    }
}
