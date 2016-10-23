package hw6.notes.dao;

import hw6.notes.domain.Notebook;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by s_okhoda on 09.02.2016.
 */
public class NotebookDaoImpl implements NotebookDao {
    private static Logger log = Logger.getLogger(NotebookDaoImpl.class);
    private SessionFactory factory;

    public NotebookDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Long create(Notebook ntb) throws HibernateException{
        Session session = factory.openSession();
        Long id = null;
        try {
            session.beginTransaction();
            id = (Long)session.save(ntb);
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
    public Notebook read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.openSession();
        Notebook notebook = null;
        try {
            notebook = (Notebook) session.get(Notebook.class, id);
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
        } finally {
            session.close();
        }
        return notebook;
    }

    @Override
    public boolean update(Notebook ntb) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
//            Query query = session.createQuery("UPDATE Notebook n set SERIAL = :SERIAL, VENDOR = :VENDOR" +
//                    ", MODEL = :MODEL, MANDATE = :MANDATE, PRICE = :PRICE where ID = :ID");
//
//            query.setParameter("ID", ntb.getId());
//            query.setParameter("SERIAL", ntb.getSerial());
//            query.setParameter("VENDOR", ntb.getVendor());
//            query.setParameter("MODEL", ntb.getModel());
//            query.setParameter("MANDATE", ntb.getManDate());
//            query.setParameter("PRICE", ntb.getPrice());
//            intRes = query.executeUpdate();
            session.update(ntb);
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
    public boolean delete(Notebook ntb) {
        if (ntb == null){
            return false;
        }
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(ntb);
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
    public boolean delete(Long id) {
        Notebook ntb;
        if ((ntb = read(id)) != null){
            return delete(ntb);
        }
        else {
            return false;
        }
    }

    @Override
    public List findAll() {
        Session session = factory.openSession();
        try {
           Query query = session.createQuery("from Notebook");
           return query.list();
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean changePrice(Long id, double price) {
        Session session = factory.openSession();
        Notebook ntb;
        if((ntb = read(id)) != null) {
            ntb.setPrice(price);
            return update(ntb);
        }
        else {
            return false;
        }
    }

    @Override
    public boolean changeSerialVendor(Long id, String serial, String vendor) {
        Session session = factory.openSession();
        int intRes;
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Notebook n set SERIAL = :SERIAL, VENDOR = :VENDOR" +
                    " where ID = :ID");
            query.setParameter("ID", id);
            query.setParameter("SERIAL", serial);
            query.setParameter("VENDOR", vendor);
            intRes = query.executeUpdate();
            session.getTransaction().commit();
            return (intRes > 0 ? true : false) ;
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
    public boolean deleteByModel(String model) {
        Session session = factory.openSession();
        int intRes;
        try {
            session.beginTransaction();
            Query query = session.createQuery("DELETE Notebook n where MODEL = :MODEL");
            query.setParameter("MODEL", model);
            intRes = query.executeUpdate();
            session.getTransaction().commit();
            return (intRes > 0 ? true : false);
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
    public List findByVendor(String vendor) {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from Notebook where VENDOR = :VENDOR");
            query.setParameter("VENDOR", vendor);
            return query.list();
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List findByPriceManufDate(Double price, Date date) {
        Session session = factory.openSession();
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Query query = session.createQuery("from Notebook where PRICE = " +
                    ":PRICE and DATE = :DATE");
            query.setParameter("PRICE", price);
            query.setParameter("DATE", date);
            return query.list();
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List findBetweenPriceLtDateByVendor(Double priceFrom, Double priceTo, Date date, String vendor) {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from Notebook where PRICE >= " +
                    ":PRICEFROM and PRICE < : PRICETO and DATE = :DATE and VENDOR = :VENDOR");
            query.setParameter("PRICEFROM", priceFrom);
            query.setParameter("PRICETO", priceTo);
            query.setParameter("DATE", date);
            query.setParameter("VENDOR", vendor);
            return query.list();
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
            return null;
        } finally {
            session.close();
        }
    }


}
