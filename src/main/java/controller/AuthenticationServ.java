package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by s_okhoda on 17.01.2016.
 *
 */
@WebServlet("/form")
public class AuthenticationServ extends HttpServlet {
    Map<String, String> users = new HashMap<>();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        resp.getWriter().println("Hello servlet1");

    }
    @Override
    public void init(){
        users.put("Iryna", "123");
        users.put("Petro", "poi432");
        users.put("Ivan", "abc123");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
//        String login = req.getParameter("login");
        Map<String, String []> paramMap = req.getParameterMap();
        int count;

        String login = paramMap.get("login")[0];
        String pass = paramMap.get("pass")[0];

        if (users.containsKey(login) && users.get(login).equals(pass)){
            req.getRequestDispatcher("/pages/menu.jsp").forward(req, res);
//            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
        else{
            req.setAttribute("FailedAuth","Your Login and/or password are not" +
                    " correct. Please try once more.");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }

    }
}
