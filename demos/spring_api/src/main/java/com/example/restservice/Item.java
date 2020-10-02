package com.example.restservice;

public class Item {

    private String name;
    private int count;
    private String requester;

    public Item() {}

    public Item(String name, int count, String requester) {
        this.name = name;
        this.count = count;
        this.requester = requester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", requester='" + requester + '\'' +
                '}';
    }
}
