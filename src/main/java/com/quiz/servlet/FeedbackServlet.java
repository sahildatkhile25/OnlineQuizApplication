package com.quiz.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

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

        String feedback =
                (String) session.getAttribute("feedback");

        request.setAttribute(
                "feedback",
                feedback
        );

        request.getRequestDispatcher(
                "/feedback.jsp"
        ).forward(request, response);
    }
}