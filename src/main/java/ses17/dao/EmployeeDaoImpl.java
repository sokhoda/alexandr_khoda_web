package ses17.dao;

import org.hibernate.Query;
import ses17.domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/16/15
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory factory;

    public EmployeeDaoImpl() {
    }

    @Override
    public List<Employee> findAll() {
        Session session = factory.getCurrentSession();
        return session.createCriteria(Employee.class).list();
        //return new ArrayList<Employee>(Arrays.asList(new Employee("Petro"), new Employee("Sidor")));
    }

    @Override
    public Employee getEmployee(String lastName) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Employee e  where e.lastName = " +
                ":LASTNAME")
                .setParameter("LASTNAME", lastName);
        return (Employee)query.uniqueResult();
    }
}
