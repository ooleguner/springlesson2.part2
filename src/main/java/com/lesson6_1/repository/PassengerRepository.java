package com.lesson6_1.repository;

import com.lesson6_1.model.Passenger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    @Override
    public boolean checkIfPresent(Passenger passenger) {
        Query q = entityManager.createNativeQuery("SELECT * FROM PASSENGER  WHERE LASTNAME = :LASTNAME AND PASPORTCODE = :PASPORTCODE ");
        q.setParameter("LASTNAME", passenger.getLastName());
        q.setParameter("PASPORTCODE", passenger.getPasportCode());

        List<Passenger> listPasenger = (List<Passenger>) q.getResultList();
        if (listPasenger == null || listPasenger.isEmpty()) {
            return false;
        }
        return true;
    }
}
