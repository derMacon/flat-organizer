package com.dermacon.securewebapp.controller;

import com.dermacon.securewebapp.data.Item;
import com.dermacon.securewebapp.data.User;
import com.dermacon.securewebapp.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(@ModelAttribute("currUser") User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        user.setUsername(currentPrincipalName);

//        return (List<User>) userRepository.findAll();
//        List<User> users = new LinkedList<>();
//
//        model.addAttribute("users", users);
//        return "index";
        return "noSecurity";
    }

    @RequestMapping("/noSecurity")
    public String noSecurity() {
        return "noSecurity";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<User> users = (List<User>)userRepository.findAll();
//        List<User> users = new LinkedList<>();

        model.addAttribute("users", users);
    }

}
