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

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {

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

        Object questionsObject =
                session.getAttribute("questions");

        if (!(questionsObject instanceof List<?>)) {
            response.sendRedirect("quiz-list");
            return;
        }

        List<?> questionList =
                (List<?>) questionsObject;

        Object currentQuestionObject =
                session.getAttribute("currentQuestion");

        if (!(currentQuestionObject instanceof Integer)) {
            response.sendRedirect("quiz-list");
            return;
        }

        int currentQuestion =
                (Integer) currentQuestionObject;

        if (currentQuestion < 0) {
            response.sendRedirect("quiz-list");
            return;
        }

        if (currentQuestion >= questionList.size()) {

            session.removeAttribute("quizEndTime");
            session.removeAttribute("remainingSeconds");

            response.sendRedirect("result");
            return;
        }

        Long quizEndTime =
                (Long) session.getAttribute("quizEndTime");

        Long remainingSeconds =
                (Long) session.getAttribute("remainingSeconds");

        if (quizEndTime != null) {

            long remainingMillis =
                    quizEndTime - System.currentTimeMillis();

            if (remainingMillis <= 0) {

                session.removeAttribute("quizEndTime");
                session.removeAttribute("remainingSeconds");

                response.sendRedirect("timeout");
                return;
            }

            remainingSeconds =
                    (remainingMillis + 999) / 1000;

        } else if (remainingSeconds != null) {

            if (remainingSeconds <= 0) {

                session.removeAttribute("remainingSeconds");

                response.sendRedirect("timeout");
                return;
            }

            long newEndTime =
                    System.currentTimeMillis()
                            + (remainingSeconds * 1000L);

            session.setAttribute(
                    "quizEndTime",
                    newEndTime
            );

            session.removeAttribute(
                    "remainingSeconds"
            );

        } else {

            response.sendRedirect("quiz-list");
            return;
        }

        Object questionObject =
                questionList.get(currentQuestion);

        if (!(questionObject instanceof Question)) {
            response.sendRedirect("quiz-list");
            return;
        }

        Question question =
                (Question) questionObject;

        request.setAttribute(
                "question",
                question
        );

        request.setAttribute(
                "questionNumber",
                currentQuestion + 1
        );

        request.setAttribute(
                "totalQuestions",
                questionList.size()
        );

        request.setAttribute(
                "timeRemaining",
                remainingSeconds
        );

        session.removeAttribute(
                "answerSubmitted"
        );



        request.getRequestDispatcher(
                "/quiz.jsp"
        ).forward(request, response);
    }
}
