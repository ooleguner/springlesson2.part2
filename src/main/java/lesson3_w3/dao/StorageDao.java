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


    @Override
    public Storage save(Storage storage) {
        Transaction transaction=null;
        try(Session session = SessionFactoryBuilder.createSessionFactory().openSession()){
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
    public void delete(Storage storage) {

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

    @Override
    public int updateList(List<Storage> files) {
        return 0;
    }
}
