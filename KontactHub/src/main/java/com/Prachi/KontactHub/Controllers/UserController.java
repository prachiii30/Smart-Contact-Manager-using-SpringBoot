package com.Prachi.KontactHub.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    //dashboard page

    @RequestMapping(value="/dashboard",method = RequestMethod.GET)
    public String userDashboard(){
        return "user/dashboard";
    }

    //user profile
    @RequestMapping(value = "/profile")
    public String userProfile(){
        return "user/profile";
    }
    //user add contact page
    // user view contact page
    // user edit contact page
    // user delete contact page


}
