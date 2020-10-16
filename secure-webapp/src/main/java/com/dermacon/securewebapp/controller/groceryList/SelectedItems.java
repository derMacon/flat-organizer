package com.dermacon.securewebapp.controller.groceryList;

import java.util.ArrayList;
import java.util.List;

public class SelectedItems {
    private List<Long> checkedItems = new ArrayList<>();

    public List<Long> getCheckedItems() {
        return checkedItems;
    }

    public void setCheckedItems(List<Long> checkedItems) {
        this.checkedItems = checkedItems;
    }
}
