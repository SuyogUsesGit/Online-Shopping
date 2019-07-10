package com.suyoggaikwad.controller;

import com.suyoggaikwad.model.Cart;
import com.suyoggaikwad.service.ServletProjectService;
import com.suyoggaikwad.service.ServletProjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletProjectService service = new ServletProjectServiceImpl();

        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if(null == userId) response.sendRedirect("index.jsp");


        List<Cart> cartList = service.checkCartForUser(userId);

        List<Cart> checkoutCart = service.checkout(cartList, userId);

        if(!checkoutCart.isEmpty()) request.setAttribute("checkoutMsg", "Thank you for shopping with us!");
        else request.setAttribute("checkoutMsg", "Sorry! We cannot process your cart as some of the requested items from your cart are no longer available.");

        request.getRequestDispatcher("checkout.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if(null == userId) response.sendRedirect("index.jsp");
        else response.sendRedirect("welcome.jsp");
    }
}
