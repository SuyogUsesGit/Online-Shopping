package com.suyoggaikwad.model;

public class Cart {
    private Item item;
    private int userId;

    public Cart(Item item, int userId) {
        this.item = item;
        this.userId = userId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
