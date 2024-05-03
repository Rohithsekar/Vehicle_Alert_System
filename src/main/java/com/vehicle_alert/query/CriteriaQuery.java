package com.vehicle_alert.query;

import com.vehicle_alert.dto.JourneyCoordinatesDTO;
import com.vehicle_alert.entity.JourneyCoordinates;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class CriteriaQuery {

    public static JourneyCoordinatesDTO fetchJourneyRecord(EntityManager entityManager, String providedSource, String providedDestination) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<JourneyCoordinates> criteriaQuery = criteriaBuilder.createQuery(JourneyCoordinates.class);
        Root<JourneyCoordinates> root = criteriaQuery.from(JourneyCoordinates.class);
        Predicate sourceDestinationMatch = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("source"), providedSource),
                criteriaBuilder.equal(root.get("destination"), providedDestination)
        );

        Predicate destinationSourceMatch = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("source"), providedDestination),
                criteriaBuilder.equal(root.get("destination"), providedSource)
        );

        // Combine predicates with OR operator
        Predicate finalPredicate = criteriaBuilder.or(sourceDestinationMatch, destinationSourceMatch);
        criteriaQuery.where(finalPredicate);

        try {
            JourneyCoordinates journeyCoordinates = entityManager.createQuery(criteriaQuery).getSingleResult();
            JourneyCoordinatesDTO journeyCoordinatesDTO = new JourneyCoordinatesDTO();
            journeyCoordinatesDTO.setJourneyCoordinates(journeyCoordinates);

            // Set isReversed flag based on the match
            if (providedSource.equals(journeyCoordinates.getSource()) && providedDestination.equals(journeyCoordinates.getDestination())) {
                journeyCoordinatesDTO.setReversed(false);
            } else if (providedSource.equals(journeyCoordinates.getDestination()) && providedDestination.equals(journeyCoordinates.getSource())) {
                journeyCoordinatesDTO.setReversed(true);
            }

            return journeyCoordinatesDTO;
        } catch (NoResultException e) {
            throw new NoResultException("No data found");
        }
    }
}
