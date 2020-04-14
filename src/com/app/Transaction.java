package com.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String ID, AccountID, Date, Amount, Description;

    public Transaction(String id, String date, String amount, String description) {
        ID = id;
        Date = date;
        Amount = amount;
        Description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public static void newTransaction(int accountID, int amount, String description) {
        DateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        java.util.Date dateObj = new Date();

        DBConnection.run(String.format("INSERT INTO transactions (AccountID, Date, Amount, Description) VALUES (%s, '%s', %s, '%s')",
                accountID,
                df.format(dateObj),
                amount,
                description
        ));
    }
}
