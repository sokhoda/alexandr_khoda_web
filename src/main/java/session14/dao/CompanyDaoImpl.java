package session14.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by s_okhoda on 14.02.2016.
 */
public class CompanyDaoImpl implements CompanyDao {
    private SessionFactory factory;

    private static Logger log = Logger.getLogger(CompanyDaoImpl.class);

    public CompanyDaoImpl() {
    }

    public CompanyDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List getEmployees(Long id) {
        Session session = factory.openSession();
        try{
            Query query = session.createQuery("from Employee e join e.comp c  where c.id = :compID ");
            query.setParameter("compID", id);
            return query.list();
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
//            session.getTransaction().rollback();
        return null;
        } finally {
            session.close();
        }
    }
}
