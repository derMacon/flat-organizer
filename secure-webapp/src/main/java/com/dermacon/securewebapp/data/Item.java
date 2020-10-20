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

    private Boolean status;

    public Item() {
        this.itemCount = 1;
        this.status = true;
    }

    public Item(long itemId, int itemCount, String itemName, Room destination, Boolean status) {
        this.itemId = itemId;
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.destination = destination;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemCount=" + itemCount +
                ", itemName='" + itemName + '\'' +
                ", destination=" + destination +
                ", status=" + status +
                '}';
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

    public boolean isValid() {
        return this.getItemCount() > 0
                && !this.getItemName().isBlank()
                && !this.getItemName().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item other = (Item) o;

        ProductDefault this_default = this.getProductDefault();
        ProductDefault other_default = other.getProductDefault();

        boolean defaultsMatch = this_default.equals(other_default);

        // custom products must match names
        if (defaultsMatch && this_default.equals(ProductDefault.COSTUM_SUPPLY)) {
            defaultsMatch = this.itemName.toLowerCase().equals(other.itemName.toLowerCase());
        }

        // all defaults only need to match
        return defaultsMatch
                && this.destination.equals(other.destination)
                && this.status.equals(other.status);
    }
}
