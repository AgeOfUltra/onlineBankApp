<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: sarad
  Date: 14-05-2024
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Signup | Srikanth Bank </title>
    <link rel="icon" type="image/x-icon" href="images/S%20Bank.svg">
    <link rel="stylesheet" href="css/signup_style.css">
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
        <header>Sign Up</header>
        <form action="signup" method="post">
            <c:if test="${not empty sessionScope.emailError or not empty sessionScope.passError or not empty sessionScope.phoneError or not empty sessionScope.userSave}">
                <p style="color: red;">Validation errors occurred:</p>
                <c:if test="${not empty sessionScope.emailError}">
                    <p style="color: red;">${sessionScope.emailError}</p>
                    <%
                        session.removeAttribute("emailError");

                    %>
                </c:if>
                <c:if test="${not empty sessionScope.passError}">
                    <p style="color: red;">${sessionScope.passError}</p>
                    <%
                        session.removeAttribute("passError");
                    %>
                </c:if>
                <c:if test="${not empty sessionScope.phoneError}">
                    <p style="color: red;">${sessionScope.phoneError}</p>
                    <%
                        session.removeAttribute("phoneError");
                    %>
                </c:if>
                <c:if test="${not empty sessionScope.userSave}">
                    <p style="color: red"> ${sessionScope.userSave}</p>
                    <%
                        session.removeAttribute("userSave");
                    %>
                </c:if>
            </c:if>
            <label>Enter Full Name</label>
            <label>
                <input type="text" name="name" placeholder="Enter Full Name">
            </label>
            <label>Enter UserName</label>
            <label>
                <input type="text" name="username" placeholder="Enter UserName">
            </label>
            <label>Enter Email</label>
            <label>
                <input type="text" name="email" placeholder="Enter your email">
            </label>
            <label>Enter Phone Number</label>
            <label>
                <input type="text" name="phone" placeholder="Enter Phone number">
            </label>
            <label>Enter Password</label>
            <label>
                <input type="password" name="password" placeholder="Create a password">
            </label>
            <label>Re Enter Password</label>
            <label>
                <input type="password" name="repass" placeholder="Confirm your password">
            </label>

            <input type="submit" class="button" value="Signup">
        </form>
        <div class="signup">
        <span class="signup">Already have an Account?
         <label><a href="login.jsp">Login</a></label>
        </span>
        </div>
    </div>
</div>
<script>
    window.history.pushState(null,"",window.location.href);
    window.onpopstate = function() {
    window.history.pushState(null, "", window.location.href);
    alert("You cannot go back to the login or signup page.");};
</script>
</body>
</html>

