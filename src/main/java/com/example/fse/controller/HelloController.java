package com.example.fse.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
    @RequestMapping(value="HI",method= RequestMethod.GET)
    public String Hello(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}
