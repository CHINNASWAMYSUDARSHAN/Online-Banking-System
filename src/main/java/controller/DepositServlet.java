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

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("jsp/login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        double amount = Double.parseDouble(req.getParameter("amount"));

        if (amount <= 0) {
            req.setAttribute("msg", "Invalid amount");
            req.getRequestDispatcher("jsp/deposit.jsp").forward(req, resp);
            return;
        }

        try (Connection con = DBConnection.getConnection()) {

            // 🔹 Check account
            String selectSql = "SELECT balance FROM accounts WHERE user_id=?";
            PreparedStatement ps1 = con.prepareStatement(selectSql);
            ps1.setInt(1, userId);
            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                req.setAttribute("msg", "No bank account found");
                req.getRequestDispatcher("jsp/deposit.jsp").forward(req, resp);
                return;
            }

            double newBalance = rs.getDouble("balance") + amount;

            // 🔹 Update balance
            String updateSql = "UPDATE accounts SET balance=? WHERE user_id=?";
            PreparedStatement ps2 = con.prepareStatement(updateSql);
            ps2.setDouble(1, newBalance);
            ps2.setInt(2, userId);
            ps2.executeUpdate();

            req.setAttribute("msg", "Deposit successful");
            req.getRequestDispatcher("jsp/deposit.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "Database error");
            req.getRequestDispatcher("jsp/deposit.jsp").forward(req, resp);
        }
    }
}
