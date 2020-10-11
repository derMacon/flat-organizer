package com.dermacon.securewebapp.controller;

import com.dermacon.securewebapp.data.User;
import com.dermacon.securewebapp.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
//        return (List<User>) userRepository.findAll();
        return "index";
    }

    @RequestMapping("/noSecurity")
    public String noSecurity() {
        return "noSecurity";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "Welcome to the Netherlands!");
    }

}
