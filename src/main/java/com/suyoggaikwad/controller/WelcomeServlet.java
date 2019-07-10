package com.suyoggaikwad.controller;

import com.suyoggaikwad.model.Item;
import com.suyoggaikwad.service.ServletProjectService;
import com.suyoggaikwad.service.ServletProjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletProjectService service = new ServletProjectServiceImpl();

//        int userID = (Integer) request.getSession().getAttribute("userId");
            
        List<Item> items = service.getItems();

        int quantity = 0;
        if(!items.isEmpty())  for(Item item: items)  quantity+=item.getQuantity();

        if(quantity > 0) {
            request.getSession().setAttribute("availabilityMsg", "What would you like to buy today?");
            request.getSession().setAttribute("itemsList", items);
        }
        else request.getSession().setAttribute("availabilityMsg", "Sorry! We are currently out of stock.");

        request.getRequestDispatcher("welcome.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
