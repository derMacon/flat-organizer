package com.dermacon.securewebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DefaultController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public List<User> index() {
        return (List<User>) userRepository.findAll();
//        return "index";
    }

    @RequestMapping("/noSecurity")
    public String noSecurity() {
        return "noSecurity";
    }

}
