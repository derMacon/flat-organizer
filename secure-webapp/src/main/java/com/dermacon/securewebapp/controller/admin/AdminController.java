package com.dermacon.securewebapp.controller.admin;

import com.dermacon.securewebapp.controller.services.FlatmateService;
import com.dermacon.securewebapp.data.InputPerson;
import com.dermacon.securewebapp.data.ItemPresetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;

@Controller
@Transactional
@RequestMapping("groceryList/admin")
public class AdminController {

    @Autowired
    private FlatmateService flatmateService;

    @Autowired
    private ItemPresetRepository itemPresetRepository;


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String displayAdmin(Model model) {
        model.addAttribute("allFlatmates", flatmateService.getAllFlatmates());
        model.addAttribute("emptyLivingSpaces", flatmateService.findEmptyLivingSpaces());
        model.addAttribute("allItemPresets", itemPresetRepository.findAll());

        // empty flatmate object -> filled in input box
        model.addAttribute("inputPerson", new InputPerson());

        // empty flatmate list wrapper -> filled in selection box
        model.addAttribute("selectedFlatmates", new SelectedElements());

        // empty flatmate list wrapper -> filled in selection box
        model.addAttribute("selectedItemPresets", new SelectedElements());

        return "admin_main";
    }

    @RequestMapping(value = "/createFlatmate", method = RequestMethod.POST)
    public String createNewFlatmate_post(@ModelAttribute(value = "inputPerson") InputPerson person) {
        if (!flatmateService.createAndSafeFlatmate(person)) {
            // todo handling error
            System.out.println("todo handle error - AdminController, createNewFlatmate_post");
        }
        return "redirect:/groceryList/admin/main";
    }


    @RequestMapping(value = "/removeFlatmate", method = RequestMethod.POST)
    public String removeFlatmate_post(@ModelAttribute(value = "selectedFlatmates") SelectedElements selectedFlatmateIds) {
        // foreach flatmate first remove user and then the entity itself
        selectedFlatmateIds.getCheckedElements().stream()
                .forEach(flatmateService::saveDeleteFlatmate);
        return "redirect:/groceryList/admin/";
    }


}
