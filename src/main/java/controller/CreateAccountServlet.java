package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBConnection;

@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("jsp/login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        try (Connection con = DBConnection.getConnection()) {

            String checkSql = "SELECT * FROM accounts WHERE user_id=?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, userId);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                req.setAttribute("msg", "Account already exists");
                req.getRequestDispatcher("jsp/createAccount.jsp")
                   .forward(req, resp);
                return;
            }

            int accountNo = 100000 + new Random().nextInt(900000);

            String sql = "INSERT INTO accounts (account_no, user_id, balance) VALUES (?, ?, 0)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNo);
            ps.setInt(2, userId);
            ps.executeUpdate();

            req.setAttribute("msg", "Account created successfully");
            req.getRequestDispatcher("jsp/createAccount.jsp")
               .forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "Database error");
            req.getRequestDispatcher("jsp/createAccount.jsp")
               .forward(req, resp);
        }
    }
}
