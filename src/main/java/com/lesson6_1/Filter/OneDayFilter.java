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

    String[] param;

    @Override
    public String[] getParam() {
        return param;
    }

    public OneDayFilter(String[] param){
        this.param=param;
    }

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Flight> filter() {
        Query q = entityManager.createNativeQuery("SELECT * FROM FLIGHT WHERE DATEFLIGHT = ?", Flight.class);
        q.setParameter(1, param[0]);
        return q.getResultList();
    }
}
