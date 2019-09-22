package com.lesson6_1.repository;

import com.lesson6_1.model.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PlaneRepository implements RepositoryInterface<Plane> {

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
        Query q = entityManager.createNativeQuery("SELECT * FROM PLANE  WHERE MODEL = :model AND CODE = :code ");
        q.setParameter("model", plane.getModel());
        q.setParameter("code", plane.getCode());

        List<Plane> listPlane = (List<Plane>) q.getResultList();
        if (listPlane == null || listPlane.isEmpty()) {
            return false;
        }
        return true;
    }


}
