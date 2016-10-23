package hw7.notes.dao;


import hw7.notes.domain.Notebook;
import hw7.notes.exception.PortionException;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Created by s_okhoda on 09.02.2016.
 */
public interface NotebookDao {
    Long create(Notebook ntb);
    hw7.notes.domain.Notebook read(Long id);
    boolean update(Notebook ntb);
    boolean delete(Notebook ntb);
    boolean checkExist(Notebook ntb) throws HibernateException;
    boolean checkExistExceptId(Notebook ntb, Long ntbID) throws
            HibernateException;
    List findAll();
    List getNotebookTypesByPortion(int size, int cnt)throws PortionException,
            HibernateException;



}
