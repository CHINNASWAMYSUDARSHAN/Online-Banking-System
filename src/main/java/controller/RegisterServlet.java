package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBConnection;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet{
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

     String username = req.getParameter("username");
     String password = req.getParameter("password");

     resp.setContentType("text/html");
     PrintWriter out = resp.getWriter();
     
     try {
         Connection con = DBConnection.getConnection();

         // 1️⃣ Check if username already exists
         String checkSql = "SELECT id FROM users WHERE username = ?";
         PreparedStatement psCheck = con.prepareStatement(checkSql);
         psCheck.setString(1, username);
         ResultSet rs = psCheck.executeQuery();

         if (rs.next()) {
             out.println("<h3 style='color:red'>Username already exists</h3>");
             out.println("<a href='/OnlineBankingSystem/jsp/register.jsp'>Try Again</a>");
             return;
         }

         // 2️⃣ Insert new user
         String insertSql = "INSERT INTO users(username, password) VALUES (?, ?)";
         PreparedStatement psInsert = con.prepareStatement(insertSql);
         psInsert.setString(1, username);
         psInsert.setString(2, password);

         psInsert.executeUpdate();

         resp.sendRedirect("/OnlineBankingSystem/jsp/registerSuccess.jsp");


     } catch (Exception e) {
         e.printStackTrace();
         out.println("<h3>Error during registration</h3>");
     }
}
}
