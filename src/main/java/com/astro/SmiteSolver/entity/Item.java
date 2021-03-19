package com.astro.SmiteSolver.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Item {

    private int itemID;
    private String itemName;

    public Item(int itemID, String itemName) {
        this.itemID = itemID;
        this.itemName = itemName;
    }

    public Item() {
    }

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
}
