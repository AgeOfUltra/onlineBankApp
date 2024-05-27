package com.app.repo;

import com.app.entities.Account;
import com.app.entities.Transaction;
import com.app.entities.TransactionType;
import com.app.entities.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserUtil {
    private DataSource ds;
    public UserUtil(DataSource ds){
        this.ds = ds;
    }
    public List<String> getAccountNumbers() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        List<String> list = new ArrayList<>();

        try{
            conn = ds.getConnection();
            stmt = conn.createStatement();
            String query = "select account_number from accounts";
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                list.add(rs.getString("account_number"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(conn != null){
                conn.close();
            }
            if(stmt != null){
                stmt.close();
            }
        }
        return list;
    }

    public void saveUser(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = ds.getConnection();
            String query  ="insert into users(username, password, full_name, email, phone_number) values(?,?,?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhoneNumber());

            int res = stmt.executeUpdate();
            if(res > 0){
                System.out.println("User saved");
            }
        }catch (SQLException e){
            System.out.println("Failed to save user");
        }
    }
    public User customerDetails(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        User user = null;
        try{
            conn = ds.getConnection();
            String query = "select * from users where username = ? and password= ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,username );
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phone_number"));
            }
            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(conn != null){
                conn.close();
            }
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public int getUserId(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = -1;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            String query = "select user_id from users where username = ? and password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,username);
            stmt.setString(2,password);
            rs = stmt.executeQuery();
            if(rs.next()){
                result = rs.getInt("user_id");
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(conn != null){
                conn.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(rs != null){
                rs.close();
            }
        }
    }
    public Account accountDetailsByUserId(int id) throws SQLException {
        Connection con= null;
        Account account = null;
        PreparedStatement stmt = null;
        try{
            con = ds.getConnection();
            String query  = "select * from accounts where user_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                account = new Account();
                account.setAid(rs.getInt("account_id"));
                account.setUserId(rs.getInt("user_id"));
                account.setAccountNumber(rs.getString("account_number"));
                account.setAccountType(rs.getString("account_type"));
                account.setBalance(rs.getDouble("balance"));
            }
            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(con != null){
                con.close();

            }
            if(stmt != null){
                stmt.close();
            }
        }
    }

    public Collection<Transaction> customerTransactions(int account_id) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        Collection<Transaction> transactions = new ArrayList<>();
        try{
            con = ds.getConnection();
            String query = "select * from transactions where account_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, account_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setTransactionType(TransactionType.valueOf(rs.getString("transaction_type")));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setTransactionDate(rs.getDate("transaction_date"));
                transaction.setDescription(rs.getString("description"));
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(con!=null){
                con.close();
            }
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public void deleteAccount(Connection connection,String account_number) throws SQLException {
        String query = "DELETE FROM accounts WHERE account_number = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, account_number);
            int res = ps.executeUpdate();
            if(res > 0){
                System.out.println("Account deleted successfully");

            }else{
                System.out.println("Account deletion failed");
            }
        }

    }
    public void deleteUser( Connection connection ,String username, String password) throws SQLException {
        String query = "DELETE FROM users WHERE username=? and password=? ";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            int res = statement.executeUpdate();
            if (res > 0) {
                System.out.println("User deleted successfully");
            } else {
                System.err.println("Failed to delete account: Account not found.");
            }
        }
    }
    public void saveAccount(int userId,String account_number) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = ds.getConnection();
            String query  ="insert into accounts(user_id, account_number, account_type, balance) values(?,?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,userId );
            stmt.setString(2,account_number);
            stmt.setString(3,"Savings");
            stmt.setDouble(4,0.0);

            int res = stmt.executeUpdate();
            if(res > 0){
                System.out.println("Account saved");
            }
        }catch (SQLException e){
            System.out.println("Failed to save user");
        }
    }
    public void saveTransaction(Transaction transaction) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = ds.getConnection();
            String query ="insert into transactions(account_id, transaction_type, amount, description) values(?,?,?,?)";
            ps= con.prepareStatement(query);
            ps.setInt(1,transaction.getAccountId());
            ps.setString(2, String.valueOf(transaction.getTransactionType()));
            ps.setDouble(3,transaction.getAmount());
            ps.setString(4, transaction.getDescription());
            int res = ps.executeUpdate();
            if(res > 0){
                System.out.println("Transaction Saved");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(con!=null){
                con.close();
            }
            if(ps != null){
                ps.close();
            }
        }

    }
    public void updateAccount(String account_number,double amount) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = ds.getConnection();
            String query ="update accounts set balance = ? where account_number = ?";
            ps = con.prepareStatement(query);
            ps.setDouble(1,amount);
            ps.setString(2,account_number);
            int res = ps.executeUpdate();
            if(res > 0){
                System.out.println("Account updated successfully");
            }
        } catch (SQLException e) {
            System.out.println("Failed to save Transaction "+e.getMessage());
        }
        finally {
            if(con!=null){
                con.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }

}
