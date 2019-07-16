package repository;

import model.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;


/**
 * Created by oleg on 12.06.2019.
 */
public class ItemDao implements DAOInterface {

    public ItemDao() {
    }

    ArrayList<Item> items = null;
    Item item = null;

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
}

