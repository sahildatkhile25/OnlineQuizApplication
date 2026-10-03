package com.quiz.servlet;

import com.quiz.dao.ResultDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/allow-retry")
public class AllowRetryServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String userIdParameter =
                request.getParameter("userId");

        String quizIdParameter =
                request.getParameter("quizId");

        if (userIdParameter == null ||
                quizIdParameter == null) {

            response.sendRedirect("attempts");
            return;
        }

        int userId =
                Integer.parseInt(userIdParameter);

        int quizId =
                Integer.parseInt(quizIdParameter);

        ResultDAO resultDAO =
                new ResultDAO();

        resultDAO.allowRetry(
                userId,
                quizId
        );

        response.sendRedirect(
                "attempts"
        );
    }
}