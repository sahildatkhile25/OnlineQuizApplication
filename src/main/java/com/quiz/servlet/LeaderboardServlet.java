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

@WebServlet("/leaderboard")
public class LeaderboardServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect("login.html");
            return;
        }

        ResultDAO resultDAO =
                new ResultDAO();

        List<String[]> leaderboard =
                resultDAO.getLeaderboard();

        request.setAttribute(
                "leaderboard",
                leaderboard
        );

        request.getRequestDispatcher(
                "/leaderboard.jsp"
        ).forward(request, response);
    }
}