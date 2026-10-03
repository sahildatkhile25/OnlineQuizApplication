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

@WebServlet("/answer")
public class AnswerServlet extends HttpServlet {

    @Override
    protected void doPost(
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

        int score =
                (Integer) session.getAttribute("score");

        String selectedAnswer =
                request.getParameter("answer");

        Question question =
                (Question) questionList.get(currentQuestion);

        if (selectedAnswer != null &&
        selectedAnswer.equals(
                question.getCorrectAnswer())) {

            score++;

            session.setAttribute(
                    "feedback",
                    "Correct Answer!"
            );

        } else {

            session.setAttribute(
                    "feedback",
                    "Incorrect Answer!"
            );
        }

        session.setAttribute(
                "score",
                score
        );

        currentQuestion++;

        session.setAttribute(
                "currentQuestion",
                currentQuestion
        );

        response.sendRedirect("feedback");
    }
}