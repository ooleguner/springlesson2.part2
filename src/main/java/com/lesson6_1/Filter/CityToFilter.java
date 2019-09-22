package com.lesson6_1.Filter;

import com.lesson6_1.model.Flight;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by oleg on 22.09.2019.
 */
public class CityToFilter implements FlightFilter {

    String[] param;

    @Override
    public String[] getParam() {
        return param;
    }

    public CityToFilter(String[] param) {
        this.param = param;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Flight> filter() {
        Query q = entityManager.createNativeQuery("SELECT * FROM FLIGHT WHERE CITYTO= ?", Flight.class);
        q.setParameter(1, param[0]);

        return q.getResultList();
    }
}
