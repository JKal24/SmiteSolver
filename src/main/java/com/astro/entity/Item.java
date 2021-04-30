package com.astro.entity;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getItemID() == item.getItemID() && getItemName().equals(item.getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemID(), getItemName());
    }
}
