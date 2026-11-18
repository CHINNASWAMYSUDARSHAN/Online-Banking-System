package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBConnection;

@WebServlet("/viewAllUsers")
public class ViewAllUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<String[]> users = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT id, username FROM users";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new String[] {
                        String.valueOf(rs.getInt("id")),
                        rs.getString("username")
                });
            }

            req.setAttribute("users", users);
            req.getRequestDispatcher("jsp/viewAllUsers.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("users", null);
            req.getRequestDispatcher("jsp/viewAllUsers.jsp").forward(req, resp);
        }
    }
}
