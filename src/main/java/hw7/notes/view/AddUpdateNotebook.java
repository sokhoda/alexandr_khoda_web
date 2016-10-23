package hw7.notes.view;

import hw7.notes.dao.CPUDao;
import hw7.notes.dao.MemoryDao;
import hw7.notes.dao.NotebookDao;
import hw7.notes.dao.VendorDao;
import hw7.notes.domain.CPU;
import hw7.notes.domain.Memory;
import hw7.notes.domain.Notebook;
import hw7.notes.domain.Vendor;
import hw7.notes.service.NotebookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static hw7.notes.view.Servlet.*;

/**
 * Created by s_okhoda on 09.02.2016.
 */
@WebServlet("/AddNotebook1")
public class AddUpdateNotebook extends HttpServlet {
    public static final String NameSurname = " All rights reserved, Alexandr " +
            "Khodakovskyi, Kyiv 2016";
    private CPUDao cpuDao;
    private VendorDao vendorDao;
    private MemoryDao memoryDao;
    private NotebookDao notebookDao;

    @Override
    public void init() {
        cpuDao = ((NotebookServiceImpl)Menu.service).getCpuDao();
        vendorDao = ((NotebookServiceImpl)Menu.service).getVendorDao();
        memoryDao = ((NotebookServiceImpl) Menu.service).getMemoryDao();
        notebookDao = ((NotebookServiceImpl) Menu.service).getNoteDao();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {

        if (req.getParameter("back2Menu") != null) {
            try {
                    req.getRequestDispatcher("/hw7.notes/pages/menu.jsp").forward(req, res);
                    return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
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

                List notebookPortion = Menu.service.getNotebookTypesByPortion
                        (sPortion, cnt);
                req.setAttribute("cnt", cnt);
                req.setAttribute("totPages", totPages);
                req.setAttribute("notebookPortion", notebookPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/hw7.notes/pages/updateNotebook.jsp")
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
                List notebookPortion = Menu.service.getNotebookTypesByPortion
                        (sPortion, cnt);
                req.setAttribute("cnt", cnt);
                req.setAttribute("totPages", totPages);
                req.setAttribute("notebookPortion", notebookPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/hw7.notes/pages/updateNotebook.jsp")
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

                Long cpuId = Long.parseLong(req.getParameter("cpuSel"));
                CPU cpu = cpuDao.read(cpuId);

                Long memId = Long.parseLong(req.getParameter("memorySel"));
                Memory mem = memoryDao.read(memId);

                Date manDate = String2Date(req.getParameter("manDate"));
                String model = req.getParameter("model");
                if (!checkStringPar(req,"model")) {
                    Notebook notebook = new Notebook(ven, model, manDate,
                            cpu, mem);
                    if (notebookDao.checkExist(notebook)) {
                        setMessageAttr(req, "red", "Notebook type'" + notebook
                                 + "' already exists in DB.");
                    }
                    else {
                        if (notebookDao.create(notebook) != null) {
                            setMessageAttr(req, "green", "Notebook type successfully added.");
                        }
                        else {
                            setMessageAttr(req, "red", "Failed to add Notebook type '" +
                                    notebook + "'.");
                        }
                    }
                }
                setNotebookAttributes(req);
                req.setAttribute("SelVal", venId);
                req.setAttribute("SelValC", cpuId);
                req.setAttribute("SelValM", memId);
                req.getRequestDispatcher("/hw7.notes/pages/addNotebook.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("updNotebook2") != null) {
            try {
                Long idVal =  Long.parseLong(req.getParameter("idVal"));
                Notebook notebook = notebookDao.read(idVal);
                req.setAttribute("SelVal", notebook.getVendor().getId());
                req.setAttribute("SelValC", notebook.getCpu().getId());
                req.setAttribute("SelValM", notebook.getMemory().getId());
                req.setAttribute("manDateA", notebook.getManDate());
                req.setAttribute("modelA", notebook.getModel());
                req.setAttribute("idVal", idVal);
                req.getRequestDispatcher("/hw7.notes/pages/updateNotebook2.jsp")
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

                Long cpuId = Long.parseLong(req.getParameter("cpuSel"));
                CPU cpu = cpuDao.read(cpuId);

                Long memId = Long.parseLong(req.getParameter("memorySel"));
                Memory mem = memoryDao.read(memId);

                Long notebookId = Long.parseLong(req.getParameter("idVal"));
                Notebook notebook = notebookDao.read(notebookId);

                Date manDate = String2Date(req.getParameter("manDate"));
                String model = req.getParameter("model");

                if (!checkStringPar(req, "model")) {
                    Notebook notebookCheck = new Notebook(ven, model, manDate,
                            cpu, mem);
                    if (notebookDao.checkExistExceptId(notebookCheck, notebookId)) {
                        setMessageAttr(req, "red", "Notebook type '" +
                                notebookCheck + "' already exists in DB.");
                    }
                    else {
                        notebook.setVendor(ven);
                        notebook.setCpu(cpu);
                        notebook.setMemory(mem);
                        notebook.setManDate(manDate);
                        notebook.setModel(model);
                        if (notebookDao.update(notebook)) {
                            setMessageAttr(req, "green", "Notebook type successfully " +
                                    "updated.");
                        }
                        else {
                            setMessageAttr(req, "red", "Failed to update Notebook type '" +
                                    notebook + "'.");
                        }
                    }
                }
                setNotebookAttributes(req);
                req.setAttribute("SelVal", venId);
                req.setAttribute("SelValC", cpuId);
                req.setAttribute("SelValM", memId);
                req.setAttribute("idVal", notebookId);
                req.getRequestDispatcher("/hw7.notes/pages/updateNotebook2.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("delNotebook") != null) {
            try {
                Long notebookId = Long.parseLong(req.getParameter("idVal"));

                if (Menu.service.deleteNotebookType(notebookId)) {
                    setMessageAttr(req, "green", "Notebook type successfully deleted.");
                }
                else {
                    setMessageAttr(req, "red", "Failed to delete Notebook type.");
                }

                Integer sPortion = Integer.parseInt(req.getParameter("sPortion"));
                String cntMark = req.getParameter("cntMark");
                int cnt = Integer.parseInt(cntMark.split(" of ")[0]);

                Integer totPages = Menu.service.getNotebookTypesTotPages(sPortion);
                cnt = (cnt > totPages ? totPages : cnt);
                List notebookPortion = Menu.service.getNotebookTypesByPortion(sPortion, cnt);

                req.setAttribute("cnt", cnt);
                req.setAttribute("totPages", totPages);
                req.setAttribute("notebookPortion", notebookPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/hw7.notes/pages/updateNotebook.jsp")
                        .forward(req, res);

                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

    }

    private void setNotebookAttributes(HttpServletRequest req) throws ParseException {
        req.setAttribute("modelA", req.getParameter("model"));
        req.setAttribute("manDateA", String2Date(req.getParameter("manDate")));
    }
}
