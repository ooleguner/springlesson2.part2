package com.lesson6_1.repository;

import com.lesson6_1.model.Passenger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

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

    @Override
    public Passenger findById(Long id) {
        return entityManager.find(Passenger.class, id);
    }

    @Override
    public Passenger update(Passenger passenger) {
        return entityManager.merge(passenger);
    }

    @Override
    public void delete(Passenger passenger) throws DataIntegrityViolationException {
        try {
            entityManager.remove(passenger);
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
    }
}
