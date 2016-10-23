package springnotes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import springnotes.domain.Vendor;
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
@Repository("vendorDao")
public class VendorDaoImpl implements VendorDao {
    private static Logger log = Logger.getLogger(VendorDao.class);
    @Autowired
    private SessionFactory factory;

    public VendorDaoImpl() {
    }

    @Override
    public Long create(Vendor vendor) throws HibernateException {
        Session session = factory.getCurrentSession();
        return (Long)session.save(vendor);
    }

    @Override
    @Transactional (readOnly = true)
    public Vendor read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.getCurrentSession();
        return (Vendor) session.get(Vendor.class, id);
    }

    @Override
    public boolean update(Vendor vendor) {
        if (vendor == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.update(vendor);
        return true;
    }

    @Override
    public boolean delete(Vendor vendor) {
        if (vendor == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.delete(vendor);
        return true;
    }

    @Override
    @Transactional (readOnly = true)
    public boolean checkExist(Vendor vendor) throws HibernateException{
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Vendor v where name = :NAME")
                .setParameter("NAME", vendor.getName());
        return (query.list().size() > 0 ? true : false);
    }

    @Override
    @Transactional (readOnly = true)
    public boolean checkExistExceptId(Vendor vendor, Long venID) throws HibernateException {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Vendor v where name = :NAME and v.id <> :venID")
                .setParameter("NAME", vendor.getName())
                .setParameter("venID", venID);
        return (query.list().size() > 0 ? true : false);
    }

    @Override
    @Transactional (readOnly = true)
    public List findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Vendor v");
        return query.list();
    }
}
