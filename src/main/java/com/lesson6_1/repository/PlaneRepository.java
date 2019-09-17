package com.lesson6_1.repository;

import com.lesson6_1.model.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PlaneRepository implements RepositoryInterface<Plane>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Plane save(Plane plane) {
        entityManager.persist(plane);
        return plane;
    }

    @Override
    public Plane findById(Long id) {
        return entityManager.find(Plane.class,id);
    }

    @Override
    public Plane update(Plane plane) {
        return entityManager.merge(plane);
    }
    @Override
    public void delete(Plane plane) {
        entityManager.remove(plane);
    }
}
