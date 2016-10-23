package hw6.notes;

import hw6.notes.domain.Notebook;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by s_okhoda on 09.02.2016.
 */
public class test {
    private static Logger log = Logger.getLogger(test.class);

    public static SessionFactory getSessionFactory() {
        Locale.setDefault(Locale.ENGLISH);
        Configuration cfg =
                new Configuration().configure("hw7.notes/hibernate.cfg.xml");
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(cfg.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();

        return cfg.buildSessionFactory(standardServiceRegistry);
    }

    public static String checkDate(GregorianCalendar gc) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        if (gc == null) {
            return "null";
        }
        else {
            return format1.format(gc.getTime());
        }
    }

    public static GregorianCalendar String2Gregorian(String dateStr) throws ParseException{
        if (dateStr == null || dateStr.length() == 0){
            return  null;
        }
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        date = df.parse(dateStr);
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return (GregorianCalendar) cal;
    }

    public static void main(String[] args)  {
        Notebook n = new Notebook();
        SessionFactory factory = getSessionFactory();

       char c1 = 064770;
//        char c2 = 'face';
         char c3 = 0xbeef;
//        int myList [] = {4, 3, 7};
//        int myList [] [] = {4,9,7,0};
//        int [] myList = (5,8,2);

        char c4 = '\u0022';
//         char c5 = '\iface';
         char c6 = '\uface';


        Long i;
        Double d,d2;
        Integer f;
        i = new Long(10);
            System.out.println(n.toString());

        System.out.println(checkDate(new GregorianCalendar()));

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;

//        try {
//            date = df.parse("25.12.1991");
//            Calendar cal = new GregorianCalendar(); //Calendar.getInstance();
//            cal.setTime(date);
//            System.out.println(df.format(cal.getTime()));
//        }
//        catch (ParseException e) {
//            e.printStackTrace();
//        }
        System.out.println(Integer.parseInt("33"));
        Session session = factory.openSession();
        try{
//            Query query = session.createQuery("from Vendor where name = :NAME")
//                    .setParameter("NAME","Apple");
//            Vendor vendor = (Vendor) query.uniqueResult();

            Query query = session.createQuery("select n.id, v.name, n.model, to_char(n.manDate, 'dd.mm.yyyy'), CONCAT(cpuv.name, ' ', c.model, ' ', c.freq), CONCAT(memv.name, ' ', m.sizze),   sum(s.quantity) as qua from Notebook n   join n .stores s " +
                    " join n.cpu c "+
                    " join c.vendor cpuv "+
                    " join n.memory m "+
                    " join m.vendor memv "+
                    " join n.vendor v "+
                    "group by n.id, v.name, n.model, n.manDate, cpuv.name, c.model, c.freq, memv.name, m.sizze" +
                    " ORDER BY  qua desc");
//                    .setParameter("NAME","Apple");
            List list = query.list();
            System.out.println("RES:");
            printList(list);

        }
        catch (HibernateException e){
            log.error("Transaction failed", e);
        }
        finally {
            session.close();
            factory.close();
        }


    }

    public static void printList(List<Object[]> list) {
        if (list.size() == 0) {
            System.out.println("List is empty");
            return;
        }
        for (Object[] obj : list) {
            System.out.println(obj[0]);
            System.out.println(Arrays.toString(obj));
//            for (Object ob : obj) {
//                System.out.println(ob);
//            }
        }
    }

    public static boolean loginCheck(String firstName, String lastName,
                                     SessionFactory factory) {
        Session session = factory.openSession();
        try{
            Query query = session.createQuery("from Vendor v where FIRSTNAME = :FIRSTNAME and LASTNAME = :LASTNAME");
            query.setParameter("FIRSTNAME", firstName);
            query.setParameter("LASTNAME", lastName);
            return (query.uniqueResult() != null ? true : false);
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
//        session.getTransaction().rollback();
            return false;

        } finally {
            session.close();
        }

    }
}
