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
    <title>View All Users</title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin-common.css">
   
</head>
<body>
<h2>All Registered Users</h2>

<%
    java.util.List users = (java.util.List) request.getAttribute("users");

    if (users == null || users.isEmpty()) {
%>
        <p>No users found.</p>
<%
    } else {
%>
        <table border="1" cellpadding="10">
            <tr>
                <th>User ID</th>
                <th>Username</th>
            </tr>

            <%
                for (Object obj : users) {
                    String[] user = (String[]) obj;
            %>
                <tr>
                    <td><%= user[0] %></td>
                    <td><%= user[1] %></td>
                </tr>
            <%
                }
            %>
        </table>
<%
    }
%>

<br>
<a href="jsp/adminDashboard.jsp">Back to Admin Dashboard</a>
</body>
</html>
