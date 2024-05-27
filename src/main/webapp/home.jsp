<%--
  Created by IntelliJ IDEA.
  User: sarad
  Date: 05-05-2024
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Srikanth Bank</title>
    <link rel="icon" type="image/x-icon" href="images/S%20Bank.svg">
    <link rel="stylesheet" href="css/home_style.css">
</head>
<body>
<header>
    <div class="logo">
        <img src="images/S Bank.svg" alt="Bank Logo">
    </div>
    <div class="bank-name">Srikanth Bank</div>
    <button class="logout-btn" onclick="logout()">Logout</button>
</header>

<main>
    <div class="button-container">
        <button id="account-btn" class="action-btn">Account Management</button>
        <button id="transaction-btn" class="action-btn">Transaction History</button>
        <button id="balance-btn" class="action-btn">Balance Enquiry</button>
        <button id="fund-transfer-btn" class="action-btn">Fund Transfer</button>
    </div>
</main>
<script>
    document.getElementById('account-btn').addEventListener('click', function () {
        window.location.href = '/home/account.jsp';
    });
    document.getElementById('transaction-btn').addEventListener('click', function() {
        window.location.href = '/home/history.jsp';
    });
    document.getElementById('balance-btn').addEventListener('click', function () {
        window.location.href = '/home/balance.jsp';
    });
    document.getElementById('fund-transfer-btn').addEventListener('click', function () {
        window.location.href = '/home/fundtransfer.jsp';
    });

    window.history.pushState(null, "", window.location.href);
    window.onpopstate = function() {
    window.history.pushState(null, "", window.location.href);
    alert("You cannot go back to the login or signup page.");};

    function logout(){
        window.location.href='  logout'
    }
</script>
</body>
</html>
