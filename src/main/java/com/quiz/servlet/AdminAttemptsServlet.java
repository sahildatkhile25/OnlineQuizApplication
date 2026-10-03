package com.quiz.servlet;

import com.quiz.dao.ResultDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/attempts")
public class AdminAttemptsServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ResultDAO resultDAO =
                new ResultDAO();

        List<String[]> attempts =
                resultDAO.getAllAttempts();

        request.setAttribute(
                "attempts",
                attempts
        );

        request.getRequestDispatcher(
                "/admin/attempts.jsp"
        ).forward(request, response);
    }
}