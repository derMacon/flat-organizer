package com.dermacon.securewebapp.controller.admin;

import com.dermacon.securewebapp.controller.services.FlatmateService;
import com.dermacon.securewebapp.data.Flatmate;
import com.dermacon.securewebapp.data.InputPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminApiController {

    @Autowired
    private FlatmateService flatmateService;

    @RequestMapping(path = "/info")
    public Iterable<Flatmate> getInfo() {
        return flatmateService.getAllFlatmates();
    }

//    @RequestMapping(path = "/addFlatmate_param")
//    public boolean addFlatmate(@RequestParam String firstname,
//                               @RequestParam String surname,
//                               @RequestParam Date birthday,
//                               @RequestParam long livingSpaceId
//    ) {
//        InputPerson person = new InputPerson(firstname, surname, birthday, livingSpaceId);
//        return flatmateService.createAndSafeFlatmate(person);
//    }

    @RequestMapping(path = "/addFlatmate")
    public boolean addFlatmate(@RequestParam InputPerson person) {
        return flatmateService.createAndSafeFlatmate(person);
    }


    @RequestMapping(path = "/removeFlatmate")
    public boolean removeFlatmate(@RequestParam long id) {
        return flatmateService.saveDeleteFlatmate(id);
    }


}
