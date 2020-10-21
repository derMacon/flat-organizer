package com.dermacon.securewebapp.controller.minutes;


import com.dermacon.securewebapp.controller.groceryList.SelectedItems;
import com.dermacon.securewebapp.data.Task;
import com.dermacon.securewebapp.data.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MinutesController {

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping(value = "/minutes", method= RequestMethod.GET)
    public String displayGroceryList(Model model) {
        model.addAttribute("selectedDomain", "minutes");
        model.addAttribute("inputTask", new Task());

//        model.addAttribute("newItems", taskRepository);
//        model.addAttribute("oldItems", );
//        model.addAttribute("selectedItems", new SelectedItems());

        return "minutes";
    }

}
