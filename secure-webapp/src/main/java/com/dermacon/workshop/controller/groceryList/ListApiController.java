package com.dermacon.workshop.controller.groceryList;

import com.dermacon.workshop.controller.services.ItemService;
import com.dermacon.workshop.data.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api controller mapping requests for the grocery list
 */
@RestController
@RequestMapping("api/groceryList")
public class ListApiController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(path = "/getNewItems")
    public Iterable<Item> getNewItems() {
        return itemService.getSortedItems_nextPurchase();
    }

    @RequestMapping(path = "/getOldItems")
    public Iterable<Item> getOldItems() {
        return itemService.getSortedItems_prevPurchase();
    }

}
