package com.dermacon.securewebapp.controller.groceryList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/groceryList")
public class ListApiController {

    @RequestMapping(path = "/getItems")
    public String getGroceryList() {
        return "test";
    }

}
