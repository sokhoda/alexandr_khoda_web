package springnotes.view;

import hw7.notes.service.NotebookService;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by s_okhoda on 09.02.2016.
 */
@WebServlet("/x")
public class Servlet extends HttpServlet {
    public static final String NameSurname = " All rights reserved, Alexandr " +
            "Khodakovskyi, Kyiv 2016";
    public static List<String> sizze = Arrays.asList("512M", "1G", "2G", "4G", "8G", "16G");
    private NotebookService service;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException {
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {

    }

    public SessionFactory getSessionFactory() {
        Locale.setDefault(Locale.ENGLISH);
        Configuration cfg =
                new Configuration().configure("hw7.notes/hibernate.cfg.xml");
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(cfg.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();

        return cfg.buildSessionFactory(standardServiceRegistry);
    }

    public static void setMessageAttr(HttpServletRequest req, String color, String
            message){
        req.setAttribute("messageColor", color);
        req.setAttribute("messageText", message );
    }


    public static String[] getAttribArray(HttpServletRequest req){
        String[] arr = new String[2];
        arr[0] = getAttribValue(req,"messageColor");
        arr[1] = getAttribValue(req, "messageText");
        return  arr;
    }

    public static String getAttribValue(HttpServletRequest req, String name){
        if (name == null){
            return "";
        }
        return (req.getAttribute(name) == null ? "" : (String)req.getAttribute(name));
    }

    public static Date String2Date(String dateStr)
            throws
            ParseException {
        if (dateStr == null || dateStr.length() == 0){
            return  null;
        }
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = df.parse(dateStr);
        return date;
    }
    public static boolean checkStringPar(HttpServletRequest req, String parName){
        String par = req.getParameter(parName);
        if (par.trim().length() == 0){
            setMessageAttr(req, "red", "'" + parName + "' can not have ZERO length.");
            return true;
        }
        return false;
    }

    public static <T extends Double> boolean checkLsZero(HttpServletRequest req,
                                                      T number){
        if (number.compareTo(0.0) < 0){
            setMessageAttr(req, "red", "Value can not be negative.");
            return true;
        }
        else {
            return false;
        }
    }

    public static <T extends Double> boolean checkLsEqZero(HttpServletRequest
                                                                 req, T number){
        if (number.compareTo(0.0) <= 0){
            setMessageAttr(req, "red", "Value can not be negative or ZERO.");
            return true;
        }
        else {
            return false;
        }
    }

    public static <T extends Integer> boolean checkLsZeroInt(HttpServletRequest req,
                                                             T number){
        if (number.compareTo(0) < 0){
            setMessageAttr(req, "red", "Value can not be negative.");
            return true;
        }
        else {
            return false;
        }
    }

    public static <T extends Integer> boolean checkLsEqZeroInt
            (HttpServletRequest req, T number){
        if (number.compareTo(0) <= 0){
            setMessageAttr(req, "red", "Value can not be negative or ZERO.");
            return true;
        }
        else {
            return false;
        }
    }


    public static Integer String2Integer(String str) throws NumberFormatException{
        if (str == null) {
            return null;
        }
        if (str.length() == 0){
            return 0;
        }
        return Integer.parseInt(str);
    }

    public static Long String2Long(String str) throws NumberFormatException{
        if (str == null) {
            return null;
        }
        if (str.length() == 0){
            return 0L;
        }
        return Long.parseLong(str);
    }

    public static Double String2Double(String str) throws NumberFormatException{
        if (str == null) {
            return null;
        }
        if (str.length() == 0){
            return 0.0;
        }
        return Double.parseDouble(str);
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

}
