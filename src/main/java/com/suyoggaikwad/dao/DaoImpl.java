package com.suyoggaikwad.dao;

import com.suyoggaikwad.model.Cart;
import com.suyoggaikwad.model.Item;

import java.sql.*;
import java.util.*;

public class DaoImpl implements Dao {

    @Override
    public int validateUser(String userName, String password) {
        try (Connection conn = DBUtil.getDataSource().getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("select * from user where username = " + "'" + userName + "' && password = " + "'" + password + "'")) {

            while (rs.next()) {
                if(rs.getString("username").equals(userName) && rs.getString("password").equals(password)) return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean registerUser(String userName, String password) {
        try (Connection conn = DBUtil.getDataSource().getConnection();
              Statement statement = conn.createStatement()) {

            statement.executeUpdate("insert into user(username, password) values(" + "'" + userName + "', '" + password + "')");

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        try(Connection conn = DBUtil.getDataSource().getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("select * from item where quantity > 0")) {

            while (rs.next()) {
                Item item = new Item(rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"));
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public boolean addToCart(List<Cart> carts, int userId) {
        try (Connection conn = DBUtil.getDataSource().getConnection();
             Statement statement = conn.createStatement()){

            statement.executeUpdate("delete from cart where user_id = " + userId);

            for (Cart cart: carts) {
                statement.executeUpdate("insert into cart values(" + cart.getUserId() + ", '" + cart.getItem().getName() + "', " + cart.getItem().getQuantity() + ", " + cart.getItem().getPrice() + ")");
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Cart> checkCartForUser(int userId) {
        List<Cart> carts = new ArrayList<>();
        try (Connection conn = DBUtil.getDataSource().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from cart where user_id = " + userId)) {

            while (rs.next()) {
                Cart cart = new Cart(new Item(rs.getString("item_name"), rs.getInt("item_quantity"), rs.getDouble("item_price")), userId);
                carts.add(cart);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carts;
    }

    @Override
    public boolean checkout(int userId) {

        try (Connection conn = DBUtil.getDataSource().getConnection();
             Statement statement1 = conn.createStatement();
             Statement statement2 = conn.createStatement();
             ResultSet rs1 = statement1.executeQuery("select * from item");
             ResultSet rs2 = statement2.executeQuery("select * from cart where user_id = " + userId)) {

            Map<String, Integer> itemsMap = new HashMap<>();
            while (rs1.next()) itemsMap.put(rs1.getString("name"), rs1.getInt("quantity"));

            Map<String, Integer> cartItemsMap = new HashMap<>();
            while (rs2.next()) cartItemsMap.put(rs2.getString("item_name"), rs2.getInt("item_quantity"));

            Map<String, Integer> rsMap = new HashMap<>();

            for(String s: cartItemsMap.keySet()) {
                if(itemsMap.containsKey(s)) {
                    int quantityAvailable = itemsMap.get(s);
                    int quantityRequired = cartItemsMap.get(s);
                    if(quantityAvailable >= quantityRequired) {
                        int quantityRemaining = quantityAvailable - quantityRequired;
                        rsMap.put(s, quantityRemaining);
                    } else return false;
                }
            }

            for(String s: rsMap.keySet())  statement1.executeUpdate("update item set quantity = " + rsMap.get(s) + " where name = " + "'" + s + "'");

            statement2.executeUpdate("delete from cart where user_id = " + userId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
