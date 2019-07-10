package com.suyoggaikwad.controller;

import com.suyoggaikwad.service.ServletProjectService;
import com.suyoggaikwad.service.ServletProjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletProjectService service = new ServletProjectServiceImpl();
        String userName = (request.getParameter("username"));
        if(service.registerUser(userName, request.getParameter("password"))) request.setAttribute("diff_msgs", "You have registered Successfully.");

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
