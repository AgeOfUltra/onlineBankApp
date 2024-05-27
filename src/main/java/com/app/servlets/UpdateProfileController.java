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
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/home/updateAccountDetails")
public class UpdateProfileController extends HttpServlet {
    @Resource(name = "jdbc/bank")
    private DataSource dataSource;

    private UserUtil userUtil;

    public void init() {
        userUtil = new UserUtil(dataSource);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        User user = (User) session.getAttribute("user");
//        String account_number = account.getAccountNumber();

        try(Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try{
                userUtil.deleteAccount(connection,account.getAccountNumber());
                userUtil.deleteUser(connection, user.getUsername(),user.getPassword());
                connection.commit();
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/logout");
            }catch (SQLException e){
                connection.rollback();
                System.out.println("Failed to delete the account or user: " + e.getMessage());
                response.sendRedirect(request.getContextPath() + "/home/account.jsp");
            }

        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/home/account.jsp");
        }

    }
}
