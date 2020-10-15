package com.dermacon.securewebapp.controller.groceryList;

import com.dermacon.securewebapp.data.Item;

public class SelectableItem {

    private boolean selected;

    private Item item;

    public SelectableItem() {}

    public SelectableItem(Item item, boolean selected) {
        this.selected = selected;
        this.item = item;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "SelectableItem{" +
                "selected=" + selected +
                ", item=" + item +
                '}';
    }
}
