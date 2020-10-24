package com.dermacon.securewebapp.data;

import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@Entity
public class ItemPreset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long presetId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private SupplyCategory category;

    private String presetName;

    public ItemPreset() {}

    public ItemPreset(SupplyCategory category, String presetName) {
        this.category = category;
        this.presetName = presetName;
    }

    public long getPresetId() {
        return presetId;
    }

    public void setPresetId(long presetId) {
        this.presetId = presetId;
    }

    public SupplyCategory getCategory() {
        return category;
    }

    public void setCategory(SupplyCategory category) {
        this.category = category;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    @Override
    public String toString() {
        return "ItemPreset{" +
                "presetId=" + presetId +
                ", category=" + category +
                ", presetName='" + presetName + '\'' +
                '}';
    }
}
