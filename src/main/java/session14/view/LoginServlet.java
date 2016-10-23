package session14.view;

import session14.service.GeneralServiceImpl;

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
@WebServlet("/LoginPage")
public class LoginServlet extends HttpServlet {
    public static final String NameSurname = " All rights reserved, Alexandr " +
            "Khodakovskyi, Kyiv 2016";
    private GeneralServiceImpl service;

    @Override
    public void init() {
        service = new GeneralServiceImpl();
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException {
        if (req.getParameter("loginBut") != null) {
            try {
                String login = req.getParameter("login");
                String pass = req.getParameter("pass");
                if (service.loginCheck(login, pass)){
                    res.getWriter().println("Hallo!");
                    req.getRequestDispatcher("/hw7.notes/pages/menu.jsp").forward(req, res);
                }
                else {
                    res.getWriter().println("Bye!");
                }
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("getEmployees") != null) {
            try {
                Long compID = String2Long(req.getParameter("CompID"));

                List<Object[]> elist = service.getEmployees(compID);
                printList(res, elist);
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {
    }

    private void printList(HttpServletResponse res, List<Object[]> list){
        try {
            if (list.size() == 0){
                res.getWriter().println("List is empty");
                return;
            }
            for (Object[] obj : list) {
                for (Object ob : obj) {
                    res.getWriter().println(ob);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
