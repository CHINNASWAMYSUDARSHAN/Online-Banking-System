<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    if (session == null || session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Transfer Money</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/operations.css">

    <% if (request.getAttribute("msg") != null) { %>
        <meta http-equiv="refresh" content="3;URL=jsp/userDashboard.jsp">
    <% } %>
</head>
<body>
<div class="op-card">


<h2>Transfer Money</h2>

<%
    if (request.getAttribute("msg") != null) {
%>
    <p style="color:green;"><%= request.getAttribute("msg") %></p>

    <% if (request.getAttribute("amount") != null) { %>
        <p>Transferred Amount: ₹ <%= request.getAttribute("amount") %></p>
        <p>Remaining Balance: ₹ <%= request.getAttribute("balance") %></p>
    <% } %>

    <p>Redirecting to dashboard...</p>

<%
    } else {
%>
    <form action="<%= request.getContextPath() %>/transfer" method="post">
        Receiver Account Number:
        <input type="number" name="toAccount" required>
        <br><br>

        Amount:
        <input type="number" name="amount" min="1" required>
        <br><br>

        <input type="submit" value="Transfer">
    </form>
<%
    }
%>
</div>
</body>
</html>
