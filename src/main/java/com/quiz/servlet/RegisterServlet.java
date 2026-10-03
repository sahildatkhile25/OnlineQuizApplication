package com.quiz.servlet;

import com.quiz.PasswordUtil;
import com.quiz.dao.UserDAO;
import com.quiz.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null ||
                username.trim().isEmpty() ||
                password.trim().isEmpty()) {

            response.sendRedirect("register.html?error=empty");
            return;
        }

        username = username.trim();

        if (password.length() < 6) {

            response.sendRedirect("register.html?error=password");
            return;
        }

        UserDAO userDAO = new UserDAO();

        if (userDAO.getUserByUsername(username) != null) {

            response.sendRedirect("register.html?error=exists");
            return;
        }

        String hashedPassword =
                PasswordUtil.hashPassword(password);

        User user = new User(
                username,
                hashedPassword,
                "USER"
        );

        boolean registered =
                userDAO.registerUser(user);

        if (registered) {

            response.sendRedirect("login.html?registered=true");

        } else {

            response.sendRedirect("register.html?error=failed");
        }
    }
}