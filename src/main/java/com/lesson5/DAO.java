package com.lesson5;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


@Repository
@Transactional
public class DAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Item save(Item item) {
        entityManager.persist(item);
        return item;
    }

    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    public Item findById(Long id) {
        return entityManager.find(Item.class, id);
    }

    public Item update(Item item) {
        return entityManager.merge(item);
    }

    public boolean checkIfItemExist(String description) {
        Query query = entityManager.createNativeQuery("SELECT * FROM ITEM WHERE DESCRIPTION = :descr")
                      .setParameter("descr", description);
        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }
}

