package com.example.fse.repo;

import com.example.fse.config.DbDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public User getUser(String uid){
        String sql = "SELECT * from usertable where uid=?";
        DbDAO dbdao = new DbDAO();
        ResultSet rs;
        User user = new User();
        try {
            PreparedStatement pstmt = dbdao.getConnection().prepareStatement(sql);
            pstmt.setString(1,uid);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUid(rs.getString("uid"));
                user.setKrw(rs.getDouble("krw"));
                user.setBtc(rs.getDouble("btc"));
            }
            return user;
        } catch (SQLException e){
            System.out.println("SQL Error");
        }
        return null;
    }
    public String loginCheck(String uid, String upwd) {
        String sql = "SELECT * from usertable where uid=? and password=?";
        String userid ="";
        DbDAO dbdao = new DbDAO();
        ResultSet rs;
        try {
            PreparedStatement pstmt = dbdao.getConnection().prepareStatement(sql);
            pstmt.setString(1, uid);
            pstmt.setString(2, upwd);
            rs = pstmt.executeQuery();

            while(rs.next()){
                userid = rs.getString("uid");
            }
            return userid;
        } catch (SQLException e) {
            System.out.println("Error");
        }
        return null;
    }
    public boolean registUser(User user){
        String sql = "INSERT into usertable values(null,?,?,1000000,0)";
        DbDAO dbdao = new DbDAO();
        try{
            PreparedStatement pstmt = dbdao.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getUid());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("Error");
        }
        return false;
    }
    public boolean changeKrw(User user, double value){
        String sql = "update usertable set uid=? where krw=?";
        DbDAO dbdao = new DbDAO();
        try{
            PreparedStatement pstmt = dbdao.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getUid());
            pstmt.setDouble(2, user.getKrw() + value);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("sql Error");
        }
        return false;
    }
    public boolean changeBtc(User user, double value){
        String sql = "update usertable set uid=? where btc=?";
        DbDAO dbdao = new DbDAO();
        try{
            PreparedStatement pstmt = dbdao.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getUid());
            pstmt.setDouble(2, user.getBtc() + value);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("sql Error");
        }
        return false;
    }
    public boolean coinBid(User user, double count, double price){
        double totalprice = count * price;
        if( changeBtc(user, count) && changeKrw(user, -totalprice) ) { return true;}
        return false;
    }
    public boolean coinSell(User user, double count, double price){
        double totalprice = count * price;
        if( changeBtc(user, -count) && changeKrw(user,  totalprice) ) { return true;}
        return false;
    }
}
