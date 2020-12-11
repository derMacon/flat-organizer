package com.dermacon.securewebapp.controller.groceryList;

import com.dermacon.securewebapp.controller.services.ItemService;
import com.dermacon.securewebapp.data.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/groceryList")
public class ListApiController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(path = "/getNewItems")
    public List<String> getNewItems() {
        return itemService.getSortedItems_nextPurchase();
    }

    @RequestMapping(path = "/getOldItems")
    public List<String> getOldItems() {
        return itemService.getSortedItems_prevPurchase();
    }

}
