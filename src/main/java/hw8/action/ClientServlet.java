package hw8.action;

import hw8.taxi.domain.Client;
import hw8.taxi.exception.ClientException;
import hw8.taxi.service.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by s_okhoda on 20.01.2016.
 */
@WebServlet("/taxi1")
public class ClientServlet extends HttpServlet {
    private ClientServiceImpl clientServiceImpl = new ClientServiceImpl();

    private Map<String, String> users = new HashMap<>();

    @Override
    public void init(){
        clientServiceImpl.fillClientsRandomly(clientServiceImpl
                .getClientService().getNumOfClients());
        users.put("Iryna", "123");
        users.put("Petro", "poi432");
        users.put("Ivan", "abc123");
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setAttribute("clientServiceImpl", clientServiceImpl);

        if (req.getParameter("back") != null){
            clientServiceImpl.getClientService().setLastPrintedClientInx(-1);
            req.getRequestDispatcher("/hw8.taxi/pages/dashboard.jsp").forward(req, res);
        }
        if (req.getParameter("authenticate") != null){
            Map<String, String []> paramMap = req.getParameterMap();

            String login = paramMap.get("login")[0];
            String pass = paramMap.get("pass")[0];

            if (users.containsKey(login) && users.get(login).equals(pass)){
                req.getRequestDispatcher("/hw8.taxi/pages/dashboard.jsp").forward
                        (req, res);
            }
            else{
                req.setAttribute("FailedAuth","Your Login and/or password are not" +
                        " correct. Please try once more.");
                req.getRequestDispatcher("/hw8.taxi/pages/loginTaxi.jsp")
                        .forward(req, res);
            }
        }
        try {
            clientServiceImpl.getClientService().createClient(
                    req.getParameter("name"), req.getParameter("surname"),
                    req.getParameter("phone"), req.getParameter("address")
            );
            List <Client> clients = clientServiceImpl.getClientService()
                    .getClients();

            req.setAttribute("SuccessfulRegistration", "Client \n" +
                    "\""+ clients.get(clients.size() - 1).getName() + ", " +
                    clients.get(clients.size() - 1).getSurname() + "\"" +
                    "\nwas successfully registered.");
        }
        catch (ClientException e) {
            req.setAttribute("FailedRegistration", e.getMessage());
        }
        req.getRequestDispatcher("/hw8.taxi/pages/registerClient.jsp").forward(req, res);
    }

    private Integer checkIntValue(HttpServletRequest req, HttpServletResponse res,
                              String valName) throws IOException{
        String val = req.getParameter(valName);
        int quantity = 0;
        try {
            quantity = Integer.parseInt(val);
        }
        catch (NumberFormatException ex) {
            res.getWriter().print("Not valid '" + valName + "' value:"+ val);
            return null;
        }
        return quantity;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<Client> clientList = null;

        Integer quantity = checkIntValue(req, res, "clientQuantity");
        if (quantity == null) {
            return;
        }
        clientServiceImpl.getClientService().setClientQuantity(quantity);

        Integer orderSum = checkIntValue(req, res, "clientOrderSum");
        if (orderSum == null) {
            return;
        }
        clientServiceImpl.getClientService().setClientOrderSum(orderSum);

        req.setAttribute("clientServiceImpl", clientServiceImpl);

        if (req.getParameter("selAct").equals("createClient")){
            req.getRequestDispatcher("/hw8.taxi/pages/registerClient.jsp").forward(req,res);
            return;
        }

        if (req.getParameter("selAct").equals("showClientsByPortion")) {
            clientList = clientServiceImpl.getClientService().getClients();
            req.setAttribute("quantity", quantity);
        }
        else if (req.getParameter("selAct").equals("showClientsGtSum")){
            clientList = clientServiceImpl.getClientService().showClientsGtSum(orderSum);
            req.setAttribute("showClientsGtSum", orderSum);
        }
        else if (req.getParameter("selAct").equals("showClientsLastMonth")){
            clientList = clientServiceImpl.getClientService().showClientsLastMonth();
            req.setAttribute("showClientsLastMonth", "");
        }
        else{
            res.getWriter().println("Not valid option selected.");
            return;
        }

        if (clientList != null) {
            req.setAttribute("clientList", clientList);
            req.getRequestDispatcher("/hw8.taxi/pages/clients.jsp").forward
                    (req,res);
        }
        else {
            res.getWriter().println("Error: clientList is NULL");
            return;
        }
    }
}
