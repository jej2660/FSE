package com.example.fse.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbDAO {
    private Connection conn;
    public DbDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/softw";
            String dbID = "root";
            String dbPassword = "hackerlogin";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        }
        catch(Exception e) {
            conn = null;
            System.out.println("Connection Error");
        }
    }
    public Connection getConnection() {
        return this.conn;
    }

}
