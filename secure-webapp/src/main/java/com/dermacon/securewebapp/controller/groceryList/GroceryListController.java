package com.dermacon.securewebapp.controller.groceryList;

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

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
        // adding item which will be set in the thymeleaf form and used
        // and overwritten when a new item will be added
        Item item = new Item();
        model.addAttribute("item", item);

        // add items to the model which will be displayed by thymeleaf
        Iterable<Item> saved_items = itemRepository.findAll();
        model.addAttribute("saved_items", saved_items);

        List<SelectableItem> selectableItems = new LinkedList<>();
        for (Item currItem : saved_items) {
            selectableItems.add(new SelectableItem(currItem, false));

        }

        model.addAttribute("selectable_items", selectableItems);

        return "groceryList";
    }

    @PostMapping("/groceryList/removeFromList")
    public String removeItems(@ModelAttribute("selectable_items") Iterable<Item> items_selected) {

        for (Item currItem : items_selected) {
            System.out.print(currItem + ", ");
        }

        return "redirect:/groceryList";
    }


    @PostMapping("/groceryList")
    public String submitForm(@ModelAttribute("item") Item item) {
        // set flatmate in item
        User currUser = getLoggedInUser();
        Flatmate loggedInFlatmate = flatmateRepository.findByUser(currUser);

        item.setFlatmate(loggedInFlatmate);
        itemRepository.save(item);

        return "redirect:/groceryList";
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
