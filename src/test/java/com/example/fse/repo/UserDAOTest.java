package com.example.fse.repo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    @Test
    public void checkName(){
        UserDAO userdao = new UserDAO();
        User user = userdao.getUser("sgom");
        assertEquals("sgom", user.getUid());
    }
    @Test
    public void checkValue(){
        UserDAO userdao = new UserDAO();
        User user = userdao.getUser("sgom");
        userdao.changeBtc(user, -1.1121212);
        assertNotNull(user.getBtc());
        assertNotNull(user.getKrw());
    }
    @Test
    public void checkBid(){
        UserDAO userado = new UserDAO();
        User user = userado.getUser("sgom");
        userado.changeUserInfo(user,99981.1111,0);
        userado.coinBid(user, 1, 99981.1111);


        user = userado.getUser("sgom");
        assertEquals(user.getBtc(),1);
        assertEquals(user.getKrw(),0);

    }
    @Test
    public void checkSell(){
        UserDAO userado = new UserDAO();
        User user = userado.getUser("sgom");
        userado.changeUserInfo(user,0,1);
        userado.coinSell(user, 1, 1000);


        user = userado.getUser("sgom");
        assertEquals(user.getBtc(),0);
        assertEquals(user.getKrw(),1000);

    }
}