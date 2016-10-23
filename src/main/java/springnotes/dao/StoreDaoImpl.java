package springnotes.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springnotes.domain.Store;

import java.util.List;

/**
 * Created by s_okhoda on 16.02.2016.
 */
@Repository("storeDao")
public class StoreDaoImpl implements StoreDao {
    private static Logger log = Logger.getLogger(StoreDao.class);
    @Autowired
    private SessionFactory factory;

    public StoreDaoImpl() {
    }

    @Override
    public Long create(Store store) throws HibernateException {
        Session session = factory.getCurrentSession();
        return (Long) session.save(store);
    }

    @Override
    @Transactional (readOnly = true)
    public Store read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.getCurrentSession();
        return (Store) session.get(Store.class, id);
    }

    @Override
    public boolean update(Store store) {
        if (store == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.update(store);
        return true;
    }

    @Override
    public boolean delete(Store store) {
        if (store == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.delete(store);
        return true;
    }


    @Override
    @Transactional (readOnly = true)
    public List findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Store");
        return query.list();
    }
}
