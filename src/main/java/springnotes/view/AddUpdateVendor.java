package springnotes.view;

import hw7.notes.dao.VendorDao;
import hw7.notes.domain.Vendor;
import hw7.notes.service.NotebookServiceImpl;
import hw7.notes.view.Menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static hw7.notes.view.Servlet.checkStringPar;
import static hw7.notes.view.Servlet.setMessageAttr;

/**
 * Created by s_okhoda on 09.02.2016.
 */
@WebServlet("/AddVen")
public class AddUpdateVendor extends HttpServlet {
    public static final String NameSurname = " All rights reserved, Alexandr " +
            "Khodakovskyi, Kyiv 2016";
    private VendorDao vendorDao;

    @Override
    public void init() {
        vendorDao = (((NotebookServiceImpl)Menu.service).getVendorDao());

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {

        if (req.getParameter("back") != null) {
            req.getRequestDispatcher("/hw7.notes/pages/menu.jsp").forward(req, res);
            return;
        }

        if (req.getParameter("add") != null) {
            try {
                String name = req.getParameter("name");
                 if (!checkStringPar(req,"name")){
                     Vendor ven = new Vendor(name);
                     if(vendorDao.checkExist(ven)){
                         setMessageAttr(req, "red", "Vendor '" + ven
                                  + "' already exists in DB.");
                     }
                     else {
                         if (vendorDao.create(ven) != null) {
                             setMessageAttr(req, "green", "Vendor successfully added.");
                         }
                         else {
                             setMessageAttr(req, "red", "Failed to add Vendor '" +
                                     ven + "'.");
                         }
                     }
                     setVendorAttributes(req);
                }
                req.setAttribute("mode","0");
                req.getRequestDispatcher("/hw7.notes/pages/addVendor.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("update") != null) {
            try {
                String name = req.getParameter("name");
                Long venId = Long.parseLong(req.getParameter("venSel"));
                if (!checkStringPar(req,"name")){
                    Vendor venCh = new Vendor(name);
                    if(vendorDao.checkExistExceptId(venCh, venId)){
                        setMessageAttr(req, "red", "Vendor '" + venCh + "' " +
                                "already exists in DB.");
                    }
                    else {
                        Vendor ven = vendorDao.read(venId);
                        ven.setName(name);
                        if (vendorDao.update(ven)) {
                            setMessageAttr(req, "green", "Vendor successfully" +
                                    " updated.");
                        }
                        else {
                            setMessageAttr(req, "red", "Failed to update " +
                                    "Vendor '" + ven + "'.");
                        }
                    }
                    setVendorAttributes(req);
                }
                req.setAttribute("mode","1");
                req.setAttribute("SelVal", venId.toString());
                req.getRequestDispatcher("/hw7.notes/pages/addVendor.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("venSel") != null) {
            try {
                Long venId = Long.parseLong(req.getParameter("venSel"));
                Vendor ven = vendorDao.read(venId);
                setVendorAttributes2(req, ven);
                req.setAttribute("mode","1");
                req.setAttribute("SelVal", venId.toString());
                req.getRequestDispatcher("/hw7.notes/pages/addVendor.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

    }

    private void setVendorAttributes(HttpServletRequest req){
        req.setAttribute("nameA", req.getParameter("name"));
    }

    private void setVendorAttributes2(HttpServletRequest req, Vendor ven){
        if (ven == null){
            return;
        }
        req.setAttribute("nameA", ven.getName());
    }
}
