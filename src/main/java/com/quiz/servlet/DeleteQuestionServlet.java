package com.quiz.servlet;

import com.quiz.dao.QuestionDAO;
import com.quiz.model.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/delete-question")
public class DeleteQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String idParameter =
                request.getParameter("id");

        String quizIdParameter =
                request.getParameter("quizId");


        if (idParameter == null ||
                quizIdParameter == null) {

            response.sendRedirect("dashboard");
            return;
        }


        int questionId =
                Integer.parseInt(idParameter);

        int quizId =
                Integer.parseInt(quizIdParameter);


        QuestionDAO questionDAO =
                new QuestionDAO();

        questionDAO.deleteQuestion(questionId);


        response.sendRedirect(
                "questions?quizId="
                + quizId
        );
    }
}