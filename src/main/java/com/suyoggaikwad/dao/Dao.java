package com.suyoggaikwad.dao;

import com.suyoggaikwad.model.Cart;
import com.suyoggaikwad.model.Item;

import java.util.List;

public interface Dao {
    int validateUser(String userName, String password);

    boolean registerUser(String userName, String password);

    List<Item> getItems();

    boolean addToCart(List<Cart> carts, int userId);

    List<Cart> checkCartForUser(int userId);

    boolean checkout(int userId);
}
