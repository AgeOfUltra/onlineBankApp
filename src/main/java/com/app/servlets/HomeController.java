package com.app.servlets;

import com.app.entities.Account;
import com.app.entities.Transaction;
import com.app.entities.User;
import com.app.repo.UserUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    @Resource(name = "jdbc/bank")
    private DataSource dataSource;

    Collection<Transaction> transactions;
    protected UserUtil util;

    public void init(){
        util = new UserUtil(dataSource);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        try {
            transactions = util.customerTransactions(account.getAid());
            System.out.println(transactions);
            session.setAttribute("transactions", transactions);

            res.sendRedirect(req.getContextPath()+"/home.jsp");
        } catch (SQLException | IOException e) {
            System.out.println("Error while getting the transactions");
            session.setAttribute("errorTransaction","Could not get transactions");
            res.sendRedirect(req.getContextPath()+"/home.jsp");
            System.out.println(e.getMessage());
        }
    }
}
