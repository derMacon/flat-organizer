package com.dermacon.securewebapp.controller;

import com.dermacon.securewebapp.data.User;
import com.dermacon.securewebapp.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/")
    public String index(@ModelAttribute("currUser") User user) {
        User currUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String currentPrincipalName = currUser.getUsername();

        user.setUsername(currentPrincipalName);

//        return (List<User>) userRepository.findAll();
//        List<User> users = new LinkedList<>();
//
//        model.addAttribute("users", users);
//        return "index";
        return "main";
    }

//    @RequestMapping("/noSecurity")
//    public String noSecurity() {
//        return "noSecurity";
//    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<User> users = (List<User>) userRepository.findAll();
//        List<User> users = new LinkedList<>();

        model.addAttribute("users", users);
    }


}
