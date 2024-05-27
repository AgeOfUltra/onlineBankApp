package com.app.servlets;

import com.app.entities.Account;
import com.app.entities.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home/balance")
public class BalanceController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(20*60);
        Account account = (Account) session.getAttribute("account");
        User user = (User) session.getAttribute("user");
        String account_number = request.getParameter("accountNumber");
        String password = request.getParameter("password");
        System.out.println("****************Balance Controller **************");
//        user = user==null?new User():user;
//        account = account==null?new Account():account;
        System.out.println(user);
        System.out.println(account);
        System.out.println("account number: "+account_number);
        System.out.println("password: "+password);
        if(account == null || user == null) {
            response.sendRedirect(request.getContextPath() + "/signup.jsp");
            return;
        }
        if(account_number.isEmpty() || password.isEmpty()){
            session.setAttribute("message", "Please enter account number/ password");
            response.sendRedirect(request.getContextPath()+ "/home/balance.jsp");
            return;
        }

        if (account_number.equals(account.getAccountNumber()) && password.equals(user.getPassword())) {
            session.setAttribute("balance", account.getBalance());
            response.sendRedirect(request.getContextPath() + "/home/balance.jsp");
        }else{
            session.setAttribute("messageInvalid", "Invalid account number/password");
            response.sendRedirect(request.getContextPath() + "/home/balance.jsp");
            return;
        }

    }
}
