package com.quiz.servlet;

import com.quiz.dao.QuizDAO;
import com.quiz.model.Quiz;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/add-quiz")
public class AddQuizServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/admin/add-quiz.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String title =
                request.getParameter("title");

        String topic =
                request.getParameter("topic");

        if (title == null ||
                topic == null ||
                title.trim().isEmpty() ||
                topic.trim().isEmpty()) {

            response.sendRedirect(
                    "add-quiz?error=empty"
            );

            return;
        }

        Quiz quiz = new Quiz();

        quiz.setTitle(title.trim());
        quiz.setTopic(topic.trim());

        QuizDAO quizDAO =
                new QuizDAO();

        boolean added =
                quizDAO.addQuiz(quiz);

        if (added) {

            response.sendRedirect(
                    "dashboard"
            );

        } else {

            response.sendRedirect(
                    "add-quiz?error=failed"
            );
        }
    }
}