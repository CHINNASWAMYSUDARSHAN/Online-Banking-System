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
    <title>Admin Dashboard</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin-base.css">

    <!-- base admin layout -->
</head>
<body>

<div class="dashboard">

    <div class="header">
        <h2>Admin Dashboard</h2>
        <div class="user">
            Logged in as <strong><%= session.getAttribute("username") %></strong>
        </div>
    </div>

    <div class="operations">

        <a class="card single" href="<%= request.getContextPath() %>/viewUser">
            <div class="icon">👤</div>
            <h3>View User</h3>
            <p>Search a single user</p>
        </a>

        <a class="card users" href="<%= request.getContextPath() %>/viewAllUsers">
            <div class="icon">👥</div>
            <h3>All Users</h3>
            <p>View all registered users</p>
        </a>

        <a class="card accounts" href="<%= request.getContextPath() %>/viewAllAccounts">
            <div class="icon">🏦</div>
            <h3>All Accounts</h3>
            <p>View all bank accounts</p>
        </a>

        <a class="card block" href="<%= request.getContextPath() %>/blockAccount">
            <div class="icon">🚫</div>
            <h3>Block / Unblock</h3>
            <p>Control account access</p>
        </a>

    </div>

    <div class="logout">
        <a href="<%= request.getContextPath() %>/logout">Logout</a>
    </div>

</div>

</body>
</html>
