package lesson3_w3.dao;

import lesson3_w3.bean.File;
import lesson3_w3.exceptions.ObjectPersistException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by oleg on 08.08.2019.
 */
public class FileDao implements GeneralDao<File> {

    @Override
    public File save(File file) {
        Transaction transaction = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(file);
            transaction.commit();
            System.out.println("File " + file.toString() + "save in DB");
        } catch (HibernateException e) {
            System.out.println("*** Wrong during save File" + file.toString() + "***");
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return file;
    }

    @Override
    public void delete(File file) {
        Transaction transaction = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(file);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Delete() Exception. File with id : " + file.getId() + "  was not deleted. ");

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public File update(File file) {
        Transaction transaction = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(file);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Update() Exception. File with  id " + file.getId() + " was not updated. ");
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return file;
    }

    public int updateList(List<File> files) {
        Transaction transaction = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for (File file : files) {
                session.update(file);
            }
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("TransferAll() Exception. Files was not transfered. ");
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return files.size();
    }


    @Override
    public File findById(long id) throws ObjectPersistException {
        Transaction transaction = null;
        File file = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            file = session.get(File.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        if (file != null) {
            return file;
        }
        throw new ObjectPersistException("File with id: " + id + " not presrnt in System");
    }

    @Override
    public List<File> getAll() {
        Transaction transaction = null;
        List<File> files = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            files = session.createSQLQuery("SELECT * FROM FILES")
                    .addEntity(File.class)
                    .list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return files;
    }


}
