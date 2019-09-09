package com.lesson6_1.repository;

import com.lesson6_1.model.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PlaneRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Plane savePlane(Plane plane){
       entityManager.persist(plane);
       return plane;
    }
}
