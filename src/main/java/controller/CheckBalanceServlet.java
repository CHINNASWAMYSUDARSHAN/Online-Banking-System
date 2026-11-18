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

@WebServlet("/checkBalance")
public class CheckBalanceServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

            String sql = "SELECT account_no, balance FROM accounts WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                req.setAttribute("accountNo", rs.getInt("account_no"));
                req.setAttribute("balance", rs.getDouble("balance"));
            } else {
                req.setAttribute("msg", "No bank account found");
            }

            req.getRequestDispatcher("jsp/checkBalance.jsp")
               .forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "Database error");
            req.getRequestDispatcher("jsp/checkBalance.jsp")
               .forward(req, resp);
        }
    }
}
