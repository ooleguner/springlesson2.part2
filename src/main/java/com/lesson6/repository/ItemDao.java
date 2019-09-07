package com.lesson6.repository;

import com.lesson6.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class ItemDao {

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

    public boolean checkIfItemExist(String name) {
        Query query = entityManager.createNativeQuery("SELECT * FROM ITEM WHERE NAME = :name")
                .setParameter("name", name);
        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }

    public int deleteByName(String name) {
        Query query = entityManager.createNativeQuery("DELETE FROM ITEM WHERE NAME LIKE  :name ")
                .setParameter("name", "%" + name + "%");
        int count = query.executeUpdate();
        System.out.println(count);
        return count;
    }
}

