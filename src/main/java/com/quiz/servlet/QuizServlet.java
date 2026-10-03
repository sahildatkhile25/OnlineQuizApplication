package com.quiz.servlet;

import com.quiz.model.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("questions") == null) {

            response.sendRedirect("quiz-list");
            return;
        }

        Object questionsObject =
                session.getAttribute("questions");

        if (!(questionsObject instanceof List<?>)) {
            response.sendRedirect("quiz-list");
            return;
        }

        List<?> questionList =
                (List<?>) questionsObject;

        int currentQuestion =
                (Integer) session.getAttribute("currentQuestion");

        if (currentQuestion >= questionList.size()) {

            response.sendRedirect("result");
            return;
        }

        Question question =
                (Question) questionList.get(currentQuestion);

        request.setAttribute(
                "question",
                question
        );

        request.setAttribute(
                "questionNumber",
                currentQuestion + 1
        );

        request.setAttribute(
                "totalQuestions",
                questionList.size()
        );

        request.getRequestDispatcher(
                "/quiz.jsp"
        ).forward(request, response);
    }
}