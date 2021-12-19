package com.example.fse.repo;

import com.example.fse.config.DbDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User 정보와 상화작용 하는 함수들 Data Access Obj
 *
 * @author JangJaeWon
 * @since 1.0
 */
public class UserDAO {
    /**
     *
     * uid 를 가지고 User 겍체를 얻어오는 기능
     * @param uid 유저 아이디.
     * @return user 겍체
     */
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
    /**
     * 로그인 유효성 여부 체크
     * @param uid 사용자가 입력한 아이디
     * @param upwd 사용자가 입력한 패스워드
     * @return 로그인 성공시 성공한 uid 리턴
     */
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
    /**
     * 유저 생성
     * @param user 유저 정보를 담은 user 겍체입니다.
     * @return 회원가입 성공 여부
     */
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
    /**
     * krw 변경
     * @param user 유저겍체 입니다.
     * @param value krw 금액입니다
     * @return 성공 여부
     */
    public boolean changeKrw(User user, double value){
        String sql = "update usertable set krw=? where uid=?";
        DbDAO dbdao = new DbDAO();
        try{
            PreparedStatement pstmt = dbdao.getConnection().prepareStatement(sql);
            pstmt.setString(2, user.getUid());
            pstmt.setDouble(1, user.getKrw() + value);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("sql Error");
        }
        return false;
    }
    /**
     * btc 변경
     * @param user 유저겍체 입니다.
     * @param value btc 금액입니다
     * @return 성공 여부
     */
    public boolean changeBtc(User user, double value){
        String sql = "update usertable set btc=? where uid=?";
        DbDAO dbdao = new DbDAO();
        try{
            PreparedStatement pstmt = dbdao.getConnection().prepareStatement(sql);
            pstmt.setString(2, user.getUid());
            pstmt.setDouble(1, user.getBtc() + value);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("sql Error");
        }
        return false;
    }
    /**
     * krw, btc 동시 변경
     * @param user 유저겍체 입니다.
     * @param krw krw 금액입니다
     * @param btc btc 금액입니다
     * @return 성공 여부
     */
    public boolean changeUserInfo(User user,double krw, double btc){
        String sql = "update usertable set btc=?,krw=? where uid=?";
        DbDAO dbdao = new DbDAO();
        try{
            PreparedStatement pstmt = dbdao.getConnection().prepareStatement(sql);
            pstmt.setDouble(1, btc);
            pstmt.setDouble(2, krw);
            pstmt.setString(3, user.getUid());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("sql Error");
        }
        return false;
    }
    /**
     * 매수
     * @param user 유저겍체 입니다.
     * @param count 거래 수량입니다.
     * @param price 거래 가격입니다.
     * @return 성공 여부
     */
    public boolean coinBid(User user, double count, double price){
        double totalprice = count * price;
        if( changeBtc(user, count) && changeKrw(user, -totalprice) ) { return true;}
        return false;
    }
    /**
     * 매도
     * @param user 유저겍체 입니다.
     * @param krw 거래수량입니다
     * @param btc 단가입니다.
     * @return 성공 여부
     */
    public boolean coinSell(User user, double count, double price){
        double totalprice = count * price;
        if( changeBtc(user, -count) && changeKrw(user,  totalprice) ) { return true;}
        return false;
    }
}
