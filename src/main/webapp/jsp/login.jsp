<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Online Banking System</title>
    <link rel="stylesheet" href="/OnlineBankingSystem/css/style.css">
    
</head>

<body>

<div class="container">

    <h2>Online Banking System</h2>
    <h3>Login</h3>

    <!-- 🔴 ERROR MESSAGE BLOCK -->
    <%
        String error = request.getParameter("error");
        if ("true".equals(error)) {
    %>
        <p style="color:red; text-align:center;">
            Invalid username or password
        </p>
    <%
        } else if ("db".equals(error)) {
    %>
        <p style="color:red; text-align:center;">
            Server error. Please try again later.
        </p>
    <%
        }
    %>

    <p id="error" style="color:red; text-align:center;"></p>

    <form action="/OnlineBankingSystem/login" method="post" onsubmit="return validateLogin();">

        <div class="field">
            <label>Username</label>
            <input type="text" id="username" name="username">
        </div>

        <div class="field">
            <label>Password</label>
            <input type="password" id="password" name="password">
        </div>

        <div style="margin-bottom:10px;">
            <input type="checkbox" onclick="togglePassword()"> Show Password
        </div>

        <input type="submit" id="loginBtn" value="Login" class="btn">

    </form>

    <p class="link">
        New user?
        <a href="register.jsp">Register here</a>
    </p>

</div>

<script>
    function validateLogin() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var error = document.getElementById("error");

        if (username === "" || password === "") {
            error.innerText = "Please enter username and password";
            return false;
        }

        error.innerText = "";
        document.getElementById("loginBtn").disabled = true;
        document.getElementById("loginBtn").value = "Logging in...";
        return true;
    }

    function togglePassword() {
        var pwd = document.getElementById("password");
        pwd.type = (pwd.type === "password") ? "text" : "password";
    }
</script>

</body>
</html>
