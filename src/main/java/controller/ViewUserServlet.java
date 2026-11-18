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
import util.DBConnection;

@WebServlet("/viewUser")
public class ViewUserServlet extends HttpServlet {

    // Handles GET request (when page is opened)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Just open the JSP page
        req.getRequestDispatcher("jsp/viewUser.jsp").forward(req, resp);
    }

    // Handles POST request (when form is submitted)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT id, username FROM users WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                req.setAttribute("userId", rs.getInt("id"));
                req.setAttribute("username", rs.getString("username"));
            } else {
                req.setAttribute("error", "User not found");
            }

            req.getRequestDispatcher("jsp/viewUser.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Database error");
            req.getRequestDispatcher("jsp/viewUser.jsp").forward(req, resp);
        }
    }
}
