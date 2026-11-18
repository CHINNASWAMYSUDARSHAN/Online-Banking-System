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

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("jsp/login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        int toAccount = Integer.parseInt(req.getParameter("toAccount"));
        double amount = Double.parseDouble(req.getParameter("amount"));

        if (amount <= 0) {
            req.setAttribute("msg", "Invalid amount");
            req.getRequestDispatcher("jsp/transfer.jsp").forward(req, resp);
            return;
        }

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false); // 🔐 transaction start

            // 1️⃣ Get sender account
            String senderSql = "SELECT account_no, balance FROM accounts WHERE user_id=?";
            PreparedStatement ps1 = con.prepareStatement(senderSql);
            ps1.setInt(1, userId);
            ResultSet rs1 = ps1.executeQuery();

            if (!rs1.next()) {
                req.setAttribute("msg", "Your account not found");
                req.getRequestDispatcher("jsp/transfer.jsp").forward(req, resp);
                return;
            }

            int fromAccount = rs1.getInt("account_no");
            double senderBalance = rs1.getDouble("balance");

            if (senderBalance < amount) {
                req.setAttribute("msg", "Insufficient balance");
                req.getRequestDispatcher("jsp/transfer.jsp").forward(req, resp);
                return;
            }

            // 2️⃣ Check receiver account
            String receiverSql = "SELECT balance FROM accounts WHERE account_no=?";
            PreparedStatement ps2 = con.prepareStatement(receiverSql);
            ps2.setInt(1, toAccount);
            ResultSet rs2 = ps2.executeQuery();

            if (!rs2.next()) {
                req.setAttribute("msg", "Receiver account not found");
                req.getRequestDispatcher("jsp/transfer.jsp").forward(req, resp);
                return;
            }

            double receiverBalance = rs2.getDouble("balance");

            // 3️⃣ Deduct from sender
            String deductSql = "UPDATE accounts SET balance=? WHERE account_no=?";
            PreparedStatement ps3 = con.prepareStatement(deductSql);
            ps3.setDouble(1, senderBalance - amount);
            ps3.setInt(2, fromAccount);
            ps3.executeUpdate();

            // 4️⃣ Add to receiver
            String addSql = "UPDATE accounts SET balance=? WHERE account_no=?";
            PreparedStatement ps4 = con.prepareStatement(addSql);
            ps4.setDouble(1, receiverBalance + amount);
            ps4.setInt(2, toAccount);
            ps4.executeUpdate();

            con.commit(); // ✅ transaction success

            req.setAttribute("msg", "Transfer successful");
            req.setAttribute("amount", amount);
            req.setAttribute("balance", senderBalance - amount);

            req.getRequestDispatcher("jsp/transfer.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                // rollback if error
                DBConnection.getConnection().rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            req.setAttribute("msg", "Transfer failed");
            req.getRequestDispatcher("jsp/transfer.jsp").forward(req, resp);
        }
    }
}
