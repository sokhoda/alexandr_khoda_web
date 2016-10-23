package hw5.auth;

import hw5.users.AddUserException;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by s_okhoda on 02.02.2016.
 */
public class UserJDBCManager {

    public UserJDBCManager() {
    }

    public Connection getConn() throws SQLException {
        Locale.setDefault(Locale.ENGLISH);
        Connection conn = null;

        String url = "jdbc:oracle:thin:@localhost:1521:XE";

        try {
            conn = DriverManager.getConnection(url, "notebooks", "notebooks");
        }
        catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            throw e;
        }
        return conn;

    }

    public int userExists(Connection conn, String name) {
        if (name == null) {
            return 0;
        }
        if (conn == null) {
            return -1;
        }

        try {
            CallableStatement cs = conn.prepareCall("{? = call getUserByName(?)}");

            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, name);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);

            if (!rs.next()) {
                return 0;
            }
            else {
                return 1;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }

    }

    public User readByNamePass(String login, String pass) throws SQLException {
        if (login == null || pass == null) {
            return null;
        }
        Connection conn = null;
        try {
            conn = getConn();
            CallableStatement cs = conn.prepareCall("{? = call readByNamePass" +
                    "(?, ?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, login);
            cs.setString(3, pass);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(rs.getDate("CDATE"));
                return (new User(rs.getInt("ID"), rs.getString("NAME"),
                        rs.getString("PASS"), gc));
            }
            else {
                return null;
            }
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public int create(User user) throws AddUserException, SQLException {
        int result = -1;
        Connection conn = null;
        try {
            conn = getConn();

            if (userExists(conn, user.getLogin()) != 0) {
                throw new AddUserException("User '" + user.getLogin() +
                        "' exists already.");
            }
            CallableStatement cs = conn.prepareCall("{? = call ADDUSER(?, ?, ?)}");

            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setString(2, user.getLogin());
            cs.setString(3, user.getPass());
            cs.setDate(4, new Date(user.getRegDate().getTimeInMillis()));
            cs.execute();
            result = cs.getInt(1);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }

    public List findAll() throws SQLException {
        List<User> users = new ArrayList<User>();

        Connection conn = null;
        try {
            conn = getConn();

            CallableStatement cs = null;
            cs = conn.prepareCall("{ ? = call SHOWUSERSCUR()}");

            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(rs.getDate("CDATE"));
                users.add(new User(rs.getInt("ID"), rs.getString("NAME"),
                        rs.getString("PASS"), gc));

//                System.out.printf("\n%15s %15s %15s %15s",
//                        rs.getString("ID"), rs.getString("NAME"),
//                        rs.getString("PASS"), rs.getDate("CDATE"));
            }

        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

}
