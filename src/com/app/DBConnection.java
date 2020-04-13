package com.app;

import java.sql.*;

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

    public static ResultSet query(String sql) {
        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
