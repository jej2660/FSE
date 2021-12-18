package com.example.fse.repo;

public class User {
    private int id;
    private String uid;
    private String password;
    private Double krw;
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
