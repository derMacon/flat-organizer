package com.dermacon.securewebapp.controller.groceryList;

import com.dermacon.securewebapp.data.Flatmate;
import com.dermacon.securewebapp.data.FlatmateRepository;
import com.dermacon.securewebapp.data.Item;
import com.dermacon.securewebapp.data.ItemRepository;
import com.dermacon.securewebapp.data.LivingSpace;
import com.dermacon.securewebapp.data.LivingSpaceRepository;
import com.dermacon.securewebapp.data.Room;
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

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

@Transactional
@Controller
public class GroceryListController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlatmateRepository flatmateRepository;

    @Autowired
    LivingSpaceRepository livingSpaceRepository;

    /**
     * Initializes model with
     * - item instance that will overwritten when a new item will be added
     * - all items that were previously selected
     * - selected items which were clicked with the checkboxes
     * @param model model provided by the framework
     * @return grocery list thymeleaf template
     */
    @RequestMapping(value = "/groceryList", method= RequestMethod.GET)
    public String displayGroceryList(Model model) {
        // adding item which will be set in the thymeleaf form and used
        // and overwritten when a new item will be added
        model.addAttribute("item", new Item());

        model.addAttribute("allItems", itemRepository.findAll());
        model.addAttribute("selectedItems", new SelectedItems());

        return "groceryList";
    }

    /**
     * Removes the selected items from the database
     * @param selectedItems object which holds a list of item ids which should be deleted
     * @return grocery list thymeleaf template
     */
    @RequestMapping(value = "/processForm", method=RequestMethod.POST)
    public String processCheckboxForm(@ModelAttribute(value="selectedItems") SelectedItems selectedItems) {

        List<Long> checkedItems = selectedItems.getCheckedItems();

        for (Long curr : checkedItems) {
            Item item = itemRepository.findByItemId(curr);
            System.out.println("removing item entity: " + item);


            // delete entity from database
            itemRepository.delete(item);
        }

        return "redirect:/groceryList";
    }


    /**
     * Adds a new Item to the database.
     * @param item item provided by the html form
     * @return grocery list thymeleaf template
     */
    @PostMapping("/groceryList")
    public String addNewItem(@ModelAttribute("item") Item item) {
        User currUser = getLoggedInUser();
//        Flatmate loggedInFlatmate = flatmateRepository.findAll().
        // todo use repository for this
//        Flatmate loggedInFlatmate = StreamSupport.stream(flatmateRepository.findAll().spliterator(),
//                false)
//                .filter(fm -> fm.getUser().getUserId() == currUser.getUserId()).findFirst().get();
        Flatmate f = flatmateRepository.findAll().iterator().next();

        Flatmate loggedInFlatmate = null;
        for (Flatmate fm : flatmateRepository.findAll()) {
            if (fm.getUser().getUserId() == currUser.getUserId()) {
                loggedInFlatmate = fm;
            }
        }

        LivingSpace livingSpace = loggedInFlatmate.getLivingSpace();
        Room destination;

        switch (item.getItemCategory()) {
            case KITCHEN_SUPPLY:
                destination = livingSpace.getKitchen();
                break;
            case BATHROOM_SUPPLY:
                destination = livingSpace.getBathroom();
                break;
            default:
                destination = livingSpace.getBedroom();
        }

        item.setDestination(destination);




//        Item alreadySavedItem = getItemWithSameName_and_sameGroup(item);
//
//        // overwrite item if necessary
//        if (alreadySavedItem != null) {
//            item = alreadySavedItem;
//        }
//
//        itemRepository.save(item);

        return "redirect:/groceryList";
    }

    private Item getItemWithSameName_and_sameGroup(Item item) {
        Iterable<Item> saved_items = itemRepository.findAll();

        return null;
    }

    /**
     * Determines the currently logged in user
     * @return the currently logged in user
     */
    private User getLoggedInUser() {
        // for some reason the id is always 0
        String user_name = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername();

        return userRepository.findByUsername(user_name);
    }

}
