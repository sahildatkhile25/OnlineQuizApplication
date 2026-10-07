package com.quiz.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/timeout")
public class TimeoutServlet extends HttpServlet {

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

        session.removeAttribute("quizEndTime");
        session.removeAttribute("remainingSeconds");
        session.removeAttribute("answerSubmitted");

        Object questionsObject =
                session.getAttribute("questions");

        if (!(questionsObject instanceof List<?>)) {

            response.sendRedirect("quiz-list");
            return;
        }

        List<?> questions =
                (List<?>) questionsObject;

        session.setAttribute(
                "currentQuestion",
                questions.size()
        );

        session.setAttribute(
                "feedback",
                "Time's up!"
        );

        response.sendRedirect("result");
    }
}
