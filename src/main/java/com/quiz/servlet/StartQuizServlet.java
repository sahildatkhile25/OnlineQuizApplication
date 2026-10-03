package com.quiz.servlet;

import com.quiz.dao.QuestionDAO;
import com.quiz.dao.ResultDAO;
import com.quiz.model.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/start-quiz")
public class StartQuizServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get existing session
        HttpSession session =
                request.getSession(false);

        // User must be logged in
        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect("login.html");
            return;
        }

        // Get quiz ID
        String idParameter =
                request.getParameter("id");

        if (idParameter == null ||
                idParameter.trim().isEmpty()) {

            response.sendRedirect("quiz-list");
            return;
        }

        int quizId;

        try {

            quizId =
                    Integer.parseInt(idParameter);

        } catch (NumberFormatException e) {

            response.sendRedirect("quiz-list");
            return;
        }


        // Get logged-in user's ID
        int userId =
                (Integer) session.getAttribute("userId");


        // Check whether the user has already attempted
        // this quiz
        ResultDAO resultDAO =
                new ResultDAO();

        boolean alreadyAttempted =
                resultDAO.hasAttemptedQuiz(
                        userId,
                        quizId
                );


        if (alreadyAttempted) {

            // Check whether admin has allowed retry
            boolean canRetry =
                    resultDAO.canRetryQuiz(
                            userId,
                            quizId
                    );


            // If retry is not allowed,
            // user cannot start the quiz again
            if (!canRetry) {

                response.sendRedirect(
                        "quiz-list?error=already-given"
                );

                return;
            }
        }


        // Get questions for selected quiz
        QuestionDAO questionDAO =
                new QuestionDAO();

        List<Question> questions =
                questionDAO.getQuestionsByQuizId(
                        quizId
                );


        // If quiz has no questions
        if (questions.isEmpty()) {

            response.sendRedirect(
                    "quiz-list?error=no-questions"
            );

            return;
        }


        // Store quiz information in session
        session.setAttribute(
                "quizId",
                quizId
        );

        session.setAttribute(
                "questions",
                questions
        );

        // Start from first question
        session.setAttribute(
                "currentQuestion",
                0
        );

        // Reset score
        session.setAttribute(
                "score",
                0
        );

        // New attempt must be saved
        // or existing result must be updated
        session.setAttribute(
                "resultSaved",
                false
        );


        // Remove previous feedback
        session.removeAttribute(
                "feedback"
        );


        // Start quiz
        response.sendRedirect("quiz");
    }
}