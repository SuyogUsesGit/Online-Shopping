package com.suyoggaikwad.controller;

import com.suyoggaikwad.service.ServletProjectService;
import com.suyoggaikwad.service.ServletProjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletProjectService service = new ServletProjectServiceImpl();
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        int userId = service.validateUser(userName, password);

        if(userId != -1) {
            request.getSession().setAttribute("userId", userId);
            request.getSession().setAttribute("username", userName);
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("service", service);
            request.getRequestDispatcher("/WelcomeServlet").forward(request, response);

        } else {
            request.getSession().setAttribute("diff_msgs", "Invalid login details! Please try again.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if(null != userId) response.sendRedirect("welcome.jsp");
        else response.sendRedirect("index.jsp");
    }
}
