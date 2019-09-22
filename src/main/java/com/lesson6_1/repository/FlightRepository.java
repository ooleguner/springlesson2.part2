package com.lesson6_1.repository;

import com.lesson6_1.model.Flight;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class FlightRepository implements RepositoryInterface<Flight> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Flight save(Flight flight) {
        entityManager.persist(flight);
        return flight;
    }

    @Override
    public Flight findById(Long id) {
        return entityManager.find(Flight.class, id);
    }

    @Override
    public Flight update(Flight flight) {
        return entityManager.merge(flight);
    }

    @Override
    public void delete(Flight flight) {
        entityManager.remove(flight);
    }

    @Override
    public boolean checkIfPresent(Flight flight) {
        Query q = entityManager.createNativeQuery("SELECT * FROM FLIGHT  WHERE PLANE_ID = :id AND CITYFROM = :from  AND CITYTO = :to AND DATEFLIGHT=:date ");
        q.setParameter("id", flight.getPlane().getId());
        q.setParameter("from", flight.getCityFrom());
        q.setParameter("to", flight.getCityTo());
        q.setParameter("date", flight.getDateFlight());

        List<Flight> listFlight = (List<Flight>) q.getResultList();
        if (listFlight == null || listFlight.isEmpty()) {
            return false;
        }
        return true;
    }

}
