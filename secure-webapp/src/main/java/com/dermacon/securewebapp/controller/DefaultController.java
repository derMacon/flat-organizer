package com.dermacon.securewebapp.controller;

import com.dermacon.securewebapp.data.Flatmate;
import com.dermacon.securewebapp.data.FlatmateRepository;
import com.dermacon.securewebapp.data.Item;
import com.dermacon.securewebapp.data.ItemRepository;
import com.dermacon.securewebapp.data.User;
import com.dermacon.securewebapp.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    FlatmateRepository flatmateRepository;

    @RequestMapping("/")
    public String index(@ModelAttribute("currUser") User user) {
        User currUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String currentPrincipalName = currUser.getUsername();

        user.setUsername(currentPrincipalName);

//        return (List<User>) userRepository.findAll();
//        List<User> users = new LinkedList<>();
//
//        model.addAttribute("users", users);
//        return "index";
        return "main";
    }

    @RequestMapping("/noSecurity")
    public String noSecurity() {
        return "noSecurity";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<User> users = (List<User>)userRepository.findAll();
//        List<User> users = new LinkedList<>();

        model.addAttribute("users", users);
    }


    @GetMapping("/groceryList")
    public String showForm(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);

        Iterable<Item> saved_items = itemRepository.findAll();
        model.addAttribute("saved_items", saved_items);

        return "groceryList";
    }



    @PostMapping("/groceryList")
    public String submitForm(@ModelAttribute("item") Item item) {
        // for some reason the id is always 0
        String user_name = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername();

        User user = userRepository.findByUsername(user_name);

        // set flatmate in item

//        Flatmate fm = userRepository.findByUser_id(currUser.getUser_id());
//        currUser.setUser_id(1);

        System.out.println("user id: " + user.getUser_id());
        Flatmate fm = flatmateRepository.findByUser(user);

//        itemRepository.save(new Item(2, "new product", fm));

        System.out.println(user);
        return "groceryList";
    }


}
