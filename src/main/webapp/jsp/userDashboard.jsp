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
    <title>User Dashboard</title>

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: "Segoe UI", Arial, sans-serif;
            background: linear-gradient(135deg, #1d2671, #c33764);
            min-height: 100vh;
        }

        .dashboard {
            width: 900px;
            margin: 60px auto;
            background: #f9f9f9;
            border-radius: 12px;
            padding: 30px;
            box-shadow: 0 15px 30px rgba(0,0,0,0.2);
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
        }

        .header h2 {
            margin: 0;
            color: #2c3e50;
        }

        .user {
            color: #555;
            font-size: 15px;
        }

        .cards {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }

        .card {
            background: white;
            padding: 25px 15px;
            border-radius: 10px;
            text-align: center;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 5px 12px rgba(0,0,0,0.1);
            text-decoration: none;
            color: #2c3e50;
        }

        .card:hover {
            transform: translateY(-6px);
            box-shadow: 0 12px 25px rgba(0,0,0,0.2);
            background: #f0f6ff;
        }

        .card span {
            display: block;
            margin-top: 8px;
            font-size: 14px;
            color: #666;
        }

        .logout {
            margin-top: 30px;
            text-align: center;
        }

        .logout a {
            background: #e74c3c;
            color: white;
            padding: 10px 25px;
            border-radius: 20px;
            text-decoration: none;
            transition: background 0.3s;
            font-size: 14px;
        }

        .logout a:hover {
            background: #c0392b;
        }

        @media (max-width: 900px) {
            .dashboard {
                width: 95%;
            }

            .cards {
                grid-template-columns: repeat(2, 1fr);
            }
        }

        @media (max-width: 600px) {
            .cards {
                grid-template-columns: 1fr;
            }
        }
    </style>

</head>
<body>

<div class="dashboard">

    <div class="header">
        <h2>User Dashboard</h2>
        <div class="user">
            Welcome, <b><%= session.getAttribute("username") %></b>
        </div>
    </div>

    <div class="cards">
        <a href="createAccount.jsp" class="card">
            🏦 Create Account
            <span>Open a new bank account</span>
        </a>

        <a href="checkBalance.jsp" class="card">
            💰 Check Balance
            <span>View available balance</span>
        </a>

        <a href="deposit.jsp" class="card">
            ➕ Deposit
            <span>Add money to account</span>
        </a>

        <a href="withdraw.jsp" class="card">
            ➖ Withdraw
            <span>Withdraw money safely</span>
        </a>

        <a href="transfer.jsp" class="card">
            🔁 Transfer
            <span>Send money to another account</span>
        </a>
    </div>

    <div class="logout">
        <a href="../logout">Logout</a>
    </div>

</div>

</body>
</html>
