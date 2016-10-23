package hw5.users;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by s_okhoda on 02.02.2016.
 */
@WebServlet("/UserAddRender")
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
//        users.put("Iryna", "123");
//        users.put("Petro", "poi432");
//        users.put("Ivan", "abc123");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if (req.getParameter("addUser") != null) {
            String login = req.getParameter("login").trim();
            if (login.length() == 0) {
                req.setAttribute("FailedCreation", "Login cannot have ZERO " +
                        "length");
                req.getRequestDispatcher("hw5.users/UserAddRender.jsp").forward(req, res);
                return;
            }
            String pass = req.getParameter("pass").trim();
            if (pass.length() == 0) {
                req.setAttribute("FailedCreation", "Pass cannot have ZERO " +
                        "length");
                req.getRequestDispatcher("hw5.users/UserAddRender.jsp").forward(req, res);
                return;
            }

            try {
                uManager.create(new User(login, pass));
                req.setAttribute("UserAddedSuccess", "User successfully added.");
                req.getRequestDispatcher("hw5.users/UserAddRender.jsp").forward(req, res);
                return;
            }
            catch (Exception e) {
//                req.setAttribute("javax.servlet.jsp.jspException",   e);
//                req.getRequestDispatcher("hw5.users/errorPage.jsp").forward(req,res);
                throw new ServletException(e.getMessage());
            }
        }

        if (req.getParameter("listAll") != null) {
            try {
                users = uManager.findAll();
                req.setAttribute("ulist", users);
                req.getRequestDispatcher("hw5.users/userList.jsp").forward(req, res);
                return;
            }
            catch (SQLException e) {
                throw new ServletException(e.getMessage());
            }

        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {

    }
}

