package com.Prachi.KontactHub.Controllers;

import com.Prachi.KontactHub.Services.UserService;
import com.Prachi.KontactHub.entities.User;
import com.Prachi.KontactHub.forms.UserForm;
import com.Prachi.KontactHub.helpers.Message;
import com.Prachi.KontactHub.helpers.MessageType;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

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
    public String register(Model model ){
//        session.removeAttribute("message");
        UserForm userForm=new UserForm();
//        userForm.setName("Prachi");
        model.addAttribute("userForm",userForm);


        return "register";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/home";

    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bingindResult, HttpSession session){

        System.out.println(userForm);
        //fetch data
        //validate
        if(bingindResult.hasErrors())
            return "register";


        //save

//        User user= User.builder()
//                .name(userForm.getName())
//                .email(userForm.getEmail())
//                .password(userForm.getPassword())
//                .phoneNumber(userForm.getPhoneNumber())
//                .build();
        User user=new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfileImg("https://www.google.com/imgres?q=pinterest%20dp%20image&imgurl=https%3A%2F%2Fi.pinimg.com%2F564x%2F80%2F34%2F3e%2F80343e69d0c5c22e827523668f76ef0b.jpg&imgrefurl=https%3A%2F%2Fin.pinterest.com%2Fpin%2F102-cool-iphone-wallpapers-aesthetic-backgrounds--909375349740454508%2F&docid=Q60-Ekk19wDCiM&tbnid=xXjp17PJ9EtqLM&vet=12ahUKEwjwitOr_fuJAxUe4zgGHVkuIkcQM3oECBoQAA..i&w=564&h=996&hcb=2&ved=2ahUKEwjwitOr_fuJAxUe4zgGHVkuIkcQM3oECBoQAA");
        User saveduser=userService.saveUser(user);
        System.out.println("USER SAVED");

        //message (optional)


       Message message= Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message",message);
        //redirect
        return "redirect:/register";
    }





}
