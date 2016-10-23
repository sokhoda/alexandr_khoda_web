package hw6.notes.view;

import hw6.notes.dao.NotebookDao;
import hw6.notes.dao.NotebookDaoImpl;
import hw6.notes.domain.Notebook;
import hw6.notes.service.NotebookService;
import hw6.notes.service.NotebookServiceImpl;
import org.hibernate.HibernateException;
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
@WebServlet("/MenuNote")
public class Menu extends HttpServlet {
    public static final String NameSurname = " All rights reserved, Alexandr " +
            "Khodakovskyi, Kyiv 2016";
    private NotebookService service;

    @Override
    public void init() {
        SessionFactory sessionFactory = getSessionFactory();
        NotebookDao noteDao = new NotebookDaoImpl(sessionFactory);
        service = new NotebookServiceImpl(noteDao);

    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException {
            if (req.getParameter("addNote") != null) {
                try {
                    req.setAttribute("messageText", "");
                    req.getRequestDispatcher("/hw6.notes/pages/addNotebook.jsp")
                            .forward(req, res);
                    return;
                }
                catch (Exception e) {
                    throw new ServletException(e.getMessage());
                }
            }
            if (req.getParameter("listNote") != null){
                try {
                    List<Notebook> nlist = service.findAll();
                    req.setAttribute("nlist", nlist);
                    req.getRequestDispatcher("/hw6.notes/pages/noteList.jsp").forward
                            (req, res);
                    return;
                }
                catch (Exception e){
                    throw new ServletException(e.getMessage());
                }
            }
            if (req.getParameter("delNote") != null){
                try {
                    Long id = String2Long(req.getParameter("idDelNote"));
                    if (service.delete(id)){
                        setMessageAttr(req, "green", "Notebook successfully " +
                                "deleted.");
                    }
                    else {
                        setMessageAttr(req, "red", "Failed to delete notebook" +
                                ".");
                    }
                    req.getRequestDispatcher("/hw6.notes/pages/menu.jsp").forward
                            (req, res);
                    return;
                }
                catch (Exception e){
                    throw new ServletException(e.getMessage());
                }
            }
            if (req.getParameter("updtPrice") != null){
                try {
                    Long id = String2Long(req.getParameter("idUpdtPrice"));
                    Double price = String2Double(req.getParameter("priceUpdtPrice"));

                    if (service.changePrice(id, price)){
                        setMessageAttr(req,"green", "Price successfully " +
                                "updated.");
                    }
                    else {
                        setMessageAttr(req,"red", "Failed to update price " +
                                "value.");
                    }
                    req.getRequestDispatcher("/hw6.notes/pages/menu.jsp").forward
                            (req, res);
                    return;
                }
                catch (Exception e){
                    throw new ServletException(e.getMessage());
                }
            }
            if (req.getParameter("updtSnVendor") != null){
                try {
                    Long id = String2Long(req.getParameter("idUpdtSnVendor"));
                    String serial = req.getParameter("serialUpdtSnVendor");
                    String vendor = req.getParameter("vendorUpdtSnVendor");

                    if (service.changeSerialVendor(id, serial, vendor)){
                        setMessageAttr(req,"green", "Serial, vendor " +
                                "successfully updated.");
                    }
                    else {
                        setMessageAttr(req,"red", "Failed to update serial, " +
                                "vendor value.");
                    }
                    req.getRequestDispatcher("/hw6.notes/pages/menu.jsp").forward
                            (req, res);
                    return;
                }
                catch (Exception e){
                    throw new ServletException(e.getMessage());
                }
            }
            if (req.getParameter("delNoteByModel") != null){
                try{
                    String model = req.getParameter("modelDelNoteByModel");

                    if (service.deleteByModel(model)){
                        setMessageAttr(req,"green", "Notebooks with model='" +
                                model + "' successfully deleted");
                    }
                    else {
                        setMessageAttr(req,"red", "Failed to delete notebooks" +
                                " with model='" + model + "'");
                    }
                    req.getRequestDispatcher("/hw6.notes/pages/menu.jsp").forward
                            (req, res);
                    return;
                }
                catch (Exception e){
                    throw new ServletException(e.getMessage());
                }
            }
            if (req.getParameter("listNoteByVendor") != null){
                try {
                    String vendor = req.getParameter("vendorListNoteByVendor");
                    List<Notebook> nlist = service.findByVendor(vendor);
                    req.setAttribute("nlist", nlist);
                    req.getRequestDispatcher("/hw6.notes/pages/noteList.jsp").forward
                            (req, res);
                    return;
                }
                catch (Exception e){
                    throw new ServletException(e.getMessage());
                }
            }
            if (req.getParameter("listNoteByPriceManDate") != null){
                try {
                    Double price = String2Double(req.getParameter("priceListNoteByPriceManDate"));
                    Date manDateGreg = String2Date(req.getParameter
                            ("manDateListNoteByPriceManDate"));
//                    if (manDateGreg == null) {
//                        setMessageAttr(req,"red", "Date is nol valid: " + manDateGreg);
//                        return;
//                    }
                    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                    Date dt= df.parse( "01.05.2014");
                    List<Notebook> nlist = service.findByPriceManufDate
                            (price, dt);
                    req.setAttribute("nlist", nlist);
                    req.getRequestDispatcher("/hw6.notes/pages/noteList.jsp").forward
                            (req, res);
                    return;
                }
                catch (Exception e){
                    throw new ServletException(e.getMessage());
                }
            }
            if (req.getParameter("listNoteByPriceManDateVendor") != null){
                try {
                    Double priceFrom = String2Double(req.getParameter
                            ("priceFromListNoteByPriceManDateVendor"));
                    Double priceTo = String2Double(req.getParameter
                            ("priceToListNoteByPriceManDateVendor"));
                    GregorianCalendar manDateGreg = String2Gregorian(req.getParameter("manDateListNoteByPriceManDateVendor"));
                    if (manDateGreg == null) {
                        setMessageAttr(req,"red", "Date is not valid: " +
                                manDateGreg);
                        return;
                    }
                    String vendor = req.getParameter("vendorListNoteByPriceManDateVendor");

                    List<Notebook> nlist = service
                            .findBetweenPriceLtDateByVendor(priceFrom,
                                    priceTo, manDateGreg.getTime(), vendor);
                    req.setAttribute("nlist", nlist);
                    req.getRequestDispatcher("/hw6.notes/pages/noteList.jsp").forward
                            (req, res);
                    return;
                }
                catch (Exception e){
                    throw new ServletException(e.getMessage());
                }
            }

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {
        try {

            if (req.getParameter("back") != null) {
                req.getRequestDispatcher("/hw6.notes/pages/menu.jsp").forward
                        (req, res);
                return;
            }

            if (req.getParameter("addNoteB") != null){
//                res.getWriter().print("Add notebook");
//                return;
                String serial = req.getParameter("serial");
                String vendor = req.getParameter("vendor");
                String model = req.getParameter("model");
                GregorianCalendar manDateGreg = String2Gregorian(req.getParameter("manDate"));
                Integer priceInt = String2Integer(req.getParameter("price"));

                Long id = service.add(new Notebook(serial, vendor, model,
                        manDateGreg, priceInt));
                if (id != null){
                    setMessageAttr(req,"green", "Notebook '" + serial +
                            "' was successfully added.");
                }
                else {
                    setMessageAttr(req,"red", "Failed to add notebook " +
                            "'" + serial + "'.");
                }
            }
        }
        catch (NumberFormatException | ParseException  | HibernateException e) {
            setMessageAttr(req,"red", e.getMessage());
        }
        catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
        setNoteAttributes(req);
        req.getRequestDispatcher("/hw6.notes/pages/addNotebook.jsp").forward(req, res);
    }

    public SessionFactory getSessionFactory() {
        Locale.setDefault(Locale.ENGLISH);
        Configuration cfg =
                new Configuration().configure("hw6.notes/hibernate.cfg.xml");
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(cfg.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();

        return cfg.buildSessionFactory(standardServiceRegistry);
    }

    private void setMessageAttr(HttpServletRequest req, String color, String
            message){
        req.setAttribute("messageColor", color);
        req.setAttribute("messageText", message );
    }

    public static GregorianCalendar String2Gregorian(String dateStr) throws
            ParseException {
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

    public static Date String2Date(String dateStr)
            throws
            ParseException {
        if (dateStr == null || dateStr.length() == 0){
            return  null;
        }
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        date = df.parse(dateStr);
        return date;
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

    private void setNoteAttributes(HttpServletRequest req){
        req.setAttribute("serialA", req.getParameter("serial"));
        req.setAttribute("vendorA", req.getParameter("vendor"));
        req.setAttribute("modelA", req.getParameter("model"));
        req.setAttribute("manDateA", req.getParameter("manDate"));
        req.setAttribute("priceA", req.getParameter("price"));
    }


    public static String[] getAttribArray(HttpServletRequest req){
        String[] arr = new String[2];
        arr[0] = getAttribValue(req,"messageColor");
        arr[1] = getAttribValue(req, "messageText");
        return  arr;
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
    public static String getAttribValue(HttpServletRequest req, String name){
        if (name == null){
            return "";
        }
        if (req.getAttribute(name) == null){
            return "";
        }
        else {
            return (String)req.getAttribute(name);
        }
    }
}
