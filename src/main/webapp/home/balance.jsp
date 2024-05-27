<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
    <title>Balance Enquiry - Srikanth Bank</title>
    <link rel="icon" type="image/x-icon" href="../images/S%20Bank.svg">
    <link rel="stylesheet" href="../css/balance_style.css">
</head>
<body>
<header>
    <div class="logo">
        <img src="../images/S%20Bank.svg" alt="Bank Logo">
    </div>
    <div class="bank-name">Srikanth Bank</div>
    <button class="logout-btn" onclick="logout()">Logout</button>
</header>

<div class="container">
    <div class="login form">
        <header>Balance Inquiry</header>
        <form action="<%=request.getContextPath()%>/home/balance" method="post">
            <c:if test="${not empty sessionScope.message }">
                <p style="color: red"> ${sessionScope.message}</p>
                <%
                    session.removeAttribute("balance");
                    session.removeAttribute("message");
                %>
            </c:if>
            <c:if test="${not empty sessionScope.messageInvalid }">
                <p style="color: red"> ${sessionScope.messageInvalid}</p>
                <%
                    session.removeAttribute("balance");
                    session.removeAttribute("messageInvalid");
                %>
            </c:if>
            <label>
                <input type="text" name="accountNumber" placeholder="Enter your Account Number">
            </label>
            <label>
                <input type="password" name="password" placeholder="Enter your password">
            </label>
            <input type="submit" class="button" value="Get Balance">
            <c:if test="${not empty sessionScope.balance}">
                <label>
                    <input type="text" readonly value="${sessionScope.balance}">
                </label>
                <%
                    session.removeAttribute("balance");
                %>
            </c:if>
        </form>
    </div>
</div>
    <script>
        function logout(){
            window.location.href='<%=request.getContextPath()%>/logout';
        }
    </script>
</body>
</html>

