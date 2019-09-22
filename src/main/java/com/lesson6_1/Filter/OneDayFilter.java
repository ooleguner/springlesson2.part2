package com.lesson6_1.Filter;

import com.lesson6_1.model.Flight;
import com.lesson6_1.repository.FilterHelper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OneDayFilter implements FlightFilter {

    @PersistenceContext
    EntityManager entityManager;

    private LocalDate dateOfFlight;


    @Override
    public List<Flight> filter(String param) {
        Query q = entityManager.createNativeQuery("SELECT * FROM FLIGHT WHERE DATEFLIGHT = :dateOfFlight", Flight.class);
        q.setParameter("dateOfFlight", dateOfFlight);
        return q.getResultList();
    }
}
