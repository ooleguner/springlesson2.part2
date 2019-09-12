package com.lesson6_1.repository;

import com.lesson6_1.model.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PassengerRepository implements RepositoryInterface<Passenger> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Passenger save(Passenger passenger) {
        entityManager.persist(passenger);
        return passenger;
    }
}
