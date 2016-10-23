package session14;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import session14.domain.Company;
import session14.domain.Employee;

import java.util.*;

/**
 * Created by s_okhoda on 14.02.2016.
 */
public class Task3 {


    private static Logger log = Logger.getLogger(Task3.class);

    public static SessionFactory getSessionFactory() {
        Locale.setDefault(Locale.ENGLISH);
        Configuration cfg =
                new Configuration().configure("session14/hibernate.cfg.xml");
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(cfg.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();

        return cfg.buildSessionFactory(standardServiceRegistry);
    }


    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<Employee>();


        SessionFactory factory = getSessionFactory();
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from Company");

            List clist = query.list();
            if (clist == null){
                System.out.println("Company list is null. Exiting application" +
                        ".");
                return;
            }
            System.out.println("\nCompanies:");
            for (int i = 0; i < clist.size(); i++) {
                System.out.println(clist.get(i).toString());
            }
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter company id:");
            Long cid = scan.nextLong();

            Company comp1 = (Company)session.get(Company.class, cid);


            System.out.println(comp1.getId());
            System.out.println(comp1.getName());
            System.out.println(comp1.getBalance());

            System.out.println("Please enter new company balance:");
            Double bal = scan.nextDouble();
            session.beginTransaction();
            comp1.setBalance(bal);
            session.save(comp1);
            session.getTransaction().commit();

            System.out.println("New balance:" + comp1.getBalance());

            session.beginTransaction();

            for (Iterator<Employee> it = comp1.getEmployee().iterator(); it.hasNext();){
                Employee e = it.next();
                session.delete(e);
                it.remove();
            }

            employees.clear();
            session.save(comp1);
            session.getTransaction().commit();

                session.beginTransaction();
//            String [] firstNames ={"Alex", "Yulia", "Pavel"};
//            String [] lastNames ={"Korolenko", "Buz", "Korop"};
            String [] firstNames ={"Petro", "Vitaliy", "Mykola"};
            String [] lastNames ={"Ivaniuk", "Palut", "Tira"};
            for (int i = 0; i < 3; i++) {
                Employee e1 = new Employee(comp1, firstNames[i], lastNames[i]);
                    employees.add(e1);
                    comp1.getEmployee().add(e1);
                    session.save(employees.get(i));
            }

                session.save(comp1);
                session.getTransaction().commit();

            session.beginTransaction();
            for (Iterator<Employee> it = comp1.getEmployee().iterator(); it.hasNext();){
                Employee e = it.next();
                e.setFirstName(e.getFirstName() + "_1");
            }

            session.save(comp1);
            session.getTransaction().commit();

            System.out.println("Company Employees:\n" + comp1.getEmployee());
//                System.out.println(employees.get(i).toString1());
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
            session.getTransaction().rollback();

        } finally {
            session.close();
            factory.close();
        }


    }
}
