package session14.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import session14.dao.CompanyDao;
import session14.dao.CompanyDaoImpl;
import session14.dao.EmployeeDao;
import session14.dao.EmployeeDaoImpl;

import java.util.List;
import java.util.Locale;

/**
 * Created by s_okhoda on 14.02.2016.
 */
public class GeneralServiceImpl implements GeneralService{
    private SessionFactory factory;
    private EmployeeDao empDao;
    private  CompanyDao companyDao;

    private static Logger log = Logger.getLogger(GeneralServiceImpl.class);

    public GeneralServiceImpl() {
        factory = getSessionFactory();
        empDao = new EmployeeDaoImpl(factory);
        companyDao = new CompanyDaoImpl(factory);
    }

    @Override
    public List getEmployees(Long id) {
        return companyDao.getEmployees(id);
    }

    @Override
    public boolean loginCheck(String firstName, String lastName) {
       return empDao.loginCheck(firstName, lastName);
    }


    public SessionFactory getSessionFactory() {
        Locale.setDefault(Locale.ENGLISH);
        Configuration cfg =
                new Configuration().configure("session14/hibernate.cfg.xml");
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(cfg.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();

        return cfg.buildSessionFactory(standardServiceRegistry);
    }


    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }


}
