package com.example.fse.repo;


/**
 * User 구조와 getter, setter가 정의돈 클레스
 *
 * @author JangJaeWon
 * @since 1.0
 */
public class User {
    /**
     * 고유 ID 값입니다. [PK]
     */
    private int id;
    /**
     * 유저 닉네임
     */
    private String uid;
    /**
     * 유저 패스워드
     */
    private String password;
    /**
     * 유저가 가진 KRW
     */
    private Double krw;
    /**
     *  유저가 가진 btc
     */
    private Double btc;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setKrw(Double krw){
        this.krw = krw;
    }
    public double getKrw(){
        return this.krw;
    }
    public void setBtc(Double btc){
        this.btc = btc;
    }
    public double getBtc(){
        return this.btc;
    }
}
