package com.lesson6.repository;

import com.lesson6.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


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

    public boolean checkIfItemExist(String description) {
        Query query = entityManager.createNativeQuery("SELECT * FROM ITEM WHERE DESCRIPTION = :descr")
                .setParameter("descr", description);
        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }


/*
    public ArrayList<Item> getAllItems() throws HibernateException {
        Transaction tr = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            items = (ArrayList<Item>) session.createQuery("From Item ").list();
            tr.commit();
            return items;
        } catch (HibernateException e) {
            throw e;
        }
    }

    public Item getItemById(long id) throws HibernateException {
        Transaction tr = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            item = (Item) session.get(Item.class, id);
            tr.commit();
            return item;
        } catch (HibernateException e) {
            throw  e;
        }
    }

    public void deleteItem(long id) throws HibernateException {
        Transaction tr = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.delete(getItemById(id));
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        }
    }

    public Item saveItem(Item item) throws HibernateException {
        Transaction tr = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.save(item);
            tr.commit();
            return item;
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
            throw  e;
        }
    }

    public Item updateItem(Item item) throws HibernateException{
        Transaction tr = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.update(item);
            tr.commit();
            return item;
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        }
    }
*/
}

