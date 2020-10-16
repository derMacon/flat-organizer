package com.dermacon.securewebapp.data;

import org.hibernate.annotations.Cascade;

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
    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    private long itemId;

    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    private int item_count;

    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    private String item_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "flatmate_id", nullable = true)
    private Flatmate flatmate;

    public Item() {
    }

    public Item(int item_count, String item_name, Flatmate flatmate_id) {
        this.item_count = item_count;
        this.item_name = item_name;
        this.flatmate = flatmate_id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
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
                "item_id=" + itemId +
                ", item_count=" + item_count +
                ", item_name='" + item_name + '\'' +
                ", flatmate=" + flatmate +
                '}';
    }
}
