package com.suyoggaikwad.controller;

import com.suyoggaikwad.model.Item;
import com.suyoggaikwad.service.ServletProjectService;

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
        ServletProjectService service = (ServletProjectService) request.getSession().getAttribute("service");

        Integer userId = (Integer) request.getSession().getAttribute("userId");

        List<Item> items = service.getItems();

        if(!items.isEmpty())  {
            request.getSession().setAttribute("availabilityMsg", "What would you like to buy today?");
            request.getSession().setAttribute("itemsList", items);
        }  else request.getSession().setAttribute("availabilityMsg", "Sorry! We are currently out of stock.");

        request.getRequestDispatcher("welcome.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if(null == userId) response.sendRedirect("index.jsp");
        else response.sendRedirect("welcome.jsp");
    }
}
