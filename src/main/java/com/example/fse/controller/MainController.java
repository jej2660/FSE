package com.example.fse.controller;
import com.example.fse.repo.User;
import com.example.fse.repo.UserDAO;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.ui.Model;
import com.example.fse.config.DbDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @RequestMapping(value="/")
    public String goMain(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String userid = (String)session.getAttribute("uid");
        model.addAttribute("uid",userid);
        return "index";
    }
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String loginOperaction(HttpServletRequest request, @RequestParam("id") String uid, @RequestParam("pwd") String password, Model model){
        HttpSession session = request.getSession();
        UserDAO userdao = new UserDAO();
        String userid = userdao.loginCheck(uid, password);
        if(userid != null) {
            session.setAttribute("uid", userid);
        }
        return "index";
    }
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value="/signup",method=RequestMethod.GET)
    public String signup(){
        return "signup";
    }
    @RequestMapping(value="/signup",method=RequestMethod.POST)
    public String signupOperation(@RequestParam("username") String uid, @RequestParam("pwd") String pwd){
        UserDAO userdao = new UserDAO();
        User user = new User();
        user.setUid(uid);
        user.setPassword(pwd);
        userdao.registUser(user);

        return "index";
    }

    @RequestMapping(value="/trade", method=RequestMethod.GET)
    public String trade(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        UserDAO userdao = new UserDAO();
        String userid = (String)session.getAttribute("uid");
        model.addAttribute("uid",userid);
        if(userid != null){
            User user = userdao.getUser(userid);
            model.addAttribute("krw", user.getKrw());
            model.addAttribute("btc", user.getBtc());
            return "trade";
        }
        return "index";
    }
    @RequestMapping(value="/trade", method=RequestMethod.POST)
    public String trade(HttpServletRequest request, @RequestParam("count") Double count, @RequestParam("price") Double price,
                        @RequestParam("mod") String mod, Model model){
        HttpSession session = request.getSession();
        String userid = (String)session.getAttribute("uid");
        DbDAO dbdao = new DbDAO();
        UserDAO userdao = new UserDAO();
        User user = userdao.getUser(userid);

        if(mod.equals("buy")){
            userdao.coinBid(user,count,price);
        }
        else if(mod.equals("sell")){
            userdao.coinSell(user,count,price);
        }
        model.addAttribute("uid",userid);
        if(userid != null){
            model.addAttribute("krw", user.getKrw());
            model.addAttribute("btc", user.getBtc());
            return "trade";
        }
        return "index";
    }
    @RequestMapping(value="HI",method= RequestMethod.GET)
    public String Hello(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        DbDAO dbdao = new DbDAO();
        return "hello";
    }
}
