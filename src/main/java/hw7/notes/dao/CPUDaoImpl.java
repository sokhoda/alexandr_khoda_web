package hw7.notes.dao;

import hw7.notes.domain.CPU;
import hw7.notes.exception.PortionException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by s_okhoda on 16.02.2016.
 */
public class CPUDaoImpl implements CPUDao {
    private static Logger log = Logger.getLogger(CPUDao.class);
    private SessionFactory factory;

    public CPUDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Long create(CPU cpu) throws HibernateException {
        Session session = factory.openSession();
        Long id = null;
        try {
            session.beginTransaction();
            id = (Long) session.save(cpu);
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
    public CPU read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.openSession();
        CPU cpu = null;
        try {
            cpu = (CPU) session.get(CPU.class, id);
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
        } finally {
            session.close();
        }
        return cpu;
    }

    @Override
    public boolean update(CPU cpu) {
        if (cpu == null){
            return false;
        }
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(cpu);
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
    public boolean delete(CPU cpu) {
        if (cpu == null){
            return false;
        }
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(cpu);
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
    public boolean checkExist(CPU cpu) throws HibernateException {
        Session session = factory.openSession();
        try{
            Query query = session.createQuery("from CPU cp join cp.vendor v " +
                    " where v.id = :venId and cp.freq = :freq and cp.model = :model")
                    .setParameter("venId", cpu.getVendor().getId())
                    .setParameter("freq", cpu.getFreq())
                    .setParameter("model", cpu.getModel());
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
    public boolean checkExistExceptId(CPU cpu, Long cpuID) throws HibernateException {
        Session session = factory.openSession();
        try{
            Query query = session.createQuery("from CPU cp join cp.vendor v " +
                    " where v.id = :venId and cp.freq = :freq and cp.model = :model and cp.id <> :cpuID")
                    .setParameter("venId", cpu.getVendor().getId())
                    .setParameter("freq", cpu.getFreq())
                    .setParameter("model", cpu.getModel())
                    .setParameter("cpuID", cpuID);
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
            Query query = session.createQuery("from CPU");
            return query.list();
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List getCPUByPortion(int size, int cnt) throws
            PortionException, HibernateException{
        if (size <= 0) {
            throw new PortionException("Negative portion size.");
        }
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from CPU");
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
}
