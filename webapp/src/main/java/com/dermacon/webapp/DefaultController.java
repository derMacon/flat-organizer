package com.dermacon.webapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public String index() {
        return "logged in";
    }

    @RequestMapping("/noSecurity")
    public String noSecurity() {
        return "everyone can see this";
    }

}
