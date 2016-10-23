package hw7.notes.view;

import hw7.notes.dao.*;
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
@WebServlet("/AddSellStore1")
public class AddToSellFromStore extends HttpServlet {
    public static final String NameSurname = " All rights reserved, Alexandr " +
            "Khodakovskyi, Kyiv 2016";
    private StoreDao storeDao;
    private NotebookDao notebookDao;

    @Override
    public void init() {
        storeDao = ((NotebookServiceImpl) Menu.service).getStoreDao();
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

        if (req.getParameter("add") != null) {
            try {
                Long ntbId = Long.parseLong(req.getParameter("notebookSel"));

                if (!checkStringPar(req,"quantity") && !checkStringPar(req,"price")){
                    Integer quantity = String2Integer(req.getParameter("quantity"));
                    Double price = String2Double(req.getParameter("price"));

                    if (!checkLsEqZeroInt(req, quantity) && !checkLsZero(req, price)){
                        if(Menu.service.receive(ntbId, quantity, price) != null){
                            setMessageAttr(req, "green", "Store successfully added.");
                        }
                        else {
                            setMessageAttr(req, "red", "Failed to add Store.");
                        }
                    }
                }
                setStoreAttributes(req);
                req.setAttribute("SelVal", ntbId);
                req.getRequestDispatcher("/hw7.notes/pages/addStore.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("sell") != null) {
            try {
                Long storeId = Long.parseLong(req.getParameter("storeSel"));

                if (!checkStringPar(req,"quantity")){
                    Integer quantity = String2Integer(req.getParameter("quantity"));
                    if (!checkLsEqZeroInt(req, quantity)){
                        if(Menu.service.sale(storeId, quantity) != null){
                            setMessageAttr(req, "green", quantity + " items " +
                                    "successfully sold.");
                        }
                        else {
                            setMessageAttr(req, "red", "Failed to sell " +
                                    quantity + "items.");
                        }
                    }
                }
                setSellAttributes(req);
                req.setAttribute("SelVal", storeId);
                req.getRequestDispatcher("/hw7.notes/pages/sellFromStore.jsp")
                        .forward(req, res);
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

                List noteInStorePortion = Menu.service.getNotebooksByPortion(sPortion, cnt);
                req.setAttribute("cnt", cnt);
                req.setAttribute("totPages", totPages);
                req.setAttribute("noteInStorePortion", noteInStorePortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/hw7.notes/pages/updateStore.jsp")
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
                List noteInStorePortion = Menu.service.getNotebooksByPortion(sPortion, cnt);
                req.setAttribute("cnt", cnt);
                req.setAttribute("totPages", totPages);
                req.setAttribute("noteInStorePortion", noteInStorePortion);
                req.setAttribute("sPortion", sPortion);
                req.getRequestDispatcher("/hw7.notes/pages/updateStore.jsp")
                        .forward(req, res);
                return;
            }
            catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        }

    }

    private void setStoreAttributes(HttpServletRequest req){
        req.setAttribute("quantityA", req.getParameter("quantity"));
        req.setAttribute("priceA", req.getParameter("price"));
    }

    private void setSellAttributes(HttpServletRequest req){
        req.setAttribute("quantityA", req.getParameter("quantity"));
    }
}
