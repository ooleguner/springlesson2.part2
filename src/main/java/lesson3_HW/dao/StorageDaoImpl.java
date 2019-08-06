package lesson3_HW.dao;

import lesson3_HW.AppException.BadRequestException;
import lesson3_HW.beans.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 27.07.2019.
 */

public class StorageDaoImpl implements GeneralDao<Storage> {


    private SessionFactory sessionFactory;
    private List storageList;

    private SessionFactory createSessionFacrory() {
        if (sessionFactory == null) {
            registerDriver();
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    private void registerDriver() {
        String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Storage save (Storage storage)  {
        Transaction transaction = null;
        try (Session session = createSessionFacrory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(storage);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return storage;
    }

    @Override
    public void remove(Long id) throws BadRequestException {
        Transaction transaction = null;
        try(Session session = createSessionFacrory().openSession()){
           transaction= session.getTransaction();
           transaction.begin();
           session.delete(getById(id));
           transaction.commit();
        } catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
                throw new BadRequestException("Wrong. Storage with id " + id + "not delete.");
            }
        }
    }

    @Override
    public Storage getById(Long id) {
        Transaction transaction=null;
       try (Session session = createSessionFacrory().openSession()){
           transaction= session.getTransaction();
           transaction.begin();
           Storage storage= session.get(Storage.class,id);
           transaction.commit();
           return storage;
       }catch (HibernateException msg){
           if (transaction != null){
               transaction.rollback();
           }
       }
       return null;
    }


    @Override
    public Storage update(Storage storage) {
        Transaction transaction = null;
        try (Session session = createSessionFacrory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(storage);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return storage;
    }

    @Override
    public List<Storage> listAll() {
        Transaction transaction=null;
        try(Session session = createSessionFacrory().openSession()){
            transaction=session.getTransaction();
            transaction.begin();
            storageList=(List<Storage>)session.createQuery("FROM Storage").list();
            transaction.commit();
        }catch (HibernateException msg){
            if(transaction != null)
                transaction.rollback();

            System.out.println(msg.getMessage());
        }
        return storageList;
    }
}
