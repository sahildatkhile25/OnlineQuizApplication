package com.quiz.servlet;

import com.quiz.PasswordUtil;
import com.quiz.dao.UserDAO;
import com.quiz.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null ||
                username.trim().isEmpty() ||
                password.isEmpty()) {

            response.sendRedirect("login.html?error=empty");
            return;
        }

        UserDAO userDAO = new UserDAO();

        User user =
                userDAO.getUserByUsername(username.trim());

        if (user != null &&
                PasswordUtil.checkPassword(
                        password,
                        user.getPassword())) {

            HttpSession session =
                    request.getSession();

            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            if ("ADMIN".equals(user.getRole())) {

    response.sendRedirect("admin/dashboard");

} else {

    response.sendRedirect("quiz-list");
}

        } else {

            response.sendRedirect("login.html?error=invalid");
        }
    }
}