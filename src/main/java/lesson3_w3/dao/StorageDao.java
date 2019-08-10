package lesson3_w3.dao;

import lesson3_w3.bean.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


/**
 * Created by oleg on 08.08.2019.
 */
public class StorageDao implements GeneralDao<Storage> {

     SessionFactory sessionFactory;

    public  SessionFactory createSessionFactory() {
        if (sessionFactory != null){
            return sessionFactory;
        }
        sessionFactory= new Configuration().configure().buildSessionFactory();
        return  sessionFactory;
    }

    public  void closeSessionFactory (){
        sessionFactory.close();
    }


    @Override
    public Storage save(Storage storage) {
        System.out.println("1");
        Transaction transaction=null;
        System.out.println("2");
        try(Session session = createSessionFactory().openSession()){
            System.out.println(session.toString());
            transaction=session.beginTransaction();
            session.save(storage);
            transaction.commit();
            System.out.println("Storage " + storage.toString() + "saved in DB");
        }
        catch (HibernateException e){
            if (transaction!=null){transaction.rollback();
                System.out.println("*** Wrong . Storage " + storage.toString() + " not saved.");
                System.out.println(e.getMessage());}
        }
        return storage;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Storage update(Storage object) {
        return null;
    }

    @Override
    public Storage findById(long id) {
        Transaction transaction=null;
        Storage storage = null;
        try(Session session = SessionFactoryBuilder.createSessionFactory().openSession()){
            transaction = session.beginTransaction();
            storage = session.get(Storage.class, id);
            transaction.commit();
        }
        catch (HibernateException e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return storage;
    }

    @Override
    public List<Storage> getAll() {
            Transaction transaction=null;
            List storages=null;
            try(Session session = SessionFactoryBuilder.createSessionFactory().openSession()){
                transaction=session.beginTransaction();
                storages = session.createSQLQuery("SELECT * FROM STORAGES").addEntity(Storage.class).list();
                transaction.commit();
            }
            catch (HibernateException e){
                if (transaction!=null){transaction.rollback();
            }
        }
        return storages;
    }
}
