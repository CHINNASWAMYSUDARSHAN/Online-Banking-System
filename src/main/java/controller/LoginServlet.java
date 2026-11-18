package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if ("suda".equals(username) && "suda123".equals(password)) {

            HttpSession session = req.getSession();
            session.setAttribute("username", "suda");
            session.setAttribute("role", "admin");

            resp.sendRedirect("jsp/adminDashboard.jsp");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT id, username FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("userId", rs.getInt("id"));
                session.setAttribute("username", rs.getString("username"));
                session.setAttribute("role", "user"); // IMPORTANT

                resp.sendRedirect("jsp/userDashboard.jsp");
                return;
            } else {
                resp.sendRedirect("jsp/login.jsp?error=true");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("jsp/login.jsp?error=db");
        }
    }
}
