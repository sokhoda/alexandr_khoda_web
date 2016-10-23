package springnotes.dao;

import springnotes.exception.PortionException;
import springnotes.domain.Memory;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Created by s_okhoda on 16.02.2016.
 */
public interface MemoryDao {
    Long create(Memory memory);
    Memory read(Long id);
    boolean update(Memory memory);
    boolean delete(Memory memory);
    boolean checkExist(Memory memory) throws HibernateException;
    boolean checkExistExceptId(Memory memory, Long memoryID) throws
            HibernateException;
    List findAll();
    List getMemoryByPortion(int size, int cnt)throws PortionException, HibernateException;
}
