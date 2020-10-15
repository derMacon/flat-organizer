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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
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

//        List<SelectableItem> selectableItems = new LinkedList<>();
//        for (Item currItem : saved_items) {
//            selectableItems.add(new SelectableItem(currItem, false));
//
//        }

        model.addAttribute("selected_items", new SelectedItemContainer());

        return "groceryList";
    }

    @PostMapping("/groceryList/removeFromList")
    public String removeItems(@ModelAttribute("selectable_items") SelectedItemContainer selectedItems) {

        for (Item currItem : selectedItems.getCheckedItems()) {
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



    @RequestMapping(value = "/showForm", method= RequestMethod.GET)
    public String showFormSO(Model model) {
        List<String> allItems = new ArrayList<String>();
        allItems.add("value1");
        allItems.add("value2");
        allItems.add("value3");
        model.addAttribute("allItems", allItems);

        Foo foo = new Foo();
        List<String> checkedItems = new ArrayList<String>();
        // value1 will be checked by default.
        checkedItems.add("value1");
        foo.setCheckedItems(checkedItems);
        model.addAttribute("foo", foo);


        return "general";

    }

    @RequestMapping(value = "/processForm", method=RequestMethod.POST)
    public String processForm(@ModelAttribute(value="foo") Foo foo) {
        // Get value of checked item.
        List<String> checkedItems = foo.getCheckedItems();

        for (String curr : checkedItems) {
            System.out.println(curr);
        }


        return "redirect:/groceryList";
    }



}
