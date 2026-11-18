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

@WebServlet("/viewAllAccounts")
public class ViewAllAccountsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        List<String[]> accounts = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT account_no, user_id, balance, status FROM accounts";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                accounts.add(new String[]{
                        String.valueOf(rs.getInt("account_no")),
                        String.valueOf(rs.getInt("user_id")),
                        String.valueOf(rs.getDouble("balance")),
                        rs.getString("status")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("jsp/viewAllAccounts.jsp").forward(req, resp);
    }
}
