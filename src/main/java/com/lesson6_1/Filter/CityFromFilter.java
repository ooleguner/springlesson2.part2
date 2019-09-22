package com.lesson6_1.Filter;

import com.lesson6_1.model.Flight;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


public class CityFromFilter implements FlightFilter {

    String[] param;

    public CityFromFilter(String[] param) {
        this.param = param;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Flight> filter() {
        Query q = entityManager.createNativeQuery("SELECT * FROM FLIGHT WHERE CITYFROM= ?", Flight.class);
        q.setParameter(1, param[0]);

        return q.getResultList();
    }

    @Override
    public String[] getParam() {
        return param;
    }
}
