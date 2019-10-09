package com.lesson6_1.repository;

import com.lesson6_1.model.Passenger;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class PassengerRepository implements RepositoryInterface<Passenger> {

    // пассажиры, с больше 25 полетов за год
    private String passengerWithMore25Flight = "SELECT * FROM " +
            "(SELECT COUNT(*) AS COUNT, PASSENGER_ID AS PASS_ID , LASTNAME AS LASTNAME " +
            "FROM JOIN_FLIGHT_PASSENGER " +
            "JOIN PASSENGER ON JOIN_FLIGHT_PASSENGER.PASSENGER_ID = PASSENGER.ID " +
            "JOIN FLIGHT ON JOIN_FLIGHT_PASSENGER.FLIGHT_ID = FLIGHT.ID WHERE EXTRACT(YEAR FROM DATEFLIGHT) = :year " +
            "GROUP BY PASSENGER_ID, LASTNAME) " +
            "WHERE COUNT > 25";

    private String checkIfPresent = "SELECT * FROM PASSENGER  WHERE LASTNAME = :LASTNAME AND PASPORTCODE = :PASPORTCODE ";

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
        Query q = entityManager.createNativeQuery(checkIfPresent);
        q.setParameter("LASTNAME", passenger.getLastName());
        q.setParameter("PASPORTCODE", passenger.getPasportCode());

        List<Passenger> listPasenger = (List<Passenger>) q.getResultList();
        if (listPasenger == null || listPasenger.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<Object[]> customersWithMoreThan25Flights(int year) {
        Query q = entityManager.createNativeQuery(passengerWithMore25Flight);
        q.setParameter("year", year);
        return q.getResultList();
    }
}
