package com.quiz.servlet;

import com.quiz.dao.ResultDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect("login.html");
            return;
        }

        int userId =
                (Integer) session.getAttribute("userId");

        ResultDAO resultDAO =
                new ResultDAO();

        List<String[]> results =
                resultDAO.getResultsByUserId(userId);

        request.setAttribute(
                "results",
                results
        );

        request.getRequestDispatcher(
                "/history.jsp"
        ).forward(request, response);
    }
}