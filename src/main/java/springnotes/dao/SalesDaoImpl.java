package springnotes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import springnotes.domain.Sales;
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
@Repository("salesDao")
public class SalesDaoImpl implements SalesDao {
    private static Logger log = Logger.getLogger(SalesDao.class);
    @Autowired
    private SessionFactory factory;

    public SalesDaoImpl() {
    }

    @Override
    public Long create(Sales sales) throws HibernateException {
        Session session = factory.getCurrentSession();
        return (Long) session.save(sales);
    }

    @Override
    @Transactional (readOnly = true)
    public Sales read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.getCurrentSession();
        return (Sales) session.get(Sales.class, id);
    }

    @Override
    public boolean update(Sales sales) {
        if (sales == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.update(sales);
        return true;
    }

    @Override
    public boolean delete(Sales sales) {
        if (sales == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.delete(sales);
        return true;
    }

    @Override
    @Transactional (readOnly = true)
    public List findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Sales");
        return query.list();
    }
}
