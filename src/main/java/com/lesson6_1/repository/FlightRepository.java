package com.lesson6_1.repository;

import com.lesson6_1.model.Flight;
import com.lesson6_1.model.Plane;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository implements RepositoryInterface<Flight> {

    //  список ТОП 10 самых популярных рейсов по городам назначения
    private String mostPopularTo =
            "SELECT * FROM (SELECT COUNT(*) AS COUNT, CITYTO AS CITYTO FROM  FLIGHT " +
                    "GROUP BY CITYTO ORDER BY COUNT DESC) WHERE ROWNUM <= 10 ";

    // список ТОП 10 самых популярных рейсов по городам вылета
    private String mostPopularFrom =
            "SELECT * FROM (SELECT COUNT(*) AS COUNT, CITYFROM AS CITYFROM FROM  FLIGHT " +
                    "GROUP BY CITYFROM ORDER BY COUNT DESC) WHERE ROWNUM <= 10";

    private String checkIfPresent = "SELECT * FROM FLIGHT  WHERE PLANE_ID = :ID " +
            "AND CITYFROM = :FROM  " +
            "AND CITYTO = :TO " +
            "AND DATEFLIGHT=:DATE ";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Flight save(Flight flight) {
        entityManager.persist(flight);
        return flight;
    }

    @Override
    public Flight findById(Long id) {
        return entityManager.find(Flight.class, id);
    }

    @Override
    public Flight update(Flight flight) {
        return entityManager.merge(flight);
    }

    @Override
    public void delete(Flight flight) {
        entityManager.remove(flight);
    }

    @Override
    public boolean checkIfPresent(Flight flight) {
        Query q = entityManager.createNativeQuery(checkIfPresent);
        q.setParameter("ID", flight.getPlane().getId());
        q.setParameter("FROM", flight.getCityFrom());
        q.setParameter("TO", flight.getCityTo());
        q.setParameter("DATE", flight.getDateFlight());

        List<Flight> listFlight = (List<Flight>) q.getResultList();
        if (listFlight == null || listFlight.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<Object[]> mostPopularTo() {
        Query q = entityManager.createNativeQuery(mostPopularTo);
        return q.getResultList();
    }

    public List<Object[]> mostPopularFrom() {
        Query q = entityManager.createNativeQuery(mostPopularFrom);
        return q.getResultList();
    }


    public List<Flight> flightsByDate(String modelPlane, String dateString, String cityFrom, String dateFrom, String dateTo, String cityTo) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> criteriaQuery = criteriaBuilder.createQuery(Flight.class);

        Root<Flight> flight = criteriaQuery.from(Flight.class);
   //     Root<Plane> plane = criteriaQuery.from(Plane.class);

/*      при таком связывании получал задвоенные результаты

        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<Plane> plane = criteriaQuery.from(Plane.class);
        Root<Flight> flight = criteriaQuery.from(Flight.class);
        criteriaQuery.multiselect(plane, flight);
        criteriaQuery.where(criteriaBuilder.equal(flight.get("plane"), plane.get("id")));
*/

        List<Predicate> predicates = new ArrayList<>();

        if (cityFrom != null) {
            predicates.add(criteriaBuilder.equal(flight.get("cityFrom"), cityFrom));
        }
        if (cityTo != null) {
            predicates.add(criteriaBuilder.equal(flight.get("cityTo"), cityTo));
        }
        if (dateString != null) {
            LocalDate date = LocalDate.parse(dateString);
            predicates.add(criteriaBuilder.equal(flight.get("dateFlight"), date));
        }
        if (dateFrom != null && dateTo != null) {
            LocalDate localDateFrom = LocalDate.parse(dateFrom);
            LocalDate localDate1To = LocalDate.parse(dateTo);

            predicates.add(criteriaBuilder.between(flight.get("dateFlight"), localDateFrom, localDate1To));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        Query res = entityManager.createQuery(criteriaQuery);

        List<Flight> result_tmpl = res.getResultList();
        List<Flight> flights = new ArrayList<>();

        // по-другому не получилось. как писал выше - получал задублированные результаты

        if (modelPlane != null ){
            for (Flight f: result_tmpl){
                if(f.getPlane().getModel().equals(modelPlane)){
                    flights.add(f);  }
            }
            return flights;
        }
        return result_tmpl;
    }
}



























