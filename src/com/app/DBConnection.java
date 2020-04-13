package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static Connection conn = null;
    private static String DBURL;

    public static void connect(String DBFile) {
        try {
            DBURL = "jdbc:sqlite:" + DBFile;
            conn = DriverManager.getConnection(DBURL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void run(String sql) {
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
