package com.lesson6_1.repository;

import com.lesson6_1.model.Flight;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class FlightRepository implements RepositoryInterface<Flight> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Flight save(Flight flight) {
        entityManager.persist(flight);
        return flight;
    }
}
