package hw7.notes.dao;

import hw7.notes.domain.Vendor;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Created by s_okhoda on 16.02.2016.
 */
public interface VendorDao {
    Long create(Vendor vendor);
    Vendor read(Long id);
    boolean update(Vendor vendor);
    boolean delete(Vendor vendor);
    boolean checkExist(Vendor vendor) throws HibernateException;
    boolean checkExistExceptId(Vendor vendor, Long venID) throws
            HibernateException;
    List findAll();
}
