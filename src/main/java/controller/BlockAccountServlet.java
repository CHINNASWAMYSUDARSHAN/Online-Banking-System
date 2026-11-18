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

@WebServlet("/blockAccount")
public class BlockAccountServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ✅ Handles page open (GET) safely
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("jsp/blockAccount.jsp");
    }

    // ✅ Handles block/unblock logic (POST)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int accountNo = Integer.parseInt(req.getParameter("accountNo"));

        try (Connection con = DBConnection.getConnection()) {

            String checkSql = "SELECT status FROM accounts WHERE account_no=?";
            PreparedStatement ps = con.prepareStatement(checkSql);
            ps.setInt(1, accountNo);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                resp.sendRedirect("jsp/blockAccount.jsp?error=notfound");
                return;
            }

            String currentStatus = rs.getString("status");
            String newStatus = currentStatus.equals("ACTIVE") ? "BLOCKED" : "ACTIVE";

            String updateSql = "UPDATE accounts SET status=? WHERE account_no=?";
            PreparedStatement ups = con.prepareStatement(updateSql);
            ups.setString(1, newStatus);
            ups.setInt(2, accountNo);
            ups.executeUpdate();

            if ("BLOCKED".equals(newStatus)) {
                resp.sendRedirect("jsp/blockAccount.jsp?msg=blocked");
            } else {
                resp.sendRedirect("jsp/blockAccount.jsp?msg=unblocked");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("jsp/blockAccount.jsp?error=db");
        }
    }
}
