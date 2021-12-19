package com.example.fse.config;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * 데이터베이스 연결을 담당하는 class
 *
 * @author JangJaeWon
 * @since 1.0
 */
public class DbDAO {
    /**
     * database Connection 변수
     */
    private Connection conn;
    /**
     * 생성자
     * @throws java.sql.SQLException 데이터베이스 연결중 에러 발생
     */
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
    /**
     *
     *
     * @return database 연결 겍체 반환
     */
    public Connection getConnection() {
        return this.conn;
    }

}
