package com.dermacon.securewebapp.data;

import org.apache.commons.pool2.BaseObject;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;

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

        return this.itemName.toLowerCase().equals(other.itemName.toLowerCase())
                && this.destination.equals(other.destination)
                && this.status.equals(other.status);
    }
}
