package com.quiz.servlet;

import com.quiz.dao.QuestionDAO;
import com.quiz.model.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/add-question")
public class AddQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String quizId =
                request.getParameter("quizId");

        request.setAttribute(
                "quizId",
                quizId
        );

        request.getRequestDispatcher(
                "/admin/add-question.jsp"
        ).forward(request, response);
    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int quizId =
                Integer.parseInt(
                        request.getParameter("quizId")
                );

        String questionText =
                request.getParameter("questionText");

        String optionA =
                request.getParameter("optionA");

        String optionB =
                request.getParameter("optionB");

        String optionC =
                request.getParameter("optionC");

        String optionD =
                request.getParameter("optionD");

        String correctAnswer =
                request.getParameter("correctAnswer");


        if (questionText == null ||
                optionA == null ||
                optionB == null ||
                optionC == null ||
                optionD == null ||
                correctAnswer == null ||
                questionText.trim().isEmpty() ||
                optionA.trim().isEmpty() ||
                optionB.trim().isEmpty() ||
                optionC.trim().isEmpty() ||
                optionD.trim().isEmpty()) {

            response.sendRedirect(
                    "add-question?quizId="
                    + quizId
                    + "&error=empty"
            );

            return;
        }


        Question question =
                new Question();

        question.setQuizId(quizId);

        question.setQuestionText(
                questionText.trim()
        );

        question.setOptionA(
                optionA.trim()
        );

        question.setOptionB(
                optionB.trim()
        );

        question.setOptionC(
                optionC.trim()
        );

        question.setOptionD(
                optionD.trim()
        );

        question.setCorrectAnswer(
                correctAnswer
        );


        QuestionDAO questionDAO =
                new QuestionDAO();

        boolean added =
                questionDAO.addQuestion(question);


        if (added) {

            response.sendRedirect(
                    "questions?quizId="
                    + quizId
            );

        } else {

            response.sendRedirect(
                    "add-question?quizId="
                    + quizId
                    + "&error=failed"
            );
        }
    }
}