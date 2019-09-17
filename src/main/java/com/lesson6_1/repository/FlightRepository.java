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

}
