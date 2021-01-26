package com.dermacon.workshop.controller;

import com.dermacon.workshop.data.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private AppUserRepository appUserRepository;


    @RequestMapping("/test")
    public String test() {
        List<String> result = new ArrayList<String>();
        appUserRepository.findAll().forEach(e -> result.add(e.getUsername()));
        return "test1: " + result;
    }

}
