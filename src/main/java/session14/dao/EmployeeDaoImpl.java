package session14.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Locale;

/**
 * Created by s_okhoda on 14.02.2016.
 */
public class EmployeeDaoImpl implements EmployeeDao{
    private SessionFactory factory;
    private static Logger log = Logger.getLogger(EmployeeDaoImpl.class);

    public EmployeeDaoImpl( SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean loginCheck(String firstName, String lastName) {
        Session session = factory.openSession();
        try{
            Query query = session.createQuery("from Employee where FIRSTNAME = :FIRSTNAME and LASTNAME = :LASTNAME");
            query.setParameter("FIRSTNAME", firstName);
            query.setParameter("LASTNAME", lastName);
            return (query.uniqueResult() != null ? true : false);
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
//        session.getTransaction().rollback();
            return false;

        } finally {
            session.close();
        }

    }



    public SessionFactory getSessionFactory() {
        Locale.setDefault(Locale.ENGLISH);
        Configuration cfg =
                new Configuration().configure("session14/hibernate.cfg.xml");
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(cfg.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();

        return cfg.buildSessionFactory(standardServiceRegistry);
    }

}
