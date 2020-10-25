package com.dermacon.securewebapp.data;

import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private SupplyCategory supplyCategory;

    private String presetName;

    public ItemPreset() {}

    public ItemPreset(SupplyCategory supplyCategory, String presetName) {
        this.supplyCategory = supplyCategory;
        this.presetName = presetName;
    }

    public long getPresetId() {
        return presetId;
    }

    public void setPresetId(long presetId) {
        this.presetId = presetId;
    }

    public SupplyCategory getSupplyCategory() {
        return supplyCategory;
    }

    public void setSupplyCategory(SupplyCategory supplyCategory) {
        this.supplyCategory = supplyCategory;
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
                ", supplyCategory=" + supplyCategory +
                ", presetName='" + presetName + '\'' +
                '}';
    }
}
