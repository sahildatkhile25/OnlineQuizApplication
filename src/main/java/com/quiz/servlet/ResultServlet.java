package com.quiz.servlet;

import com.quiz.dao.ResultDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {

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

        int userId =
                (Integer) session.getAttribute("userId");

        int quizId =
                (Integer) session.getAttribute("quizId");

        int score =
                (Integer) session.getAttribute("score");

        List<?> questions =
                (List<?>) session.getAttribute("questions");

        int totalQuestions =
                questions.size();

        Boolean resultSaved =
                (Boolean) session.getAttribute("resultSaved");

        boolean saved = false;

        if (resultSaved == null || !resultSaved) {

    ResultDAO resultDAO =
            new ResultDAO();

    saved =
            resultDAO.saveResult(
                    userId,
                    quizId,
                    score,
                    totalQuestions
            );

    if (saved) {

        session.setAttribute(
                "resultSaved",
                true
        );
    }
}

        request.setAttribute(
                "score",
                score
        );

        request.setAttribute(
                "totalQuestions",
                totalQuestions
        );

        request.setAttribute(
                "saved",
                saved
        );

        request.getRequestDispatcher(
                "/result.jsp"
        ).forward(request, response);
    }
}