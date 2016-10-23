package ses17.dao;


import ses17.domain.Employee;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/16/15
 */
public interface EmployeeDao {
    List<Employee> findAll();
    public Employee getEmployee(String lastName);
}
