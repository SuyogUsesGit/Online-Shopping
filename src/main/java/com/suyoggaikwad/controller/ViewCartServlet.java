package com.suyoggaikwad.controller;

import com.suyoggaikwad.model.Cart;
import com.suyoggaikwad.service.ServletProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewCartServlet")
public class ViewCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletProjectService service = (ServletProjectService) request.getSession().getAttribute("service");

        Integer userId = (Integer) request.getSession().getAttribute("userId");

        List<Cart> carts = service.checkCartForUser(userId);

        if(!carts.isEmpty()) {
            request.getSession().setAttribute("cartContentsMsg", "Your cart contains following items!");
            request.getSession().setAttribute("viewCartItemsList", carts);
            double total = 0;
            for(Cart cart: carts) total += cart.getItem().getPrice();
            request.getSession().setAttribute("totalAmount", Math.round(total * 100.0) / 100.0);
        } else {
            request.getSession().setAttribute("cartContentsMsg", "Your cart is Empty! Continue Shopping.");
        }

        request.getRequestDispatcher("view_cart.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if(null == userId) response.sendRedirect("index.jsp");
        else response.sendRedirect("welcome.jsp");

    }
}
