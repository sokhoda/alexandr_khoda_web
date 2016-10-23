package hw7.notes.dao;

import hw7.notes.domain.Notebook;
import hw7.notes.exception.PortionException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by s_okhoda on 09.02.2016.
 */
public class NotebookDaoImpl implements NotebookDao {
    private static Logger log = Logger.getLogger(NotebookDao.class);
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
        if (ntb == null){
            return false;
        }
        Session session = factory.openSession();
        try {
            session.beginTransaction();
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
    public boolean checkExist(Notebook ntb) throws HibernateException {
        Session session = factory.openSession();
        try{
            Query query = session.createQuery("from Notebook nt join nt.vendor v " +
                    "join nt.cpu c join nt.memory m where v.id = :vendorId and" +
                    " nt.model = :model and nt.manDate = :manDate and c.id = :cpuId and m.id = :memoryId" )
                    .setParameter("vendorId", ntb.getVendor().getId())
                    .setParameter("model", ntb.getModel())
                    .setParameter("manDate", ntb.getManDate())
                    .setParameter("cpuId", ntb.getCpu().getId())
                    .setParameter("memoryId", ntb.getMemory().getId());
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
    public boolean checkExistExceptId(Notebook ntb, Long ntbID) throws HibernateException {
        Session session = factory.openSession();
        try{
            Query query = session.createQuery("from Notebook nt join nt.vendor v " +
                    "join nt.cpu c join nt.memory m where v.id = :vendorId and" +
                    " nt.model = :model and nt.manDate = :manDate and c.id = :cpuId and m.id = :memoryId"  +
                    " and nt.id <> :ntbID" )
                    .setParameter("vendorId", ntb.getVendor().getId())
                    .setParameter("model", ntb.getModel())
                    .setParameter("manDate", ntb.getManDate())
                    .setParameter("cpuId", ntb.getCpu().getId())
                    .setParameter("memoryId", ntb.getMemory().getId())
                    .setParameter("ntbID", ntbID);
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
    public List getNotebookTypesByPortion(int size, int cnt) throws PortionException, HibernateException {
        if (size <= 0) {
            throw new PortionException("Negative portion size.");
        }
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from Notebook");
            query.setFirstResult((cnt - 1) * size);
            query.setMaxResults(size);
            return query.list();
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
            throw new HibernateException(e.getMessage());
        } finally {
            session.close();
        }
    }



    //    @Override
//    public boolean changePrice(Long id, double price) {
//        Session session = factory.openSession();
//        Notebook ntb;
//        if((ntb = read(id)) != null) {
//            ntb.setPrice(price);
//            return update(ntb);
//        }
//        else {
//            return false;
//        }
//    }
//
//    @Override
//    public boolean changeSerialVendor(Long id, String serial, String vendor) {
//        Session session = factory.openSession();
//        int intRes;
//        try {
//            session.beginTransaction();
//            Query query = session.createQuery("UPDATE Notebook n set SERIAL = :SERIAL, VENDOR = :VENDOR" +
//                    " where ID = :ID");
//            query.setParameter("ID", id);
//            query.setParameter("SERIAL", serial);
//            query.setParameter("VENDOR", vendor);
//            intRes = query.executeUpdate();
//            session.getTransaction().commit();
//            return (intRes > 0 ? true : false) ;
//        }
//        catch (HibernateException e) {
//            log.error("Transaction failed", e);
//            session.getTransaction().rollback();
//            return false;
//        } finally {
//            session.close();
//        }
//    }
//
//    @Override
//    public boolean deleteByModel(String model) {
//        Session session = factory.openSession();
//        int intRes;
//        try {
//            session.beginTransaction();
//            Query query = session.createQuery("DELETE Notebook n where MODEL = :MODEL");
//            query.setParameter("MODEL", model);
//            intRes = query.executeUpdate();
//            session.getTransaction().commit();
//            return (intRes > 0 ? true : false);
//        }
//        catch (HibernateException e) {
//            log.error("Transaction failed", e);
//            session.getTransaction().rollback();
//            return false;
//        } finally {
//            session.close();
//        }
//    }
//
//    @Override
//    public List findByVendor(String vendor) {
//        Session session = factory.openSession();
//        try {
//            Query query = session.createQuery("from Notebook where VENDOR = :VENDOR");
//            query.setParameter("VENDOR", vendor);
//            return query.list();
//        } catch (HibernateException e) {
//            log.error("Transaction failed", e);
//            return null;
//        } finally {
//            session.close();
//        }
//    }
//
//    @Override
//    public List findByPriceManufDate(Double price, Date date) {
//        Session session = factory.openSession();
//            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//        try {
//            Query query = session.createQuery("from Notebook where PRICE = " +
//                    ":PRICE and DATE = :DATE");
//            query.setParameter("PRICE", price);
//            query.setParameter("DATE", date);
//            return query.list();
//        } catch (HibernateException e) {
//            log.error("Transaction failed", e);
//            return null;
//        } finally {
//            session.close();
//        }
//    }
//
//    @Override
//    public List findBetweenPriceLtDateByVendor(Double priceFrom, Double priceTo, Date date, String vendor) {
//        Session session = factory.openSession();
//        try {
//            Query query = session.createQuery("from Notebook where PRICE >= " +
//                    ":PRICEFROM and PRICE < : PRICETO and DATE = :DATE and VENDOR = :VENDOR");
//            query.setParameter("PRICEFROM", priceFrom);
//            query.setParameter("PRICETO", priceTo);
//            query.setParameter("DATE", date);
//            query.setParameter("VENDOR", vendor);
//            return query.list();
//        } catch (HibernateException e) {
//            log.error("Transaction failed", e);
//            return null;
//        } finally {
//            session.close();
//        }
//    }


}
