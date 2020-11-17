package com.dermacon.securewebapp.controller.admin;

import com.dermacon.securewebapp.controller.services.FlatmateService;
import com.dermacon.securewebapp.data.Flatmate;
import com.dermacon.securewebapp.data.FlatmateRepository;
import com.dermacon.securewebapp.data.ItemPresetRepository;
import com.dermacon.securewebapp.data.LivingSpace;
import com.dermacon.securewebapp.data.LivingSpaceRepository;
import com.dermacon.securewebapp.data.User;
import com.dermacon.securewebapp.data.UserRepository;
import com.dermacon.securewebapp.data.UserRole;
import com.dermacon.securewebapp.logger.LoggerSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
@Transactional
public class AdminController {

    @Autowired
    private FlatmateRepository flatmateRepository;

    @Autowired
    private LivingSpaceRepository livingSpaceRepository;

    @Autowired
    private FlatmateService flatmateService;

    @Autowired
    private ItemPresetRepository itemPresetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/groceryList/admin", method = RequestMethod.GET)
    public String displayAdmin(Model model) {
        model.addAttribute("allFlatmates", flatmateRepository.findAll());

        // empty flatmate object -> filled in input box
        model.addAttribute("inputFlatmate", new Flatmate());

        // empty flatmate list wrapper -> filled in selection box
        model.addAttribute("selectedFlatmates", new SelectedElements());

        initModel_removePresetTemplate(model);

        Set<LivingSpace> emptyLivingSpaces =
                StreamSupport.stream(livingSpaceRepository.findAll().spliterator(), false)
                        .filter(e -> flatmateRepository.findByLivingSpace(e) == null)
                        .collect(Collectors.toSet());

        model.addAttribute("emptyLivingSpaces", emptyLivingSpaces);

        return "admin_main";
//        return "construction";
    }

    private void initModel_removePresetTemplate(Model model) {
        model.addAttribute("allItemPresets", itemPresetRepository.findAll());
        // empty flatmate list wrapper -> filled in selection box
        model.addAttribute("selectedItemPresets", new SelectedElements());

    }

    @RequestMapping(value = "/groceryList/admin/createFlatmate", method = RequestMethod.POST)
    public String createNewWorkshop_post(@ModelAttribute(value = "inputFlatmate") Flatmate flatmate) {

        // todo handling error
        String firstname = flatmate.getFirstname();
        String surname = flatmate.getSurname();
        if (flatmateRepository.findByFirstnameAndSurname(firstname, surname) != null) {
            // todo throw error
            LoggerSingleton.getInstance().warning("flatmate already exists");
            return "redirect:/groceryList/admin";
        }

        // form only sets living space id -> necessary to load whole entity
        Long formInput_id = flatmate.getLivingSpace().getLivingSpaceId();
        flatmate.setLivingSpace(livingSpaceRepository.findById(formInput_id).get());

        // generate user (username: <firstname>; pw: <lastname><birthday-day><birthday-month>)
        User newUser = generateUser(flatmate);
        newUser = userRepository.save(newUser);
        flatmate.setUser(newUser);

        // save in database
        LoggerSingleton.getInstance().info("save flatmate: " + flatmate);
        flatmateRepository.save(flatmate);
        return "redirect:/groceryList/admin";
    }


    /**
     * generate user (username: <firstname>; pw: <lastname><birthday>)
     *
     * @param flatmate
     * @return
     */
    private User generateUser(Flatmate flatmate) {
        User newUser = new User();

        newUser.setUsername(generateUsername(flatmate));
        String hash = passwordEncoder.encode(generatePassword(flatmate));
        newUser.setPassword(hash);
        newUser.setRole(UserRole.ROLE_USER);

        return newUser;
    }

    private String generateUsername(Flatmate flatmate) {
        String username = flatmate.getFirstname();
        // flatmate with same firstname already exists
        // -> append as much letters from surname as needed to make it unique
        int i = 0;
        String surname = flatmate.getSurname();
        while (flatmateRepository.findByFirstname(username) != null && i < surname.length()) {
            username += flatmate.getSurname().substring(i, i + 1);
            i++;
        }
        return username;
    }

    private String generatePassword(Flatmate flatmate) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(flatmate.getBirthday());

        int day = cal.get(Calendar.DAY_OF_MONTH);
        // for some reason the month of january will evaluate to month == 0
        int month = cal.get(Calendar.MONTH) + 1;

        String month_str = month < 10
                ? "0" + month
                : "" + month;

        String day_str = day < 10
                ? "0" + day
                : "" + day;

        return flatmate.getSurname() + day_str + month_str;
    }


    @RequestMapping(value = "/groceryList/admin/removeFlatmate", method = RequestMethod.POST)
    public String removeFlatmate_post(@ModelAttribute(value = "selectedFlatmates") SelectedElements selectedFlatmateIds) {
        // translate selected flatmate ids to actual flatmate instances from the db
        Stream<Flatmate> selectedFlatmates =
                selectedFlatmateIds.getCheckedElements()
                .stream()
                .map(flatmateRepository::findById)
                .map(Optional::get);

        // foreach flatmate first remove user and then the entity itself
        selectedFlatmates.forEach(flatmateService::saveDeleteFlatmate);

        return "redirect:/groceryList/admin";
    }


    @RequestMapping(value = "/groceryList/admin/removePreset", method = RequestMethod.POST)
    public String removePreset_post(@ModelAttribute(value = "selectedItemPreset") SelectedElements selectedPresets) {
        selectedPresets.getCheckedElements()
                .stream()
                .forEach(itemPresetRepository::deleteByPresetId);
        return "redirect:/groceryList/admin";
    }

}
