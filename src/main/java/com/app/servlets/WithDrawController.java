package com.app.servlets;

import com.app.entities.Account;
import com.app.entities.Transaction;
import com.app.entities.TransactionType;
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

@WebServlet("/home/fundTransfer/withdraw")
public class WithDrawController extends HttpServlet {
    @Resource(name = "jdbc/bank")
    private DataSource dataSource;

    protected static UserUtil util;
    public void init(){
        util= new UserUtil(dataSource);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        String account_number = req.getParameter("accountNumber");
        String password = req.getParameter("password");
        String amount = req.getParameter("withdraw_amt");
        String description = req.getParameter("description");
        User user = (User)session.getAttribute("user");
        Account account = (Account) session.getAttribute("account");
        System.out.println(user+"\n"+account);
        System.out.println(amount);
        System.out.println(password+" "+account);
        if( account_number==null || password==null || amount==null || account_number.isEmpty() || password.isEmpty() || amount.isEmpty()) {
            session.setAttribute("errorInput", "Enter  Credentials and/or amount");
            res.sendRedirect(req.getContextPath()+"/home/fundTransfer/withdraw.jsp");
            return;
        }
        if(!password.equals(user.getPassword()) ){
            session.setAttribute("errorInvalid", "Invalid password");
            res.sendRedirect(req.getContextPath()+"/home/fundTransfer/withdraw.jsp");
            return;
        }
        if(!account_number.equals(account.getAccountNumber()))
        {
            session.setAttribute("errorInvalid", "Invalid Account");
            res.sendRedirect(req.getContextPath()+"/home/fundTransfer/withdraw.jsp");
            return;
        }
        double withdraw_amt;
        try {
            withdraw_amt = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            session.setAttribute("errorAmt", "Invalid Amount");
            res.sendRedirect(req.getContextPath()+"/home/fundTransfer/withdraw.jsp");
            return;
        }
        System.out.println("withdraw_amt: "+withdraw_amt);
        if(withdraw_amt>account.getBalance()){
            session.setAttribute("errorAmt", "Insufficient Amount");
            res.sendRedirect(req.getContextPath()+"/home/fundTransfer/withdraw.jsp");
            return;
        }
        Transaction transaction = new Transaction();
        transaction.setAccountId(account.getAid());
        transaction.setAmount(withdraw_amt);
        transaction.setTransactionType(TransactionType.Withdrawal);
        transaction.setDescription(description);
        System.out.println("Transaction check "+transaction);

        try{
            util.saveTransaction(transaction);
            account.setBalance(account.getBalance()-withdraw_amt);
            System.out.println("Second check verify : "+account);
            util.updateAccount(account.getAccountNumber(), account.getBalance());
            System.out.println(account);
            session.setAttribute("success","Transaction Success!");
            res.sendRedirect(req.getContextPath()+"/home/fundTransfer/withdraw.jsp");
        } catch (SQLException e) {
            session.setAttribute("errorTrans", "Failed Transaction");
            res.sendRedirect(req.getContextPath()+"/home/fundTransfer/withdraw.jsp");
            System.out.println("failed to save transaction "+e.getMessage());
        }


    }

}
