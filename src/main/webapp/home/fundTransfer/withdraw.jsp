<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: sarad
  Date: 15-05-2024
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Withdraw form | Srikanth Bank </title>
    <link rel="icon" type="image/x-icon" href="../../images/S%20Bank.svg">
    <link rel="stylesheet" href="../../css/tranfer.css">
</head>
<body>
<header>
    <div class="logo">
        <img src="../../images/S%20Bank.svg" alt="Bank Logo">
    </div>
    <div class="bank-name">Srikanth Bank</div>
    <button class="logout-btn" onclick="logout()">Logout</button>
</header>
<div class="container">
    <div class="login form">
        <header>WithDraw Form</header>
        <form action="<%=request.getContextPath()%>/home/fundTransfer/withdraw" method="post">
            <label>
                <input type="text" name="accountNumber" placeholder="Enter your accountNumber">
            </label>
            <label>
                <input type="password" name="password" placeholder="Enter your password">
            </label>
            <label>
                <input type="number" name="withdraw_amt" placeholder="Enter your WithDraw amount">
            </label>
            <label>
                <input type="text" name="description" placeholder="Enter your reason">
            </label>
            <input type="submit" class="button" value="Withdraw">
            <c:if test="${not empty sessionScope.errorInput}">
                <p style="color: red;"> ${sessionScope.errorInput}</p>
                <% session.removeAttribute("errorInput");%>
            </c:if>
            <c:if test="${not empty sessionScope.errorAmt}">
                <p style="color: red;"> ${sessionScope.errorAmt}</p>
                <% session.removeAttribute("errorAmt");%>
            </c:if>
            <c:if test="${not empty sessionScope.success}">
                <p style="color: green;"> ${sessionScope.success}</p>
                <%
                    session.removeAttribute("success");
                %>
            </c:if>
            <c:if test="${not empty sessionScope.errorInvalid}">
                <p style="color: red;"> ${sessionScope.errorInvalid}</p>
                <%
                    session.removeAttribute("errorInvalid");
                %>
            </c:if>
            <c:if test="${not empty sessionScope.errorTrans}">
                <p style="color: red"> ${sessionScope.errorTrans}</p>
                <% session.removeAttribute("errorTrans");%>
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
