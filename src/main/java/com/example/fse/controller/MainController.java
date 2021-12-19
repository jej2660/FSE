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
/**
 * WAS URL Mapping 되어 있는 Controller
 *
 * @author JangJaeWon
 * @since 1.0
 */
@Controller
public class MainController {
    /**
     * 메인페이지 이동
     * @param request 세션정보입니다
     * @param model front-end로 넘겨줄 맵핑 데이터입니다.
     * @return 렌더링할 템플릿 입니다.
     */
    @RequestMapping(value="/")
    public String goMain(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String userid = (String)session.getAttribute("uid");
        model.addAttribute("uid",userid);
        return "index";
    }
    /**
     * 로그인 동작
     * @param request 세션정보입니다
     * @param model front-end로 넘겨줄 맵핑 데이터입니다.
     * @param id login id
     * @param pwd login password
     * @return 렌더링할 템플릿 입니다.
     */
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
    /**
     * 로그인 페이지 이동
     * @return 렌더링할 템플릿 입니다.
     */
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        return "login";
    }
    /**
     * 회원가입 페이지 이동
     *
     * @return 렌더링할 템플릿 입니다.
     */
    @RequestMapping(value="/signup",method=RequestMethod.GET)
    public String signup(){
        return "signup";
    }
    /**
     * 회원가입 엑션
     * @param uid 회원가입시 입력한 아이디 정보 입니다.
     * @param pwd 회원가입시 입력한 패스워드 정보 입니다.
     * @return 렌더링할 템플릿 입니다.
     */
    @RequestMapping(value="/signup",method=RequestMethod.POST)
    public String signupOperation(@RequestParam("username") String uid, @RequestParam("pwd") String pwd){
        UserDAO userdao = new UserDAO();
        User user = new User();
        user.setUid(uid);
        user.setPassword(pwd);
        userdao.registUser(user);

        return "index";
    }
    /**
     *거래소 이동
     * @param request 세션정보입니다
     * @param model front-end로 넘겨줄 맵핑 데이터입니다.
     * @return 렌더링할 템플릿 입니다.
     */
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
    /**
     * 거래소 매수 매도 동작
     * @param request 세션정보입니다
     * @param model front-end로 넘겨줄 맵핑 데이터입니다.
     * @param count 거래 수량입니다
     * @param price 거래 가격입니다.
     * @param mod 매수/매도 선택자입니다.
     * @return 렌더링할 템플릿 입니다.
     */
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
