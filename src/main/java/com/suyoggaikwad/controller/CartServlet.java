package com.suyoggaikwad.controller;

import com.suyoggaikwad.model.Cart;
import com.suyoggaikwad.model.Item;
import com.suyoggaikwad.service.ServletProjectService;
import com.suyoggaikwad.service.ServletProjectServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletProjectService service = new ServletProjectServiceImpl();

        Integer userId = (Integer) request.getSession().getAttribute("userId");

        List<Cart> cartList = new ArrayList<>();
        int x = 0;
        while(request.getParameter("ip4_" + ++x) != null) {
            int quantityEntered = Integer.parseInt(request.getParameter("ip4_" + x));
            if(quantityEntered == 0) continue;
            String itemName = request.getParameter("ip1_" + x);
            double totalPrice = Double.parseDouble(request.getParameter("ip5_" + x));

            Item item = new Item(itemName, quantityEntered, totalPrice);
            Cart cart = new Cart(item, userId);
            cartList.add(cart);
        }

        if(service.addToCart(cartList, userId)) request.setAttribute("addedToCart_msg", "Your cart has been updated!");

        request.getRequestDispatcher("welcome.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if(null == userId) response.sendRedirect("index.jsp");
        else response.sendRedirect("welcome.jsp");

    }
}
