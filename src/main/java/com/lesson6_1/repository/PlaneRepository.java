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
}
