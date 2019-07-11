package com.suyoggaikwad.service;

import com.suyoggaikwad.dao.Dao;
import com.suyoggaikwad.dao.DaoImpl;
import com.suyoggaikwad.model.Cart;
import com.suyoggaikwad.model.Item;

import java.util.List;

public class ServletProjectServiceImpl implements ServletProjectService {

    Dao dao = new DaoImpl();

    @Override
    public int validateUser(String userName, String password) {
        return dao.validateUser(userName, password);
    }

    @Override
    public boolean registerUser(String userName, String password) {
        return dao.registerUser(userName, password);
    }

    @Override
    public List<Item> getItems() {
        return dao.getItems();
    }

    @Override
    public boolean addToCart(List<Cart> carts, int userId) {
       return dao.addToCart(carts, userId);
    }

    @Override
    public List<Cart> checkCartForUser(int userId) {
        return dao.checkCartForUser(userId);
    }

    @Override
    public boolean checkout(int userId) {
        return dao.checkout(userId);
    }
}
