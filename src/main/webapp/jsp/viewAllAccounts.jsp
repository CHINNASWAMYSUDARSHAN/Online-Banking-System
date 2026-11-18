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
    <title>View All Accounts</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin-common.css">
    
</head>
<body>

<h2>All Bank Accounts</h2>

<%
    java.util.List accounts = (java.util.List) request.getAttribute("accounts");

    if (accounts == null || accounts.isEmpty()) {
%>
        <p>No accounts found.</p>
<%
    } else {
%>
        <table border="1" cellpadding="10">
            <tr>
                <th>Account No</th>
                <th>User ID</th>
                <th>Balance</th>
                <th>Status</th>
            </tr>

            <%
                for (Object obj : accounts) {
                    String[] acc = (String[]) obj;
            %>
                <tr>
                    <td><%= acc[0] %></td>
                    <td><%= acc[1] %></td>
                    <td><%= acc[2] %></td>
                    <td><%= acc[3] %></td>
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
