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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GroceryListController {

    private final int ITEM_ID_FLD = 0;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlatmateRepository flatmateRepository;


    private User getLoggedInUser() {
        // for some reason the id is always 0
        String user_name = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername();

        return userRepository.findByUsername(user_name);
    }



    @RequestMapping(value = "/groceryList", method= RequestMethod.GET)
    public String showFormSO(Model model) {
        // adding item which will be set in the thymeleaf form and used
        // and overwritten when a new item will be added
        Item item = new Item();
        model.addAttribute("item", item);

        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("allItems", items);

        SelectedItems selectedItems = new SelectedItems();
        List<String> checkedItems = new ArrayList<>();
        // value1 will be checked by default.
        checkedItems.add("value1");
        selectedItems.setCheckedItems(checkedItems);
        model.addAttribute("selectedItems", selectedItems);

        return "groceryList";
    }

    @RequestMapping(value = "/processForm", method=RequestMethod.POST)
    public String processForm(@ModelAttribute(value="selectedItems") SelectedItems selectedItems) {
        // Get value of checked item.
        List<String> checkedItems = selectedItems.getCheckedItems();

        for (String curr : checkedItems) {
            System.out.println(curr);
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



}
