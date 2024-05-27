<%@ page import="java.util.Collection" %>
<%@ page import="com.app.entities.Transaction" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: sarad
  Date: 15-05-2024
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction History - Srikanth Bank</title>
    <link rel="stylesheet" href="../css/history_style.css">
    <%
        Collection<Transaction> transactions =(Collection<Transaction>) session.getAttribute("transactions");

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
    <div class="transaction-history-container">
        <div class="title">Transaction History</div>
        <c:if test="${not empty sessionScope.errorTransaction}">
            <p style="color: red ">${sessionScope.errorTransaction}</p>
            <%
                session.removeAttribute("errorTransaction");
            %>
        </c:if>
        <table class="transaction-table">
            <thead>
            <tr>
                <th>Transaction ID</th>
                <th>Transaction Type</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="<%=transactions%>" var="transaction">
                    <tr>
                        <td>${transaction.transactionId}</td>
                        <td>${transaction.transactionType}</td>
                        <td>${transaction.amount}</td>
                        <td>${transaction.transactionDate}</td>
                        <td>${transaction.description}</td>
                    </tr>
                </c:forEach>
<%--            <tr>--%>
<%--                <td>12345</td>--%>
<%--                <td>Withdraw</td>--%>
<%--                <td>$100.00</td>--%>
<%--                <td>ATM Withdrawal</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>12346</td>--%>
<%--                <td>Savings</td>--%>
<%--                <td>$500.00</td>--%>
<%--                <td>Salary Deposit</td>--%>
<%--            </tr>--%>
            <!-- More rows as needed -->
            </tbody>
        </table>
    </div>
</main>

<script>
    function logout() {
        window.location.href='<%=request.getContextPath()%>/logout';
    }
</script>
</body>
</html>

