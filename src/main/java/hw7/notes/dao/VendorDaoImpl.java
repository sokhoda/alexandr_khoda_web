package hw7.notes.dao;

import hw7.notes.domain.Vendor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by s_okhoda on 16.02.2016.
 */
public class VendorDaoImpl implements VendorDao {
    private static Logger log = Logger.getLogger(VendorDaoImpl.class);
    private SessionFactory factory;

    public VendorDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public VendorDaoImpl() {
    }

    @Override
    public Long create(Vendor vendor) throws HibernateException {
        Session session = factory.openSession();
        Long id = null;
        try {
            session.beginTransaction();
            id = (Long)session.save(vendor);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
            session.getTransaction().rollback();
            throw new HibernateException(e.getMessage() + ", " + e.getCause()
                    .getMessage());
        } finally {
            session.close();
        }
        return id;
    }

    @Override
    public Vendor read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.openSession();
        Vendor vendor = null;
        try {
            vendor = (Vendor) session.get(Vendor.class, id);
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
        } finally {
            session.close();
        }
        return vendor;
    }

    @Override
    public boolean update(Vendor vendor) {
        if (vendor == null){
            return false;
        }
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(vendor);
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException e) {
            log.error("Transaction failed", e);
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Vendor vendor) {
        if (vendor == null){
            return false;
        }
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(vendor);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public boolean checkExist(Vendor vendor) throws HibernateException{
        Session session = factory.openSession();
        try{
            Query query = session.createQuery("from Vendor v where name = :NAME")
                    .setParameter("NAME", vendor.getName());
            return (query.list().size() > 0 ? true : false);
        }
        catch (HibernateException e){
            log.error("Transaction failed", e);
            throw new HibernateException(e.getMessage());
        }
        finally {
            session.close();
        }
    }

    @Override
    public boolean checkExistExceptId(Vendor vendor, Long venID) throws HibernateException {
        Session session = factory.openSession();
        try{
            Query query = session.createQuery("from Vendor v where name = :NAME and v.id <> :venID")
                    .setParameter("NAME", vendor.getName())
                    .setParameter("venID", venID);
            return (query.list().size() > 0 ? true : false);
        }
        catch (HibernateException e){
            log.error("Transaction failed", e);
            throw new HibernateException(e.getMessage());
        }
        finally {
            session.close();
        }
    }

    @Override
    public List findAll() {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from Vendor v");
            return query.list();
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
            return null;
        } finally {
            session.close();
        }
    }
}
