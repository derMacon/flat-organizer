package com.dermacon.securewebapp.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private long item_id;

    private int item_count;

    private String item_name;

    private int flatmate_id;

    public Item() {}

    public Item(String item_name, int flatmate_id) {
        this.item_name = item_name;
        this.flatmate_id = flatmate_id;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }


    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getFlatmate_id() {
        return flatmate_id;
    }

    public void setFlatmate_id(int flatmate_id) {
        this.flatmate_id = flatmate_id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", flatmate_id=" + flatmate_id +
                '}';
    }
}
