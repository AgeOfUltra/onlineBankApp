<%--
  Created by IntelliJ IDEA.
  User: sarad
  Date: 14-05-2024
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Login | Srikanth Bank </title>
  <link rel="icon" type="image/x-icon" href="images/S%20Bank.svg">
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
  <div class="logo">
    <img src="images/S Bank.svg" alt="Bank Logo">
  </div>
  <div class="bank-name">Srikanth Bank</div>
  <button class="logout-btn" style="visibility: hidden">Logout</button>
</header>
<div class="container">
  <div class="login form">
    <header>Login</header>
    <form action="login" method="post">
      <c:if test="${not empty sessionScope.loginError}">
        <p style="color: red">${sessionScope.loginError}</p>
        <%
          session.removeAttribute("loginError");
        %>
      </c:if>
      <label>
        <input type="text" name="username" placeholder="Enter your username">
      </label>
      <label>
        <input type="password" name="password" placeholder="Enter your password">
      </label>
      <input type="submit" class="button" value="Login">
    </form>
    <div class="signup">
        <span class="signup">Don't have an account?
         <label><a href="signup.jsp">Signup</a></label>
        </span>
    </div>
  </div>
</div>
</body>
</html>
