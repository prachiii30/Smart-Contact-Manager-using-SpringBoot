package com.Prachi.KontactHub.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class PageController {
    @RequestMapping("/home")
    public String home(Model m){
        m.addAttribute("name"," Prachi ");
        return "home";
    }


    @RequestMapping("/about")
    public String about(Model m){
        m.addAttribute("name"," Prachi ");
        return "about";
    }


    @RequestMapping("/service")
    public String service(Model m){
        m.addAttribute("name"," Prachi ");
        return "service";
    }

    @RequestMapping("/contact")
    public String contact(){

        return "contact";
    }

    @RequestMapping("/login")
    public String login(){

        return "login";
    }

    @RequestMapping("/register")
    public String register(){

        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(){

        //fetch data
        //validate
        //save
        //message (optional)
        //redirect
        return "";
    }



}
