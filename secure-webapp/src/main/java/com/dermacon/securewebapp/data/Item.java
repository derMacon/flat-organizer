package com.dermacon.securewebapp.data;

import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Arrays;
import java.util.stream.Stream;

@Entity
public class Item {

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
        String lowerCaseName = itemName.toLowerCase();

        boolean isKitchenSupply = Arrays.stream(ItemCategory.KITCHEN_SUPPLY.getItemNames())
                .filter(lowerCaseName::equals).findAny().isPresent();
        if (isKitchenSupply) {
            return ItemCategory.KITCHEN_SUPPLY;
        }

        boolean isBathroomSupply = Arrays.stream(ItemCategory.BATHROOM_SUPPLY.getItemNames())
                .filter(lowerCaseName::equals).findAny().isPresent();
        if (isBathroomSupply) {
            return ItemCategory.BATHROOM_SUPPLY;
        }

        return ItemCategory.COSTUM_SUPPLY;
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
}
