package com.quiz.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("feedback") == null) {

            response.sendRedirect("quiz-list");
            return;
        }

        Long remainingSeconds =
                (Long) session.getAttribute("remainingSeconds");

        // Normally AnswerServlet pauses the timer. This keeps
        // feedback safe even if the page is opened directly.
        if (remainingSeconds == null) {

            Long quizEndTime =
                    (Long) session.getAttribute("quizEndTime");

            if (quizEndTime != null) {

                long remainingMillis =
                        quizEndTime - System.currentTimeMillis();

                if (remainingMillis <= 0) {

                    session.removeAttribute("quizEndTime");

                    response.sendRedirect("timeout");
                    return;
                }

                remainingSeconds =
                        (remainingMillis + 999) / 1000;

                session.setAttribute(
                        "remainingSeconds",
                        remainingSeconds
                );

                session.removeAttribute("quizEndTime");
            }
        }

        if (remainingSeconds == null ||
                remainingSeconds <= 0) {

            session.removeAttribute("remainingSeconds");

            response.sendRedirect("timeout");
            return;
        }

        String feedback =
                (String) session.getAttribute("feedback");

        request.setAttribute(
                "feedback",
                feedback
        );

        request.setAttribute(
                "feedbackType",
                session.getAttribute("feedbackType")
        );

        boolean quizFinished =
                false;

        Object questionsObject =
                session.getAttribute("questions");

        Object currentQuestionObject =
                session.getAttribute("currentQuestion");

        if (questionsObject instanceof List<?> &&
                currentQuestionObject instanceof Integer) {

            List<?> questions =
                    (List<?>) questionsObject;

            int currentQuestion =
                    (Integer) currentQuestionObject;

            quizFinished =
                    currentQuestion >= questions.size();
        }

        request.setAttribute(
                "quizFinished",
                quizFinished
        );

        request.getRequestDispatcher(
                "/feedback.jsp"
        ).forward(request, response);
    }
}
