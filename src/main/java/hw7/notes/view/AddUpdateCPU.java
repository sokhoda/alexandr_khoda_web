package hw7.notes.view;

import hw7.notes.dao.CPUDao;
import hw7.notes.dao.VendorDao;
import hw7.notes.domain.CPU;
import hw7.notes.domain.Vendor;
import hw7.notes.service.NotebookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static hw7.notes.view.Servlet.*;

/**
 * Created by s_okhoda on 09.02.2016.
 */
@WebServlet("/AddCpu1")
public class AddUpdateCPU extends HttpServlet {
    public static final String NameSurname = " All rights reserved, Alexandr " +
            "Khodakovskyi, Kyiv 2016";
    private CPUDao cpuDao;
    private VendorDao vendorDao;

    @Override
    public void init() {
        cpuDao = ((NotebookServiceImpl)Menu.service).getCpuDao();
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

                List cpuPortion = (List<CPU>)cpuDao.getCPUByPortion(sPortion, cnt);
                req.setAttribute("cnt", cnt);
                req.setAttribute("totPages", totPages);
                req.setAttribute("cpuPortion", cpuPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/hw7.notes/pages/updateCPU.jsp")
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
                List cpuPortion = (List<CPU>)cpuDao.getCPUByPortion(sPortion, cnt);
                req.setAttribute("cnt", cnt);
                req.setAttribute("totPages", totPages);
                req.setAttribute("cpuPortion", cpuPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/hw7.notes/pages/updateCPU.jsp")
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
                Double freq = String2Double(req.getParameter("freq"));
                String model = req.getParameter("model");
                if (!checkStringPar(req,"freq")) {
                    CPU cpu = new CPU(ven, freq, model);
                    if (cpuDao.checkExist(cpu)) {
                        setMessageAttr(req, "red", "CPU '" + cpu
                                 + "' already exists in DB.");
                    }
                    else {
                        if (cpuDao.create(cpu) != null) {
                            setMessageAttr(req, "green", "CPU successfully added.");
                        }
                        else {
                            setMessageAttr(req, "red", "Failed to add CPU '" +
                                    cpu + "'.");
                        }
                    }
                }
                setCPUAttributes(req);
                req.setAttribute("SelVal", venId);
                req.getRequestDispatcher("/hw7.notes/pages/addCPU.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("updCPU2") != null) {
            try {
                Long idVal =  Long.parseLong(req.getParameter("idVal"));
                CPU cpu = cpuDao.read(idVal);
                req.setAttribute("SelVal", cpu.getVendor().getId());
                req.setAttribute("freqA", cpu.getFreq());
                req.setAttribute("modelA", cpu.getModel());
                req.setAttribute("idVal", idVal);
                req.getRequestDispatcher("/hw7.notes/pages/updateCPU2.jsp")
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

                Long cpuId = Long.parseLong(req.getParameter("idVal"));
                CPU cpu = cpuDao.read(cpuId);
                Double freq = String2Double(req.getParameter("freq"));
                String model = req.getParameter("model");

                if (!checkStringPar(req,"freq")) {
                    if (Menu.service.updateCPU(cpu)) {
                        setMessageAttr(req, "green", "CPU successfully " +
                                "updated.");
                    }
                    else {
                        setMessageAttr(req, "red", "Failed to update CPU '" +
                                cpu + "'.");
                    }
                }
                setCPUAttributes(req);
                req.setAttribute("SelVal", venId);
                req.setAttribute("idVal", cpuId);
                req.getRequestDispatcher("/hw7.notes/pages/updateCPU2.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("delCPU") != null) {
            try {
                Long cpuId = Long.parseLong(req.getParameter("idVal"));
                CPU cpu = cpuDao.read(cpuId);
                if ( cpuDao.delete(cpu)) {
                    setMessageAttr(req, "green", "CPU successfully deleted.");
                }
                else {
                    setMessageAttr(req, "red", "Failed to delete CPU '" +
                            cpu + "'.");
                }

                List cpu1 = (List<CPU>)cpuDao.findAll();
                Integer sPortion = Integer.parseInt(req.getParameter("sPortion"));
                String cntMark = req.getParameter("cntMark");
                int cnt = Integer.parseInt(cntMark.split(" of ")[0]);

                Integer totPages = (cpu1.size() == 0 ? 1 :(int) Math.ceil
                        (cpu1.size() / (double)sPortion));
                cnt = (cnt > totPages ? totPages : cnt);
                List cpuPortion = (List<CPU>)cpuDao.getCPUByPortion(sPortion, cnt);

                req.setAttribute("cnt", cnt);
                req.setAttribute("totPages", totPages);
                req.setAttribute("cpuPortion", cpuPortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/hw7.notes/pages/updateCPU.jsp")
                        .forward(req, res);

                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

    }

    private void setCPUAttributes(HttpServletRequest req){
        req.setAttribute("freqA", req.getParameter("freq"));
        req.setAttribute("modelA", req.getParameter("model"));
    }
}
