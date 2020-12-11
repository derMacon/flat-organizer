package com.dermacon.securewebapp.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminApiController {

    // todo implement adding / removing flatmate
    @RequestMapping(path = "/info")
    public String getInfo() {
        return "info";
    }

}
