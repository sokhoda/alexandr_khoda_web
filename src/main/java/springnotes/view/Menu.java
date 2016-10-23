package springnotes.view;

import org.apache.log4j.Logger;
import springnotes.dao.*;
import springnotes.domain.CPU;
import springnotes.domain.Memory;
import springnotes.exception.PortionException;
import springnotes.service.NotebookService;
import springnotes.service.NotebookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static springnotes.view.Servlet.String2Integer;

/**
 * Created by s_okhoda on 09.02.2016.
 */
@WebServlet(name = "HW7Menu", value = "/MainNote")
public class Menu extends HttpServlet {
    public static final Logger log = Logger.getLogger(Menu.class);
    public static final String NameSurname = " All rights reserved, Alexandr " +
            "Khodakovskyi, Kyiv 2016";

    public static NotebookService service;
    private VendorDao vendorDao;
    private CPUDao cpuDao;
    private MemoryDao memoryDao;
    private NotebookDao notebookDao;

    @Override
    public void init() {
        log.info("Menu Init!");
        service = new NotebookServiceImpl();
        vendorDao = (((NotebookServiceImpl)service).getVendorDao());
        cpuDao = (((NotebookServiceImpl)service).getCpuDao());
        memoryDao = ((NotebookServiceImpl) service).getMemoryDao();
        notebookDao = ((NotebookServiceImpl) service).getNoteDao();
//        ((NotebookServiceImpl)service).getLog().info("Menu.init()");
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws
            ServletException {
        if (req.getParameter("crVen") != null) {
            try {
                req.setAttribute("mode", "0");
                req.getRequestDispatcher("/springnotes/pages/addVendor.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }
        if (req.getParameter("crCPU") != null) {
            try {
               log.info("test!!!");
                req.getRequestDispatcher("/springnotes/pages/addCPU.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }
        if (req.getParameter("crMemory") != null) {
            try {
                req.getRequestDispatcher("/hw7.notes/pages/addMemory.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }
        if (req.getParameter("crNtbType") != null) {
            try {
                req.getRequestDispatcher("/springnotes/pages/addNotebook.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }
        if (req.getParameter("updVen") != null) {
            try {
                req.setAttribute("mode", "1");
                req.setAttribute("SelInx", "0");
                req.getRequestDispatcher("/springnotes/pages/addVendor.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("updCPU") != null) {
            try {
                List cpu = (List<CPU>)cpuDao.findAll();
                Integer sPortion = String2Integer((String)req.getParameter("updCPUPortion"));
                if (sPortion == 0) {
                    throw new PortionException("Portion size can not be ZERO.");
                }
                Integer totPages = (cpu.size() == 0 ? 1 :(int) Math.ceil
                        (cpu.size() / (double)sPortion));
                List cpuPortion = (List<CPU>)cpuDao.getCPUByPortion
                        (sPortion, 1);
                req.setAttribute("cnt", 1);
                req.setAttribute("totPages", totPages);
                req.setAttribute("cpuPortion", cpuPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/springnotes/pages/updateCPU.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }
        if (req.getParameter("updMemory") != null) {
            try {
                List memory = (List<Memory>)memoryDao.findAll();
                Integer sPortion = String2Integer((String)req.getParameter("updMemoryPortion"));
                if (sPortion == 0) {
                    throw new PortionException("Portion size can not be ZERO.");
                }
                Integer totPages = (memory.size() == 0 ? 1 :(int) Math.ceil
                        (memory.size() / (double)sPortion));
                List memoryPortion = (List<Memory>)memoryDao.getMemoryByPortion(sPortion, 1);
                req.setAttribute("cnt", 1);
                req.setAttribute("totPages", totPages);
                req.setAttribute("memoryPortion", memoryPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/springnotes/pages/updateMemory.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }
        if (req.getParameter("updNtb") != null) {
            try {
                Integer sPortion = String2Integer(req.getParameter("updNtbPortion"));

                Integer totPages = springnotes.view.Menu.service.getNotebookTypesTotPages(sPortion);
                List notebookPortion = springnotes.view.Menu.service
                        .getNotebookTypesByPortion(sPortion, 1);
                req.setAttribute("cnt", 1);
                req.setAttribute("totPages", totPages);
                req.setAttribute("notebookPortion", notebookPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/springnotes/pages/updateNotebook.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }
        if (req.getParameter("listNtbTypesByPortion") != null) {
            try {

                Integer sPortion = String2Integer((String)req.getParameter
                        ("listNtbTypesByPortionPortion"));

                Integer totPages = springnotes.view.Menu.service.getNotebookTypesTotPages(sPortion);
                List notebookPortion = springnotes.view.Menu.service
                        .getNotebookTypesByPortion(sPortion, 1);
                req.setAttribute("cnt", 1);
                req.setAttribute("totPages", totPages);
                req.setAttribute("notebookPortion", notebookPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/springnotes/pages/updateNotebook.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("listNtbByPortion") != null || req.getParameter("listNtbStore") != null) {
            try {
                Integer sPortion = (req.getParameter("listNtbStore") != null ?
                    Integer.MAX_VALUE :
                        String2Integer(req.getParameter("listNtbByPortionPortion")));
                Integer totPages = springnotes.view.Menu.service.getNotebookInStoreTotPages(sPortion);

                List noteInStorePortion = springnotes.view.Menu.service.getNotebooksByPortion(sPortion, 1);
                req.setAttribute("cnt", 1);
                req.setAttribute("totPages", totPages);
                req.setAttribute("noteInStorePortion", noteInStorePortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/springnotes/pages/updateStore.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }
        if (req.getParameter("sell") != null) {
            try {
                req.setAttribute("SelInx", "0");
                req.getRequestDispatcher("/springnotes/pages/sellFromStore.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }
        if (req.getParameter("accBatch") != null) {
            try {
                req.setAttribute("SelInx", "0");
                req.getRequestDispatcher("/springnotes/pages/addStore.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {
//        try {
//
//            if (req.getParameter("back") != null) {
//                req.getRequestDispatcher("/hw6.notes/pages/menu.jsp").forward
//                        (req, res);
//                return;
//            }
//
//            if (req.getParameter("addNoteB") != null){
////                res.getWriter().print("Add notebook");
////                return;
//                String serial = req.getParameter("serial");
//                String vendor = req.getParameter("vendor");
//                String model = req.getParameter("model");
//                GregorianCalendar manDateGreg = String2Gregorian(req.getParameter("manDate"));
//                Integer priceInt = String2Integer(req.getParameter("price"));
//
//                Long id = service.add(new Notebook(serial, vendor, model,
//                        manDateGreg, priceInt));
//                if (id != null){
//                    setMessageAttr(req,"green", "Notebook '" + serial +
//                            "' was successfully added.");
//                }
//                else {
//                    setMessageAttr(req,"red", "Failed to add notebook " +
//                            "'" + serial + "'.");
//                }
//            }
//        }
//        catch (NumberFormatException | ParseException  | HibernateException e) {
//            setMessageAttr(req,"red", e.getMessage());
//        }
//        catch (Exception e) {
//            throw new ServletException(e.getMessage());
//        }
//        setNoteAttributes(req);
//        req.getRequestDispatcher("/hw6.notes/pages/addNotebook.jsp").forward(req, res);
    }

    private void setNoteAttributes(HttpServletRequest req){
        req.setAttribute("serialA", req.getParameter("serial"));
        req.setAttribute("vendorA", req.getParameter("vendor"));
        req.setAttribute("modelA", req.getParameter("model"));
        req.setAttribute("manDateA", req.getParameter("manDate"));
        req.setAttribute("priceA", req.getParameter("price"));
    }


}
