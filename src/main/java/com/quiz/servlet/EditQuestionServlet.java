package com.quiz.servlet;

import com.quiz.dao.QuestionDAO;
import com.quiz.model.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/edit-question")
public class EditQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String idParameter =
                request.getParameter("id");

        if (idParameter == null) {

            response.sendRedirect("dashboard");
            return;
        }

        int questionId =
                Integer.parseInt(idParameter);

        QuestionDAO questionDAO =
                new QuestionDAO();

        Question question =
                questionDAO.getQuestionById(questionId);

        if (question == null) {

            response.sendRedirect("dashboard");
            return;
        }

        request.setAttribute(
                "question",
                question
        );

        request.getRequestDispatcher(
                "/admin/edit-question.jsp"
        ).forward(request, response);
    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int questionId =
                Integer.parseInt(
                        request.getParameter("id")
                );

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

        String difficulty =
        request.getParameter("difficulty");

        


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
                    "edit-question?id="
                    + questionId
                    + "&error=empty"
            );

            return;
        }


        Question question =
                new Question();

        question.setId(questionId);

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

        question.setDifficulty(
        difficulty
);      


        QuestionDAO questionDAO =
                new QuestionDAO();

        boolean updated =
                questionDAO.updateQuestion(question);


        if (updated) {

            response.sendRedirect(
                    "questions?quizId="
                    + quizId
            );

        } else {

            response.sendRedirect(
                    "edit-question?id="
                    + questionId
                    + "&error=failed"
            );
        }
    }
}