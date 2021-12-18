package com.example.fse.controller;
import org.springframework.ui.Model;
import com.example.fse.config.DbDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @RequestMapping(value="/")
    public String goMain(){
        return "index";
    }
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(@RequestParam()){
        return "login";
    }
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String loginOperation(){
        return "login";
    }
    @RequestMapping(value="/signup",method=RequestMethod.GET)
    public String signup(){
        return "signup";
    }
    @RequestMapping(value="/signup",method=RequestMethod.POST)
    public String signupOperation(){
        return "signup";
    }

    @RequestMapping(value="HI",method= RequestMethod.GET)
    public String Hello(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        DbDAO dbdao = new DbDAO();
        return "hello";
    }
}
