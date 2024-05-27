package com.app.service;

import com.app.repo.UserUtil;
import jakarta.annotation.Resource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;


public class AccountNumberGenerator {
    private Random random;

    private final List<String> accountNumbers;
    public AccountNumberGenerator(DataSource dataSource) throws SQLException {
        UserUtil util = new UserUtil(dataSource);
        random = new Random();
        accountNumbers = util.getAccountNumbers();
    }

    public String generateAccountNumber() {
        String result = null;
        String id = String.format("%07d", random.nextInt(10000000));
        String accNumber = "SAV".concat(id);
        if(!accountNumbers.contains(accNumber)) {
            result = accNumber;
        }else{
            return generateAccountNumber();
        }
        return result;
    }
}
