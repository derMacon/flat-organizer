package com.dermacon.securewebapp.data;

/**
 * Very similar to the item class. Only difference, it is only necessary
 * to provide a room id instead of a full room object
 */
public class InputItem {

    private int itemCount;
    private String itemName;
    private long roomId;
    private boolean status;


    public InputItem() {}

    public InputItem(int itemCount, String itemName, long roomId, boolean status) {
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.roomId = roomId;
        this.status = status;
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

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InputItem{" +
                "itemCount=" + itemCount +
                ", itemName='" + itemName + '\'' +
                ", roomId=" + roomId +
                ", status=" + status +
                '}';
    }
}
