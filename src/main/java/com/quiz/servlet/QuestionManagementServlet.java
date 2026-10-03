package com.quiz.servlet;

import com.quiz.dao.QuestionDAO;
import com.quiz.dao.QuizDAO;
import com.quiz.model.Question;
import com.quiz.model.Quiz;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/questions")
public class QuestionManagementServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String idParameter =
                request.getParameter("quizId");

        if (idParameter == null) {

            response.sendRedirect("dashboard");
            return;
        }

        int quizId =
                Integer.parseInt(idParameter);

        QuestionDAO questionDAO =
                new QuestionDAO();

        QuizDAO quizDAO =
                new QuizDAO();

        List<Question> questions =
                questionDAO.getQuestionsByQuizId(quizId);

        Quiz quiz =
                quizDAO.getQuizById(quizId);

        request.setAttribute(
                "questions",
                questions
        );

        request.setAttribute(
                "quiz",
                quiz
        );

        request.getRequestDispatcher(
                "/admin/questions.jsp"
        ).forward(request, response);
    }
}