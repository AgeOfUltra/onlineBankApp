package com.app.servlets;

import com.app.entities.Account;
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

@WebServlet("/login")
public class EntryController extends HttpServlet {
    @Resource(name = "jdbc/bank")
    private DataSource dataSource;

    private static User user;
    private Account account ;

    private UserUtil utils;
    @Override
    public void init(){
        utils = new UserUtil(dataSource);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)  {
        String username = req.getParameter("username");
        String passcode = req.getParameter("password");
        HttpSession session = req.getSession();
        System.out.println(username+" "+passcode);
        try {
            user =utils.customerDetails(username,passcode);
            int userId = utils.getUserId(username,passcode);
            System.out.println(user == null ? user = null : user.toString());
            System.out.println("Account _ number "+ userId);
            account = utils.accountDetailsByUserId(userId);
            System.out.println(account == null ? account= new Account():account);
            if(user != null && account != null){
                session.setAttribute("user", user);
                session.setAttribute("account", account);
                res.sendRedirect("/home");
            }
            else {
                session.setAttribute("loginError", "Account not found. Please check your credentials.");
                res.sendRedirect( "login.jsp"); // Redirect back to login page
            }
//            session. setMaxInactiveInterval(20*60);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }

}
