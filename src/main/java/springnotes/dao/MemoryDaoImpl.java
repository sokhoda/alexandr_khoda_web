package springnotes.dao;

import org.springframework.transaction.annotation.Transactional;
import springnotes.exception.PortionException;
import org.springframework.beans.factory.annotation.Autowired;
import springnotes.domain.Memory;
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
@Repository("memoryDao")
public class MemoryDaoImpl implements MemoryDao {
    private static Logger log = Logger.getLogger(MemoryDao.class);
    @Autowired
    private SessionFactory factory;

    public MemoryDaoImpl() {
    }

    @Override
    public Long create(Memory memory) throws HibernateException {
        Session session = factory.getCurrentSession();
        return  (Long) session.save(memory);
    }

    @Override
    @Transactional(readOnly = true)
    public Memory read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.getCurrentSession();
        return  (Memory) session.get(Memory.class, id);
    }

    @Override
    public boolean update(Memory memory) {
        if (memory == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.update(memory);
        return true;
    }

    @Override
    public boolean delete(Memory memory) {
        if (memory == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.delete(memory);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkExist(Memory memory) throws HibernateException {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Memory m join m.vendor v where v.id = :vendorId and " +
                " m.sizze = :sizze")
                .setParameter("vendorId", memory.getVendor().getId())
                .setParameter("sizze", memory.getSizze());
        return (query.list().size() > 0 ? true : false);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkExistExceptId(Memory memory, Long memoryID) throws HibernateException {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Memory m join m.vendor v where v.id = :vendorId and " +
                " m.sizze = :sizze and m.id <> :memoryID")
                .setParameter("vendorId", memory.getVendor().getId())
                .setParameter("sizze", memory.getSizze())
                .setParameter("memoryID", memoryID);
        return (query.list().size() > 0 ? true : false);
    }

    @Override
    @Transactional(readOnly = true)
    public List findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Memory");
        return query.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List getMemoryByPortion(int size, int cnt) throws
            PortionException, HibernateException {
        if (size <= 0) {
            throw new PortionException("Negative portion size.");
        }
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Memory");
        query.setFirstResult((cnt - 1) * size);
        query.setMaxResults(size);
        return query.list();
    }
}
