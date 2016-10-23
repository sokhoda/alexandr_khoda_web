package session14.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Locale;

/**
 * Created by s_okhoda on 14.02.2016.
 */
public interface EmployeeDao {

    boolean loginCheck(String firstName, String lastName);
}
