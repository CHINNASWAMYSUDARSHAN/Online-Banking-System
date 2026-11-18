<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Online Banking System</title>
    <link rel="stylesheet" href="../css/style.css">

    <script>
        function validateRegister() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var error = document.getElementById("error");

            if (username === "" || password === "") {
                error.innerText = "Please fill all fields";
                return false;
            }

            error.innerText = "";
            return true;
        }
    </script>
</head>

<body>

<div class="container">

    <h2>Online Banking System</h2>
    <h3>User Registration</h3>

    <p id="error" style="color:red; text-align:center;"></p>

    <form action="/OnlineBankingSystem/registerServlet" method="post" onsubmit="return validateRegister();">

        <div class="field">
            <label>Username</label>
            <input type="text" id="username" name="username">
        </div>

        <div class="field">
            <label>Password</label>
            <input type="password" id="password" name="password">
        </div>

        <input type="submit" value="Register" class="btn">

    </form>

    <p class="link">
        Already have an account?
        <a href="login.jsp">Login here</a>
    </p>

</div>

</body>
</html>
