package com.dermacon.securewebapp.controller.groceryList;

import com.dermacon.securewebapp.controller.admin.SelectedElements;
import com.dermacon.securewebapp.controller.services.ItemService;
import com.dermacon.securewebapp.data.Flatmate;
import com.dermacon.securewebapp.data.FlatmateRepository;
import com.dermacon.securewebapp.data.Item;
import com.dermacon.securewebapp.data.ItemPreset;
import com.dermacon.securewebapp.data.ItemPresetRepository;
import com.dermacon.securewebapp.data.ItemRepository;
import com.dermacon.securewebapp.data.LivingSpace;
import com.dermacon.securewebapp.data.LivingSpaceRepository;
import com.dermacon.securewebapp.data.Room;
import com.dermacon.securewebapp.data.SupplyCategory;
import com.dermacon.securewebapp.data.TaskRepository;
import com.dermacon.securewebapp.data.User;
import com.dermacon.securewebapp.data.UserRepository;
import com.dermacon.securewebapp.logger.LoggerSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Controller for the grocery list endpoint
 */
@Transactional
@Controller
@RequestMapping("groceryList")
public class GroceryListController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlatmateRepository flatmateRepository;

    @Autowired
    LivingSpaceRepository livingSpaceRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ItemPresetRepository itemPresetRepository;

    @Autowired
    ItemService itemService;


    /**
     * Initializes model with
     * - item instance that will overwritten when a new item will be added
     * - all items that were previously selected
     * - selected items which were clicked with the checkboxes
     * @param model model provided by the framework
     * @return grocery list thymeleaf template
     */
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String displayGroceryList(Model model) {

        // adding item which will be set in the thymeleaf form and used
        // and overwritten when a new item will be added
        model.addAttribute("item", new Item());

        model.addAttribute("preset", new ItemPreset());

        // add list of active and inactive elements, will be used to display
        // what is currently in the grocery list and what was bought at the
        // last shopping trip
        model.addAttribute("newItems", itemService.getSortedItems_nextPurchase());
        model.addAttribute("oldItems", itemService.getSortedItems_prevPurchase());
        model.addAttribute("dateLastPurchase", itemService.getLastPurchase());
        model.addAttribute("selectedItems", new SelectedItems());

        // used in header to select which of the title segments should be highlighted
        model.addAttribute("selectedDomain", "groceryList");

        // used to display preset options
        // todo put into service
        model.addAttribute("saved_presets", sort(itemPresetRepository.findAll()));

        // used when adding a new preset to determine the category type of new preset
        Iterable<SupplyCategory> categories = Arrays.asList(SupplyCategory.values());
        model.addAttribute("available_categories", categories);
        model.addAttribute("new_preset", new ItemPreset());

        model.addAttribute("selectedItemPresets", new SelectedElements());

        return "groceryList";
    }

    // todo remove this
    private <T> Iterable<T> sort(Iterable<T> it) {
        Stream<T> stream = StreamSupport.stream(it.spliterator(), false);
        return stream.sorted().collect(Collectors.toList());
    }

    @RequestMapping(value = "/processForm", method=RequestMethod.POST, params = "updateAll")
    public String checkAllItems() {
        itemService.checkAllItems();
        return "redirect:/groceryList/";
    }

    /**
     * Removes the selected items from the database
     * @param selectedItems object which holds a list of item ids which should be deleted
     * @return grocery list thymeleaf template
     */
    @RequestMapping(value = "/processForm", method=RequestMethod.POST, params = "update")
    public String processCheckboxForm(@ModelAttribute(value="selectedItems") SelectedItems selectedItems) {

        Iterable<Long> itemIds = selectedItems.getCheckedItems();
        boolean ableToSelectAllItems = !itemService.shopSelectedItems(itemIds);
        if (!ableToSelectAllItems) {
            // todo handle error
        }

        return "redirect:/groceryList/";
    }


    @RequestMapping(value = "/processForm", method=RequestMethod.POST, params = "remove")
    public String removeItems(@ModelAttribute(value="selectedItems") SelectedItems selectedItems) {

        Iterable<Long> itemIds = selectedItems.getCheckedItems();
        itemService.removeSelectedItems(itemIds);

        return "redirect:/groceryList/";
    }


    /**
     * Adds a new Item to the database.
     * @param item item provided by the html form
     * @return grocery list thymeleaf template
     */
    // todo update mapping
    @PostMapping("/addItem")
    public String addNewItem(@ModelAttribute("item") Item item) {

        if (item != null && item.isValid()) {
            Flatmate loggedInFlatmate = getLoggedInFlatmate();
            item.setStatus(false);
            updateItem_flatmateDestination(item, loggedInFlatmate);
            persistItem(item);

            LoggerSingleton.getInstance().info("added new item: " + item);
        } else {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).warning("can't add item: " + item);
        }

        return "redirect:/groceryList/";
    }


    @PostMapping("/addNewPreset")
    public String addNewPreset(@ModelAttribute("new_preset") ItemPreset itemPreset) {

        ItemPreset alreadySavedPreset = itemPresetRepository
                .findItemPresetsByPresetName(itemPreset.getPresetName());

        // preset name must be unique
        if (alreadySavedPreset == null) {
            LoggerSingleton.getInstance().info("add new item preset: " + itemPreset);
            // todo throws java.sql.SQLIntegrityConstraintViolationException
            // when supply_category is null, this should be displayed to the error page
            itemPresetRepository.save(itemPreset);
        }

        return "redirect:/groceryList/";
    }



    /**
     * Checks if an item with the same name, destination and shipping status already exists,
     * if so the appropriate entity will be updated otherwise the given entity will be saved
     * to the database as it is.
     * @param item item to persist
     */
    private void persistItem(Item item) {
        Item alreadySavedItem = getItemWithSameName_and_Destination_and_status(item);
        // overwrite item if necessary
        if (alreadySavedItem != null) {
            alreadySavedItem.setItemCount(item.getItemCount() + alreadySavedItem.getItemCount());
            LoggerSingleton.getInstance().info("overwrites already saved item: " + item);
        } else {
            itemRepository.save(item);
            LoggerSingleton.getInstance().info("no existing item entity, persist new: " + item);
        }
    }

    /**
     * Returns the Flatmate entity of the currently logged in user.
     * @return the Flatmate entity of the currently logged in user.
     */
    private Flatmate getLoggedInFlatmate() {
        User currUser = getLoggedInUser();
        // todo use flatmateRepository for this
        Flatmate loggedInFlatmate = null;
        for (Flatmate fm : flatmateRepository.findAll()) {
            if (fm.getUser().getUserId() == currUser.getUserId()) {
                loggedInFlatmate = fm;
            }
        }
        return loggedInFlatmate;
    }

    /**
     * The destination field of the item will be filled.
     *
     * Depending where the item is neede (e.g. kitchen vs. bathroom supply)
     * the
     * @param item
     * @param flatmate
     */
    private void updateItem_flatmateDestination(Item item, Flatmate flatmate) {
        LivingSpace livingSpace = flatmate.getLivingSpace();
        Room destination;

        ItemPreset preset = itemPresetRepository.findItemPresetsByPresetName(item.getItemName());
        switch (preset.getSupplyCategory()) {
            case KITCHEN_SUPPLY:
                destination = livingSpace.getKitchen();
                break;
            case BATHROOM_SUPPLY:
                destination = livingSpace.getBathroom();
                break;
            case CLEANING_SUPPLY:
                destination = livingSpace.getStorage();
                break;
            default:
                destination = livingSpace.getBedroom();
        }

        item.setDestination(destination);
        LoggerSingleton.getInstance().info("updated item with destination: " + item);
    }

    /**
     * Get equivalent item to given input
     * @param inputItem input item to check
     * @return equivalent item to given input
     */
    private Item getItemWithSameName_and_Destination_and_status(Item inputItem) {
        Item out = null;

        for (Item currItem : itemRepository.findAll()) {
            if (currItem.equals(inputItem)
                    && currItem.getItemId() != inputItem.getItemId()) {
                out = currItem;
            }
        }

        return out;
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


    @RequestMapping(value = "/removePreset", method = RequestMethod.POST)
    public String removePreset_post(@ModelAttribute(value = "selectedItemPreset") SelectedElements selectedPresets) {
        selectedPresets.getCheckedElements()
                .stream()
                .forEach(itemPresetRepository::deleteByPresetId);
        return "redirect:/groceryList/";
    }
}
