<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    if (session == null || session.getAttribute("role") == null
        || !"admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>View User</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin-common.css">
    
</head>
<body>

<h2>View Single User</h2>

<form action="<%= request.getContextPath() %>/viewUser" method="post">
    <label>Enter Username:</label><br>
    <input type="text" name="username" required>
    <br><br>
    <input type="submit" value="Search User">
</form>

<br>

<%
    if (request.getAttribute("error") != null) {
%>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<%
    }
%>

<%
    if (request.getAttribute("userId") != null) {
%>
    <h3>User Details</h3>
    <p>User ID: <%= request.getAttribute("userId") %></p>
    <p>Username: <%= request.getAttribute("username") %></p>
<%
    }
%>

<br>
<a href="jsp/adminDashboard.jsp">Back to Admin Dashboard</a>

</body>
</html>
