package springnotes.dao;


import springnotes.domain.Sales;

import java.util.List;

/**
 * Created by s_okhoda on 16.02.2016.
 */
public interface SalesDao {
    Long create(Sales sales);
    Sales read(Long id);
    boolean update(Sales sales);
    boolean delete(Sales sales);
    List findAll();
}
