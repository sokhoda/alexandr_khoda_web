package hw5.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by s_okhoda on 02.02.2016.
 */
@WebServlet("/UserAuth")
public class MainWindow extends HttpServlet {
    private UserJDBCManager uManager = new UserJDBCManager();
    private List<User> users = new ArrayList<User>();

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if (req.getParameter("loginBut") != null) {
            String login = req.getParameter("login").trim();
            String pass = req.getParameter("pass").trim();
            try {
                if (login.length() != 0) {
                    if (uManager.readByNamePass(login, pass) != null){
                        users = uManager.findAll();
                        req.setAttribute("ulist", users);
                        req.getRequestDispatcher("hw5.auth/authUserList.jsp")
                                .forward(req, res);
                        return;
                    }
                }
                    req.setAttribute("FailedAuth", "Authentication failed.");
                    req.getRequestDispatcher("hw5.auth/UserAuth.jsp").forward
                            (req, res);
                    return;
            }
            catch (Exception e) {
//                req.setAttribute("javax.servlet.jsp.jspException",   e);
//                req.getRequestDispatcher("hw5.users/errorPage.jsp").forward(req,res);
                throw new ServletException(e.getMessage());
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {

    }
}

