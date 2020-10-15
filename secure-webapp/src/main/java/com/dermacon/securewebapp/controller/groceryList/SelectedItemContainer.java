package com.dermacon.securewebapp.controller.groceryList;

import com.dermacon.securewebapp.data.Item;

import java.util.LinkedList;
import java.util.List;

public class SelectedItemContainer {
    private List<Item> checkedItems;

    public List<Item> getCheckedItems() {
        return checkedItems;
    }

    public void setCheckedItems(List<Item> checkedItems) {
        this.checkedItems = checkedItems;
    }
}
