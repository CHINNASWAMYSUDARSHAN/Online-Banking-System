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
    <title>Check Balance</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/operations.css">
    <% if (request.getAttribute("balance") != null
        || request.getAttribute("msg") != null) { %>
        <meta http-equiv="refresh" content="3;URL=jsp/userDashboard.jsp">
    <% } %>
</head>
<body>
<div class="op-card">


<h2>Check Balance</h2>

<%
    if (request.getAttribute("balance") != null) {
%>
    <p><b>Account Number:</b> <%= request.getAttribute("accountNo") %></p>
    <p><b>Balance:</b> ₹ <%= request.getAttribute("balance") %></p>
    <p>Redirecting to dashboard...</p>

<%
    } else if (request.getAttribute("msg") != null) {
%>
    <p style="color:red;"><%= request.getAttribute("msg") %></p>
    <p>Redirecting to dashboard...</p>

<%
    } else {
%>
    <form action="<%= request.getContextPath() %>/checkBalance" method="post">
        <input type="submit" value="Check Balance">
    </form>
<%
    }
%>
</div>
</body>
</html>
