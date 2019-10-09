package com.lesson6_1.repository;

import com.lesson6_1.model.Plane;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


public class PlaneRepository implements RepositoryInterface<Plane> {

    //самолеты старше 20 лет
    private String oldPlanes = "SELECT * FROM  PLANE WHERE YEAR_PRODUCED < ADD_MONTHS(TRUNC(SYSDATE,'yyyy'), -240) ";

    //самолеты, которые с больше 300 полетов за год
    private String regularPlane = "SELECT * FROM (SELECT COUNT(*) AS COUNT, PLANE_ID AS PLANE_ID , PLANE.MODEL AS MODEL FROM FLIGHT " +
            "JOIN PLANE ON FLIGHT.PLANE_ID = PLANE.ID  " +
            "WHERE EXTRACT(YEAR FROM DATEFLIGHT) = ? " +
            "GROUP BY PLANE_ID, MODEL) WHERE COUNT >=300 ";


    private String checkIfPresent = "SELECT * FROM PLANE  WHERE MODEL = :MODEL AND CODE = :CODE ";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Plane save(Plane plane) {
        entityManager.persist(plane);
        return plane;
    }

    @Override
    public Plane findById(Long id) {
        return entityManager.find(Plane.class, id);
    }

    @Override
    public Plane update(Plane plane) {
        return entityManager.merge(plane);
    }

    @Override
    public void delete(Plane plane) {
        entityManager.remove(plane);
    }

    @Override
    public boolean checkIfPresent(Plane plane) {
        Query q = entityManager.createNativeQuery(checkIfPresent);
        q.setParameter("MODEL", plane.getModel());
        q.setParameter("CODE", plane.getCode());

        List<Plane> listPlane = (List<Plane>) q.getResultList();
        if (listPlane == null || listPlane.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<Object[]> getRegularPlanes(int year) {
        Query q = entityManager.createNativeQuery(regularPlane);
        q.setParameter(1, year);
        return q.getResultList();
    }

    public List<Plane> oldPlanes() {

        Query q = entityManager.createNativeQuery(oldPlanes, Plane.class);

        List<Plane> oldPlanes = (List<Plane>) q.getResultList();
        return oldPlanes;
    }
}
