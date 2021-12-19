package com.example.fse.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbDAOTest {
    @Test
    public void checkConnection(){
        DbDAO dbdao = new DbDAO();
        assertNotNull(dbdao.getConnection());
    }

}