package com.lesson6_1.Filter;

import com.lesson6_1.model.Flight;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ModelPlaneFilter implements FlightFilter {

    String[] param;

    @Override
    public String[] getParam() {
        return param;
    }

    public ModelPlaneFilter(String[] param) {
        this.param = param;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Flight> filter() {
        Query q = entityManager.createNativeQuery(
                "SELECT FLIGHT.ID, FLIGHT.PLANE_ID, FLIGHT.DATEFLIGHT, FLIGHT.CITYFROM, FLIGHT.CITYTO " +
                        "FROM FLIGHT JOIN PLANE ON PLANE_ID = PLANE.ID " +
                        "WHERE MODEL = ? ", Flight.class);
        q.setParameter(1, param[0]);

        return q.getResultList();
    }
}
