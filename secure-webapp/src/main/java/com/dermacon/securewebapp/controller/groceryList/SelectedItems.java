package com.dermacon.securewebapp.controller.groceryList;

import java.util.List;

public class SelectedItems {
    private List<String> checkedItems;

    public List<String> getCheckedItems() {
        return checkedItems;
    }

    public void setCheckedItems(List<String> checkedItems) {
        this.checkedItems = checkedItems;
    }
}
