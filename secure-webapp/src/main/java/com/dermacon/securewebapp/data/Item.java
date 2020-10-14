package com.dermacon.securewebapp.data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long item_id;

    private int item_count;

    private String item_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flatmate_id")
    private Flatmate flatmate;

    public Item() {
    }

    public Item(int item_count, String item_name, Flatmate flatmate_id) {
        this.item_count = item_count;
        this.item_name = item_name;
        this.flatmate = flatmate_id;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Flatmate getFlatmate() {
        return flatmate;
    }

    public void setFlatmate(Flatmate flatmate) {
        this.flatmate = flatmate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", item_count=" + item_count +
                ", item_name='" + item_name + '\'' +
                ", flatmate=" + flatmate +
                '}';
    }
}
