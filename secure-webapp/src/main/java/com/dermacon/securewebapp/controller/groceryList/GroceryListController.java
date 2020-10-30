package com.dermacon.securewebapp.controller.groceryList;

import com.dermacon.securewebapp.data.Flatmate;
import com.dermacon.securewebapp.data.FlatmateRepository;
import com.dermacon.securewebapp.data.Item;
import com.dermacon.securewebapp.data.ItemPreset;
import com.dermacon.securewebapp.data.ItemPresetRepository;
import com.dermacon.securewebapp.data.ItemRepository;
import com.dermacon.securewebapp.data.LivingSpace;
import com.dermacon.securewebapp.data.LivingSpaceRepository;
import com.dermacon.securewebapp.data.Room;
import com.dermacon.securewebapp.data.SelectedSupplyCategory;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Controller for the grocery list endpoint
 */
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

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ItemPresetRepository itemPresetRepository;


    private Date lastPurchase = new Date(System.currentTimeMillis());

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

        Iterable<ItemPreset> p = itemPresetRepository.findAll();

        // adding item which will be set in the thymeleaf form and used
        // and overwritten when a new item will be added
        model.addAttribute("item", new Item());

        model.addAttribute("preset", new ItemPreset());

        // add list of active and inactive elements, will be used to display
        // what is currently in the grocery list and what was bought at the
        // last shopping trip
        model.addAttribute("newItems", itemRepository.findAllByStatus(false));
        model.addAttribute("oldItems", itemRepository.findAllByStatus(true));
        model.addAttribute("selectedItems", new SelectedItems());

        // used in header to select which of the title segments should be highlighted
        model.addAttribute("selectedDomain", "groceryList");

        // used to display preset options
        model.addAttribute("saved_presets", itemPresetRepository.findAll());

        // used when adding a new preset to determine the category type of new preset

        Iterable<SupplyCategory> categories = Arrays.asList(SupplyCategory.values());
//        Iterable<String> categories =
//                Arrays.asList(SupplyCategory.values()).stream().map(SupplyCategory::getCategoryName).collect(Collectors.toList());
        model.addAttribute("available_categories", categories);
        model.addAttribute("new_preset", new ItemPreset());

        // todo delete this
//        Long id = (long)300;
//        Set<Task> tasks = taskRepository.findAllByResponsibleFlatmates_flatmateId(id);

        Iterable<User> allUsers = userRepository.findAll();
        for (User u : allUsers) {

        }

        return "groceryList";
    }

    /**
     * Removes the selected items from the database
     * @param selectedItems object which holds a list of item ids which should be deleted
     * @return grocery list thymeleaf template
     */
    @RequestMapping(value = "/processForm", method=RequestMethod.POST, params = "update")
    public String processCheckboxForm(@ModelAttribute(value="selectedItems") SelectedItems selectedItems) {

        updateOldItems();

        List<Long> checkedItems = selectedItems.getCheckedItems();
        for (Long currId : checkedItems) {
            Item item = itemRepository.findByItemId(currId);
            LoggerSingleton.getInstance().info("persist item: " + item);

            updateLastShoppingList(item);

            LoggerSingleton.getInstance().info("moving item to old items table: " + item);
//
        }

        return "redirect:/groceryList";
    }

    private void updateLastShoppingList(Item inputItem) {

        Item alreadySavedItem = null;

        for (Item currItem : itemRepository.findAll()) {
            if (currItem.getItemName().toLowerCase().equals(inputItem.getItemName().toLowerCase())
                    && currItem.getDestination().equals(inputItem.getDestination())
                    && currItem.getItemId() != inputItem.getItemId()) {

                alreadySavedItem = currItem;

            }
        }

        if (alreadySavedItem == null) {
            inputItem.setStatus(true);
        } else {
            alreadySavedItem.setItemCount(alreadySavedItem.getItemCount() + inputItem.getItemCount());
            // delete entity from database
            itemRepository.delete(inputItem);
        }
    }

    @RequestMapping(value = "/processForm", method=RequestMethod.POST, params = "remove")
    public String removeItems(@ModelAttribute(value="selectedItems") SelectedItems selectedItems) {

        List<Long> checkedItems = selectedItems.getCheckedItems();
        for (Long curr : checkedItems) {
            Item item = itemRepository.findByItemId(curr);
            item.setDestination(null);
            LoggerSingleton.getInstance().info("persist item: " + item);
            itemRepository.delete(item);
        }

        return "redirect:/groceryList";
    }

    /**
     * When the user wants to move new items to the right column the old items
     * will be removed, given that the last move action was at the last day
     *
     * Needed to keep the old items column up to date
     */
    private void updateOldItems() {
        Date curr = new Date(System.currentTimeMillis());
        if (getDateDiff(lastPurchase, curr, TimeUnit.HOURS) > 0) {

            LoggerSingleton.getInstance().info("latest purchase too old, will be removed. Last " +
                    "Purchase (" + lastPurchase + "), current date (" + curr + ")");

            lastPurchase = curr;

            for (Item item : itemRepository.findAllByStatus(true)) {
                item.setDestination(null);
                // delete entity from database
                itemRepository.delete(item);

                LoggerSingleton.getInstance().info("removed item: " + item);
            }

        }
    }

    /**
     * Get a diff between two dates
     * https://stackoverflow.com/questions/1555262/calculating-the-difference-between-two-java-date-instances
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }


    /**
     * Adds a new Item to the database.
     * @param item item provided by the html form
     * @return grocery list thymeleaf template
     */
    @PostMapping("/groceryList")
    public String addNewItem(@ModelAttribute("item") Item item) {

        if (item != null && item.isValid()) {
            Flatmate loggedInFlatmate = getLoggedInFlatmate();
            item.setStatus(false);
            updateItem_flatmateDestination(item, loggedInFlatmate);
            persistItem(item);

            LoggerSingleton.getInstance().info("added new item: " + item);
        }

        return "redirect:/groceryList";
    }


    @PostMapping("/addNewPreset")
    public String addNewPreset(@ModelAttribute("new_preset") ItemPreset itemPreset) {

        ItemPreset alreadySavedPreset = itemPresetRepository
                .findItemPresetsByPresetName(itemPreset.getPresetName());

        // preset name must be unique
        if (alreadySavedPreset == null) {
            LoggerSingleton.getInstance().info("add new item preset: " + itemPreset);
            itemPresetRepository.save(itemPreset);
        }

        return "redirect:/groceryList";
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

}
