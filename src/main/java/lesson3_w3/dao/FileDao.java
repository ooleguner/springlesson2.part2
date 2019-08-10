package lesson3_w3.dao;

import lesson3_w3.bean.File;
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
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("*** Wrong during save File" + file.toString() + "***");
            System.out.println(e.getMessage());
        }
        return file;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public File update(File object) {
        return null;
    }

    @Override
    public File findById(long id) {
        return null;
    }

    @Override
    public List<File> getAll() {
        Transaction transaction = null;
        List<File> files = null;
        try (Session session = SessionFactoryBuilder.createSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            files = session.createSQLQuery("SELECT * FROM FILES").addEntity(File.class).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return files;
    }
}
