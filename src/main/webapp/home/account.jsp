<%--
  Created by IntelliJ IDEA.
  User: sarad
  Date: 14-05-2024
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.app.entities.Account" %>
<%@ page import="com.app.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Management - Srikanth Bank</title>
    <link rel="icon" type="image/x-icon" href="../images/S%20Bank.svg">
    <link rel="stylesheet" href="../css/account_style.css">
    <%
        User user = (User)session.getAttribute("user");
        Account account = (Account) session.getAttribute("account");
        if (user == null || account == null) {
            response.sendRedirect(request.getContextPath() + "/signup.jsp");
            return;
        }
    %>
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
    <div class="form-container">
        <form action="<%=request.getContextPath()%>/home/updateAccountDetails" method="post">
            <h2>Account Details</h2>
            <div class="input-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="<%=user.getFullName()%>" readonly>
            </div>
            <div class="input-group">
                <label for="username">User Name:</label>
                <input type="text" id="username" name="username" value="<%= user.getUsername()%>" readonly>
            </div>
            <div class="input-group">
                <label for="phone">Phone Number:</label>
                <input type="text" id="phone" name="phone" value="<%=user.getPhoneNumber() %>" readonly>
            </div>
            <div class="input-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<%=user.getEmail() %>" readonly>
            </div>
            <div class="input-group">
                <label for="accountNumber">Account Number:</label>
                <input type="text" id="accountNumber" name="accountNumber" value="<%= account.getAccountNumber()%>" readonly>
            </div>

            <div class="button-group">
                <label>
                    <input class="edit-btn" type="submit" value="Delete">
                </label>
            </div>
        </form>
    </div>
</main>
<script>

    function logout(){
        window.location.href='<%=request.getContextPath()%>/logout';
    }

</script>
</body>
</html>
