package com.app.servlets;

import com.app.entities.Account;
import com.app.entities.User;
import com.app.repo.UserUtil;
import com.app.service.AccountNumberGenerator;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/signup")
public class RegistrationController  extends HttpServlet {

    @Resource(name = "jdbc/bank")
    private DataSource dataSource;
    private Account account;
    private UserUtil utils;
    public void init(){
        utils = new UserUtil(dataSource);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String repass = req.getParameter("repass");
        System.out.println(username+" "+email+" "+password+" "+phone+" "+repass);
        boolean emailValid = isEmailValid(email);
        boolean phoneNumber = phoneNumberValid(phone);
        if (!password.equals(repass) || !emailValid || !phoneNumber) {
            if (!emailValid) {
                session.setAttribute("emailError", "Invalid email address");
            } else if(!phoneNumber) {
                session.setAttribute("phoneError", "Invalid phone number");
            }else{
                session.setAttribute("passError", "Passwords do not match");
            }
            // Forward to signup.jsp to display error messages
            RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
            dispatcher.forward(req, res);
            return;
        }

        String account_number= null;
        try {
            AccountNumberGenerator generator =  new AccountNumberGenerator(dataSource);
            account_number = generator.generateAccountNumber();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        User user = new User();
        user.setFullName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phone);
        try {
            utils.saveUser(user);
            int userId = getId(user.getUsername(),user.getPassword());
            createAccount(userId,account_number);
            account = getAccount(userId);
            session.setAttribute("user",user);
            session.setAttribute("account",account);
            res.sendRedirect("/home");
        } catch (SQLException e) {
            req.setAttribute("userSave","some thing went wrong");
            res.sendRedirect("signup.jsp");
            System.out.println("Error while saving user \n "+e.getMessage());
        }

        System.out.println(name+" "+username+" "+email+" "+password+" "+phone+" "+repass+" "+account_number);

    }
    public boolean isEmailValid(String email){
        Matcher match = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$").matcher(email);
        return match.matches();
    }
    public boolean phoneNumberValid(String phone){
        Matcher matcher = Pattern.compile("^[0-9]{10}$").matcher(phone);
        return matcher.matches();
    }

    public void createAccount(int userId,String accountNumber) throws SQLException {
        utils.saveAccount(userId,accountNumber);
    }
    public int getId(String username, String password) throws SQLException {
       return utils.getUserId(username, password);
    }
    public Account getAccount(int userId) throws SQLException {
        return utils.accountDetailsByUserId(userId);
    }

}
