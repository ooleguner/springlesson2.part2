package lesson3_w3.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by oleg on 08.08.2019.
 */
public class SessionFactoryBuilder {

    static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
      if (sessionFactory != null){
          return sessionFactory;
      }
      sessionFactory= new Configuration().configure().buildSessionFactory();
      return  sessionFactory;
    }

    public static void closeSessionFactory (){
        sessionFactory.close();
    }
}
