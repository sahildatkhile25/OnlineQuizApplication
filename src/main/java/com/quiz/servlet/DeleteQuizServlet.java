package com.quiz.servlet;

import com.quiz.dao.QuizDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/delete-quiz")
public class DeleteQuizServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String idParameter =
                request.getParameter("id");

        if (idParameter == null) {

            response.sendRedirect("dashboard");
            return;
        }

        int quizId =
                Integer.parseInt(idParameter);

        QuizDAO quizDAO =
                new QuizDAO();

        quizDAO.deleteQuiz(quizId);

        response.sendRedirect(
                "dashboard"
        );
    }
}