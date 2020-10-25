package com.dermacon.securewebapp.controller.information;

import com.dermacon.securewebapp.data.FlatmateRepository;
import com.dermacon.securewebapp.data.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for the information endpoint
 * used to display information about the flatmates
 */
@Controller
public class InformationController {

    @Autowired
    FlatmateRepository flatmateRepository;

    @RequestMapping(value = "/information", method= RequestMethod.GET)
    public String displayGroceryList(Model model) {
        model.addAttribute("selectedDomain", "information");

        model.addAttribute("flatmates", flatmateRepository.findAll());

        return "information";
    }

}
