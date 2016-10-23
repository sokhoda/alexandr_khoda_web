package hw7.notes.view;

import hw7.notes.dao.MemoryDao;
import hw7.notes.dao.VendorDao;
import hw7.notes.domain.Memory;
import hw7.notes.domain.Vendor;
import hw7.notes.service.NotebookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static hw7.notes.view.Servlet.checkStringPar;
import static hw7.notes.view.Servlet.setMessageAttr;

/**
 * Created by s_okhoda on 09.02.2016.
 */
@WebServlet("/AddMem1")
public class AddUpdateMemory extends HttpServlet {
    public static final String NameSurname = " All rights reserved, Alexandr " +
            "Khodakovskyi, Kyiv 2016";
    private MemoryDao memoryDao;
    private VendorDao vendorDao;

    @Override
    public void init() {
        memoryDao = (((NotebookServiceImpl)Menu.service).getMemoryDao());
        vendorDao = ((NotebookServiceImpl)Menu.service).getVendorDao();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {

        if (req.getParameter("back2Menu") != null) {
            req.getRequestDispatcher("/hw7.notes/pages/menu.jsp").forward(req, res);
            return;
        }

        if (req.getParameter("back") != null) {
            try {
                Integer sPortion = Integer.parseInt(req.getParameter("sPortion"));
                String cntMark = req.getParameter("cntMark");
                int cnt = Integer.parseInt(cntMark.split(" of ")[0]);
                int totPages = Integer.parseInt(cntMark.split(" of ")[1]);

                if (cnt > 1) {
                    cnt--;
                }
                List memoryPortion = (List<Memory>)memoryDao.getMemoryByPortion(sPortion, cnt);
                req.setAttribute("cnt", cnt);
                req.setAttribute("totPages", totPages);
                req.setAttribute("memoryPortion", memoryPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/hw7.notes/pages/updateMemory.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("forward") != null) {
            try {
                Integer sPortion = Integer.parseInt(req.getParameter("sPortion"));
                String cntMark = req.getParameter("cntMark");
                int cnt = Integer.parseInt(cntMark.split(" of ")[0]);
                int totPages = Integer.parseInt(cntMark.split(" of ")[1]);

                if (cnt < totPages) {
                    cnt++;
                }
                List memoryPortion = (List<Memory>)memoryDao.getMemoryByPortion(sPortion, cnt);
                req.setAttribute("cnt", cnt);
                req.setAttribute("totPages", totPages);
                req.setAttribute("memoryPortion", memoryPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/hw7.notes/pages/updateMemory.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("add") != null) {
            try {
                Long venId = Long.parseLong(req.getParameter("venSel"));
                Vendor ven = vendorDao.read(venId);
                String size = req.getParameter("sizeSel");
                if (!checkStringPar(req, "sizeSel")){
                    Memory mem = new Memory(size, ven);
                    if (memoryDao.checkExist(mem)) {
                        setMessageAttr(req, "red", "Memory '" + mem
                                + "' already exists in DB.");
                    }
                    else {
                        if (memoryDao.create(mem) != null) {
                            setMessageAttr(req, "green", "Memory successfully added.");
                        }
                        else {
                            setMessageAttr(req, "red", "Failed to add Memory '" +
                                    mem + "'.");
                        }
                    }
                    req.setAttribute("SelVal", venId);
                    req.setAttribute("SelValS", size);
                }
                req.getRequestDispatcher("/hw7.notes/pages/addMemory.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("updMemory2") != null) {
            try {
                Long idVal =  Long.parseLong(req.getParameter("idVal"));
                Memory mem = memoryDao.read(idVal);
                req.setAttribute("SelVal", mem.getVendor().getId());
                req.setAttribute("SelValS", mem.getSizze());
                req.setAttribute("idVal", idVal);
                req.getRequestDispatcher("/hw7.notes/pages/updateMemory2.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("update") != null) {
            try {
                Long venId = Long.parseLong(req.getParameter("venSel"));
                Vendor ven = vendorDao.read(venId);

                Long memId = Long.parseLong(req.getParameter("idVal"));
                Memory mem = memoryDao.read(memId);
                String size = req.getParameter("sizeSel");
                if (!checkStringPar(req, "sizeSel")) {
                    Memory memCheck = new Memory(size, ven);
                    if (memoryDao.checkExistExceptId(memCheck, memId)) {
                        setMessageAttr(req, "red", "Memory '" + memCheck
                                + "' already exists in DB.");
                    }
                    else {
                        mem.setVendor(ven);
                        mem.setSizze(size);
                        if (memoryDao.update(mem)) {
                            setMessageAttr(req, "green", "Memory successfully" +
                                    " updated.");
                        }
                        else {
                            setMessageAttr(req, "red", "Failed to update " +
                                    "Memory '" + mem + "'.");
                        }
                    }
                }
                req.setAttribute("SelVal", venId);
                req.setAttribute("SelValS", size);
                req.setAttribute("idVal", memId);
                req.getRequestDispatcher("/hw7.notes/pages/updateMemory2.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("delMemory") != null) {
            try {
                Long memId = Long.parseLong(req.getParameter("idVal"));
                Memory mem = memoryDao.read(memId);
                if ( memoryDao.delete(mem)) {
                    setMessageAttr(req, "green", "Memory successfully deleted.");
                }
                else {
                    setMessageAttr(req, "red", "Failed to delete Memory '" +
                            mem + "'.");
                }
                List mem1 = (List<Memory>)memoryDao.findAll();
                Integer sPortion = Integer.parseInt(req.getParameter("sPortion"));
                String cntMark = req.getParameter("cntMark");
                int cnt = Integer.parseInt(cntMark.split(" of ")[0]);

                Integer totPages = (mem1.size() == 0 ? 1 :(int) Math.ceil
                        (mem1.size() / (double)sPortion));
                cnt = (cnt > totPages ? totPages : cnt);
                List memoryPortion = (List<Memory>)memoryDao.getMemoryByPortion(sPortion, cnt);

                req.setAttribute("cnt", cnt);
                req.setAttribute("totPages", totPages);
                req.setAttribute("memoryPortion", memoryPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/hw7.notes/pages/updateMemory.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

    }
    private void setMemoryAttributes(HttpServletRequest req){
        req.setAttribute("nameA", req.getParameter("name"));
    }
}
