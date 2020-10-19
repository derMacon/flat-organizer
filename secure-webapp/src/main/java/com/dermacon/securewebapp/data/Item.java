package com.dermacon.securewebapp.data;

import org.apache.commons.pool2.BaseObject;
import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

@Entity
public class Item extends BaseObject {

    @Id
    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    private long itemId;

    private int itemCount;

    private String itemName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id", nullable = true)
    private Room destination;

    public Item() {
        this.itemCount = 1;
    }

    public Item(long itemId, int itemCount, String itemName, Room destination) {
        this.itemId = itemId;
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.destination = destination;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public ItemCategory getItemCategory() {
        ItemCategory out = null;

        // if further product presets should be added, those can be updated here

        switch (getProductDefault()) {
            case KITCHEN_ROLL:
                out = ItemCategory.KITCHEN_SUPPLY;
                break;
            case TOILET_PAPER:
                out = ItemCategory.BATHROOM_SUPPLY;
                break;
            default:
                out = ItemCategory.COSTUM_SUPPLY;
        }

        return out;
    }

    private ProductDefault getProductDefault() {
        ProductDefault[] values = ProductDefault.values();
        int i = 0;
        boolean found = false;

        while (!found && i < values.length) {
            String lowerCaseName = itemName.toLowerCase();
            found = Arrays.stream(values[i].getItemNames())
                    .filter(lowerCaseName::equals)
                    .findAny()
                    .isPresent();
            i++;
        }

        if (found) {
            return values[i-1];
        }

        return ProductDefault.COSTUM_SUPPLY;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Room getDestination() {
        return destination;
    }

    public void setDestination(Room destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemCount=" + itemCount +
                ", itemName='" + itemName + '\'' +
                ", destination=" + destination +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item other = (Item) o;

        boolean product_name_equals = this.getProductDefault().equals(other.getProductDefault())
                || this.itemName.toLowerCase().equals(other.itemName.toLowerCase());

        return product_name_equals && this.destination.equals(other.destination);
    }
}
