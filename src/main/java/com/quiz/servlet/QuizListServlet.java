package com.quiz.servlet;

import com.quiz.dao.QuizDAO;
import com.quiz.model.Quiz;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/quiz-list")
public class QuizListServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        // Check whether user is logged in
        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect("login.html");
            return;
        }

        QuizDAO quizDAO = new QuizDAO();

        List<Quiz> quizzes =
                quizDAO.getAllQuizzes();

        request.setAttribute("quizzes", quizzes);

        request.getRequestDispatcher(
                "/dashboard.jsp"
        ).forward(request, response);
    }
}