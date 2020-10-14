package com.dermacon.securewebapp.controller;

import com.dermacon.securewebapp.data.Flatmate;
import com.dermacon.securewebapp.data.FlatmateRepository;
import com.dermacon.securewebapp.data.Item;
import com.dermacon.securewebapp.data.ItemRepository;
import com.dermacon.securewebapp.data.User;
import com.dermacon.securewebapp.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroceryListController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlatmateRepository flatmateRepository;


    @GetMapping("/groceryList")
    public String showForm(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);

        Iterable<Item> saved_items = itemRepository.findAll();
        model.addAttribute("saved_items", saved_items);

        for (Item currItem : saved_items) {
            System.out.print(item + ", ");
        }

        return "groceryList";
    }


    @PostMapping("/groceryList")
    public String submitForm(@ModelAttribute("item") Item item) {
        // set flatmate in item
        User user = getLoggedInUser();

        System.out.println("user id: " + user.getUser_id());
        Flatmate fm = flatmateRepository.findByUser(user);
        Item new_item = new Item(2, "new product", fm);

        itemRepository.save(new_item);

        System.out.println(fm);
        return "groceryList";
    }


    private User getLoggedInUser() {
        // for some reason the id is always 0
        String user_name = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername();

        return userRepository.findByUsername(user_name);
    }


}
