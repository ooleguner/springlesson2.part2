package com.lesson6_1.repository;

import com.lesson6_1.model.Flight;
import com.lesson6_1.model.Plane;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by oleg on 19.09.2019.
 */
public class FilterHelper {

    @PersistenceContext
    EntityManager entityManager;

    // SELECT * FROM  PLANE WHERE YEAR_PRODUCED < ADD_MONTHS(TRUNC(SYSDATE,'yyyy'), -240);
    public List<Plane> oldPlanes() {

        Query q = entityManager.createNativeQuery("SELECT * FROM  " +
                "PLANE WHERE YEAR_PRODUCED < ADD_MONTHS(TRUNC(SYSDATE,'yyyy'), -240) ", Plane.class);

        List<Plane> oldPlanes = (List<Plane>) q.getResultList();
        return oldPlanes;
    }

    public List<Object[]> customersWithMoreThan25Flights(int year) {
        Query q = entityManager.createNativeQuery("SELECT * FROM " +
                "(SELECT COUNT(*) as count, PASSENGER_ID as pass_id , LASTNAME as LASTNAME " +
                "FROM join_flight_passenger " +
                "JOIN passenger ON join_flight_passenger.passenger_id = passenger.id " +
                "JOIN flight ON join_flight_passenger.flight_id = flight.id where extract(year from DATEFLIGHT) = :year " +
                "GROUP BY PASSENGER_ID, LASTNAME) " +
                "where count > 25");
        q.setParameter("year", year);
        return q.getResultList();
    }

    public List<Object[]> getRegularPlanes(int year) {
        Query q = entityManager.createNativeQuery("SELECT * FROM " +
                "(SELECT COUNT(*) as count, PLANE_ID as plane_id , PLANE.MODEL as model " +
                "FROM FLIGHT JOIN PLANE ON FLIGHT.PLANE_ID = PLANE.ID  " +
                "WHERE EXTRACT(YEAR from DATEFLIGHT) = ? " +
                "GROUP BY plane_id, model) WHERE count >=300 ");
        q.setParameter(1, year);
        return q.getResultList();

    }

    public List<Object[]> mostPopularTo() {
        Query q = entityManager.createNativeQuery("SELECT * FROM " +
                "(SELECT COUNT(*) AS count, CITYTO AS cityTo " +
                "FROM  FLIGHT GROUP BY cityTo " +
                "ORDER BY COUNT DESC) " +
                "WHERE ROWNUM <= 10 ");
        return q.getResultList();
    }

    public List<Object[]> mostPopularFrom() {
        Query q = entityManager.createNativeQuery("SELECT * FROM " +
                "(SELECT COUNT(*) AS count, CITYFROM AS ciyFrom " +
                "FROM  FLIGHT GROUP BY CITYFROM ORDER BY count DESC) " +
                "where rownum <= 10");
        return q.getResultList();
    }

}
