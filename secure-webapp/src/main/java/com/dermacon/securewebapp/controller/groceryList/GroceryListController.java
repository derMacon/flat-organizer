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

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Controller
public class GroceryListController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlatmateRepository flatmateRepository;


    /**
     * Initializes model with
     * - item instance that will overwritten when a new item will be added
     * - all items that were previously selected
     * - selected items which were clicked with the checkboxes
     * @param model model provided by the framework
     * @return grocery list thymeleaf template
     */
    @RequestMapping(value = "/groceryList", method= RequestMethod.GET)
    public String initGroceryList(Model model) {
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

            // remove associated flatmate before deleting entity
            item.setFlatmate(null);

            // delete entity from database
            itemRepository.delete(item);
        }

        return "redirect:/groceryList";
    }

    /**
     * Adds a new Item to the database. The reference to the
     * current user will also be appended to the item entity
     * itself
     * @param item item provided by the html form
     * @return grocery list thymeleaf template
     */
    @PostMapping("/groceryList")
    public String addNewItem(@ModelAttribute("item") Item item) {
        // set flatmate in item
        User currUser = getLoggedInUser();
        Flatmate loggedInFlatmate = flatmateRepository.findByUser(currUser);

        item.setFlatmate(loggedInFlatmate);
        itemRepository.save(item);

        return "redirect:/groceryList";
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
