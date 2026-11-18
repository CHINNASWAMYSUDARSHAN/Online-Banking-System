<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    if (session == null || !"admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Block / Unblock Account</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin-common.css">
    
</head>
<body>

<h2>Block / Unblock Bank Account</h2>

<form action="<%=request.getContextPath()%>/blockAccount" method="post">
    <label>Account Number:</label><br>
    <input type="number" name="accountNo" required><br><br>
    <input type="submit" value="Submit">
</form>

<br>

<%
    if ("notfound".equals(request.getParameter("error"))) {
%>
    <p style="color:red;">Account not found</p>
<%
    }
    if ("blocked".equals(request.getParameter("msg"))) {
%>
    <p style="color:red;">Account blocked successfully</p>
<%
    }
    if ("unblocked".equals(request.getParameter("msg"))) {
%>
    <p style="color:green;">Account unblocked successfully</p>
<%
    }
%>

<br>
<a href="adminDashboard.jsp">Back to Admin Dashboard</a>

</body>
</html>
