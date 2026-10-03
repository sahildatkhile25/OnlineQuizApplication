package com.quiz.servlet;

import com.quiz.dao.QuizDAO;
import com.quiz.model.Quiz;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        QuizDAO quizDAO =
                new QuizDAO();

        List<Quiz> quizzes =
                quizDAO.getAllQuizzes();

        request.setAttribute(
                "quizzes",
                quizzes
        );

        request.getRequestDispatcher(
                "/admin/dashboard.jsp"
        ).forward(request, response);
    }
}