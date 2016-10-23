package springnotes.dao;

import org.springframework.transaction.annotation.Transactional;
import springnotes.exception.PortionException;
import org.springframework.beans.factory.annotation.Autowired;
import springnotes.domain.CPU;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by s_okhoda on 16.02.2016.
 */
@Repository("cpuDao")
public class CPUDaoImpl implements CPUDao {
    private static Logger log = Logger.getLogger(CPUDao.class);

    @Autowired
    private SessionFactory factory;

    public CPUDaoImpl() {

    }

    @Override
    public Long create(CPU cpu) throws HibernateException {
        Session session = factory.getCurrentSession();
        return (Long) session.save(cpu);
    }

    @Override
    @Transactional(readOnly = true)
    public CPU read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.getCurrentSession();
        return (CPU) session.get(CPU.class, id);
    }

    @Override
    public boolean update(CPU cpu) {
        if (cpu == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.update(cpu);
        return true;
    }

    @Override
    public boolean delete(CPU cpu) {
        if (cpu == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.delete(cpu);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkExist(CPU cpu) throws HibernateException {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from CPU cp join cp.vendor v " +
                " where v.id = :venId and cp.freq = :freq and cp.model = :model")
                .setParameter("venId", cpu.getVendor().getId())
                .setParameter("freq", cpu.getFreq())
                .setParameter("model", cpu.getModel());
        return (query.list().size() > 0 ? true : false);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkExistExceptId(CPU cpu, Long cpuID) throws HibernateException {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from CPU cp join cp.vendor v " +
                " where v.id = :venId and cp.freq = :freq and cp.model = :model and cp.id <> :cpuID")
                .setParameter("venId", cpu.getVendor().getId())
                .setParameter("freq", cpu.getFreq())
                .setParameter("model", cpu.getModel())
                .setParameter("cpuID", cpuID);
        return (query.list().size() > 0 ? true : false);
    }

    @Override
    @Transactional(readOnly = true)
    public List findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from CPU");
        return query.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List getCPUByPortion(int size, int cnt) throws
            PortionException, HibernateException{
        if (size <= 0) {
            throw new PortionException("Negative portion size.");
        }
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from CPU");
        query.setFirstResult((cnt - 1) * size);
        query.setMaxResults(size);
        return query.list();
    }
}
