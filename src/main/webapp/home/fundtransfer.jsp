<%--
  Created by IntelliJ IDEA.
  User: sarad
  Date: 14-05-2024
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction Management</title>
    <link rel="stylesheet" href="../css/home_style.css">
</head>
<body>
<header>
    <div class="logo">
        <img src="../images/S%20Bank.svg" alt="Bank Logo">
    </div>
    <div class="bank-name">Srikanth Bank</div>
    <button class="logout-btn" onclick="logout()">Logout</button>
</header>

<main>
    <div class="button-container">
        <button id="deposit-btn" class="action-btn">Deposit</button>
        <button id="withdraw-btn" class="action-btn">WithDraw</button>
    </div>
</main>
<script>
    document.getElementById('deposit-btn').addEventListener('click', function() {
        window.location.href = '/home/fundTransfer/deposit.jsp';
    });
    document.getElementById('withdraw-btn').addEventListener('click', function() {
        window.location.href = '/home/fundTransfer/withdraw.jsp';
    });
    function logout(){
        window.location.href='<%=request.getContextPath()%>/logout';
    }
</script>
</body>
</html>
