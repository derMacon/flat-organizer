package com.dermacon.securewebapp.controller.admin;

import java.util.ArrayList;
import java.util.List;

public class SelectedFlatmates {

    /**
     * Selected Flatmate IDs in the checkbox form
     */
    private List<Long> checkedFlatmates = new ArrayList<>();

    public List<Long> getCheckedFlatmates() {
        return checkedFlatmates;
    }

    public void setCheckedFlatmates(List<Long> checkedFlatmates) {
        this.checkedFlatmates = checkedFlatmates;
    }
}
