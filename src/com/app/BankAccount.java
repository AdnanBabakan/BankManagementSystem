package com.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class BankAccount {
    public static int generateAccountNumber() {
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999999);
        int accountNumbers = 0;

        try {
            ResultSet accounts = DBConnection.query(String.format("SELECT * FROM accounts WHERE AccountNumber = %s", randomNum));
            while (accounts.next()) {
                accountNumbers++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (accountNumbers == 0) {
            return randomNum;
        } else {
            return generateAccountNumber();
        }
    }
}
