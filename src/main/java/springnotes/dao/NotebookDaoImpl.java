package springnotes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import springnotes.exception.*;
import springnotes.domain.Notebook;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by s_okhoda on 09.02.2016.
 */
@Repository("notebookDao")
public class NotebookDaoImpl implements NotebookDao {
    private static Logger log = Logger.getLogger(NotebookDao.class);
    @Autowired
    private SessionFactory factory;

    public NotebookDaoImpl() {
    }

    @Override
    public Long create(Notebook ntb) throws HibernateException{
        Session session = factory.getCurrentSession();
        return (Long)session.save(ntb);
    }

    @Override
    @Transactional(readOnly = true)
    public Notebook read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.getCurrentSession();
        return (Notebook) session.get(Notebook.class, id);
    }

    @Override
    public boolean update(Notebook ntb) {
        if (ntb == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.update(ntb);
        return true;
    }

    @Override
    public boolean delete(Notebook ntb) {
        if (ntb == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.delete(ntb);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Notebook");
        return query.list();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkExist(Notebook ntb) throws HibernateException {
        Session session = factory.getCurrentSession();
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

    @Override
    @Transactional(readOnly = true)
    public boolean checkExistExceptId(Notebook ntb, Long ntbID) throws HibernateException {
        Session session = factory.getCurrentSession();
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

    @Override
    @Transactional(readOnly = true)
    public List getNotebookTypesByPortion(int size, int cnt) throws PortionException, HibernateException {
        if (size <= 0) {
            throw new PortionException("Negative portion size.");
        }
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Notebook");
        query.setFirstResult((cnt - 1) * size);
        query.setMaxResults(size);
        return query.list();
    }
}
