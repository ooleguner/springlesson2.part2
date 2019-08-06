package lesson3_HW.dao;

import lesson3_HW.AppException.BadRequestException;
import lesson3_HW.beans.File;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

/**
 * Created by oleg on 27.07.2019.
 */
public class FileDaoImpl implements GeneralDao<File> {

    private SessionFactory sessionFactory;
    private List fileList;

    private void registerDriver() {
        String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private SessionFactory createSessionFacrory() {
        if (sessionFactory == null) {
            registerDriver();
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }


    @Override
    public File save (File file)  {
        Transaction transaction = null;
        try (Session session = createSessionFacrory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(file);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return file;
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
    public File getById(Long id) {
        Transaction transaction=null;
        try (Session session = createSessionFacrory().openSession()){
            transaction= session.getTransaction();
            transaction.begin();
            File file= session.get(File.class,id);
            transaction.commit();
            return file;
        }catch (HibernateException msg){
            if (transaction != null){
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public File update(File file) {
        Transaction transaction = null;
        try (Session session = createSessionFacrory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(file);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return file;
    }

    @Override
    public List<File> listAll() {
        Transaction transaction=null;
        try(Session session = createSessionFacrory().openSession()){
            transaction=session.getTransaction();
            transaction.begin();
            fileList=(List<File>)session.createQuery("FROM File").list();
            transaction.commit();
        }catch (HibernateException msg){
            if(transaction != null)
                transaction.rollback();

            System.out.println(msg.getMessage());
        }
        return fileList;
    }
}
