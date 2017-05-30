package ua.lviv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lviv.service.UserService;

/**
 * Created by PC-6 on 25.04.2017.
 */
@Controller
public class BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(){
        return "registration";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam("name") String name, @RequestParam("secondName")String secondName, @RequestParam("email")String email, @RequestParam("phone")String phone, @RequestParam("login")String login,@RequestParam("password")String password){
        userService.addUser(name, secondName, email, phone, login, password);
        return "redirect:/";
    }
    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String signIn(){
        return "signIn";
    }
}
