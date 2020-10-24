package com.dermacon.securewebapp.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SupplyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_Id;

    private String category_name;

    public SupplyCategory() {}

    public SupplyCategory(String category_name) {
        this.category_name = category_name;
    }

    public long getCategory_Id() {
        return category_Id;
    }

    public void setCategory_Id(long category_Id) {
        this.category_Id = category_Id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "SupplyCategory{" +
                "category_Id=" + category_Id +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
