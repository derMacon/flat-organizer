package com.dermacon.securewebapp.controller.admin;

import com.dermacon.securewebapp.data.Flatmate;
import com.dermacon.securewebapp.data.FlatmateRepository;
import com.dermacon.securewebapp.data.LivingSpace;
import com.dermacon.securewebapp.data.LivingSpaceRepository;
import com.dermacon.securewebapp.data.User;
import com.dermacon.securewebapp.data.UserRepository;
import com.dermacon.securewebapp.data.UserRole;
import com.dermacon.securewebapp.logger.LoggerSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class AdminController {

    @Autowired
    private FlatmateRepository flatmateRepository;

    @Autowired
    private LivingSpaceRepository livingSpaceRepository;

    @RequestMapping(value = "/groceryList/admin", method= RequestMethod.GET)
    public String displayAdmin(Model model) {
        model.addAttribute("inputFlatmate", new Flatmate());

        Set<LivingSpace> emptyLivingSpaces =
                StreamSupport.stream(livingSpaceRepository.findAll().spliterator(), false)
                .filter(e -> flatmateRepository.findByLivingSpace(e) == null)
                .collect(Collectors.toSet());

        model.addAttribute("emptyLivingSpaces", emptyLivingSpaces);

        return "admin_editFlatmate";
    }

    @RequestMapping(value = "/groceryList/admin/createFlatmate", method = RequestMethod.POST)
    public String createNewWorkshop_post(@ModelAttribute(value="inputFlatmate") Flatmate flatmate) {

        // todo error
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

        // generate user (username: <firstname>; pw: <lastname><birthday>)
        User newUser = generateUser(flatmate);
        flatmate.setUser(newUser);

        // save in database
        LoggerSingleton.getInstance().info("save flatmate: " + flatmate);
        flatmateRepository.save(flatmate);
        return "redirect:/groceryList/admin";
    }


    /**
     * generate user (username: <firstname>; pw: <lastname><birthday>)
     * @param flatmate
     * @return
     */
    private User generateUser(Flatmate flatmate) {
        User newUser = new User();

        newUser.setUsername(generateUsername(flatmate));
        newUser.setPassword(generatePassword(flatmate));
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
        String shortened_birthday = flatmate.getBirthday().toString().replaceAll(".", "");
        return flatmate.getSurname() + shortened_birthday;
    }


}
