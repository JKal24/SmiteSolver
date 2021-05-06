package com.astro.SmiteSolver.entity.auxillary;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "base_item_name")
public class BaseItemName {

    @Id
    private int itemID;

    private String itemName;

    private Integer itemTier;

    private String itemIconURL;

    public BaseItemName(int itemID, String itemName, Integer itemTier, String itemIconURL) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemTier = itemTier;
        this.itemIconURL = itemIconURL;
    }

    public BaseItemName() { }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemTier() {
        return itemTier;
    }

    public void setItemTier(Integer itemTier) {
        this.itemTier = itemTier;
    }

    public String getItemIconURL() {
        return itemIconURL;
    }

    public void setItemIconURL(String itemIconURL) {
        this.itemIconURL = itemIconURL;
    }
}
