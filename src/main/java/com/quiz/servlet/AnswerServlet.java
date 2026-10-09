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

@WebServlet("/answer")
public class AnswerServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        // -------------------------------------------------
        // 1. Check whether quiz session exists
        // -------------------------------------------------

        if (session == null ||
                session.getAttribute("questions") == null) {

            response.sendRedirect("quiz-list");
            return;
        }


        // -------------------------------------------------
        // 2. Check quiz timer
        // -------------------------------------------------

        Long quizEndTime =
                (Long) session.getAttribute("quizEndTime");

        if (quizEndTime == null) {

            response.sendRedirect("quiz-list");
            return;
        }

        long remainingMillis =
                quizEndTime - System.currentTimeMillis();


        // -------------------------------------------------
        // 3. If timer has expired, stop the quiz
        // -------------------------------------------------

        if (remainingMillis <= 0) {

            session.removeAttribute("quizEndTime");
            session.removeAttribute("remainingSeconds");

            response.sendRedirect("timeout");
            return;
        }

        long remainingSeconds =
                (remainingMillis + 999) / 1000;


        // -------------------------------------------------
        // 5. Get questions from session
        // -------------------------------------------------

        Object questionsObject =
                session.getAttribute("questions");

        if (!(questionsObject instanceof List<?>)) {

            response.sendRedirect("quiz-list");
            return;
        }

        List<?> questionList =
                (List<?>) questionsObject;


        // -------------------------------------------------
        // 6. Validate current question index
        // -------------------------------------------------

        Object currentQuestionObject =
                session.getAttribute("currentQuestion");

        if (!(currentQuestionObject instanceof Integer)) {

            response.sendRedirect("quiz-list");
            return;
        }

        int currentQuestion =
                (Integer) currentQuestionObject;

        if (currentQuestion < 0 ||
                currentQuestion >= questionList.size()) {

            response.sendRedirect("quiz-list");
            return;
        }


        // -------------------------------------------------
        // 7. Get current score
        // -------------------------------------------------

        Object scoreObject =
                session.getAttribute("score");

        int score = 0;

        if (scoreObject instanceof Integer) {

            score = (Integer) scoreObject;
        }


        // -------------------------------------------------
        // 8. Get current question
        // -------------------------------------------------

        Object questionObject =
                questionList.get(currentQuestion);

        if (!(questionObject instanceof Question)) {

            response.sendRedirect("quiz-list");
            return;
        }

        Question question =
                (Question) questionObject;


        // -------------------------------------------------
        // 9. Get selected answer
        // -------------------------------------------------

        String selectedAnswer =
                request.getParameter("answer");


        // -------------------------------------------------
        // 10. Check answer
        // -------------------------------------------------

        String correctAnswer =
                question.getCorrectAnswer();

        boolean correct =
                selectedAnswer != null &&
                        correctAnswer != null &&
                        selectedAnswer.trim().equalsIgnoreCase(
                                correctAnswer.trim()
                        );

        if (correct) {

            score++;

            session.setAttribute(
                    "feedback",
                    "Correct Answer!"
            );

            session.setAttribute(
                    "feedbackType",
                    "correct"
            );

        } else {

            session.setAttribute(
                    "feedback",
                    "Incorrect Answer!"
            );

            session.setAttribute(
                    "feedbackType",
                    "incorrect"
            );
        }


        // -------------------------------------------------
        // 11. Update score
        // -------------------------------------------------

        session.setAttribute(
                "score",
                score
        );


        // -------------------------------------------------
        // 12. Move to next question
        // -------------------------------------------------

        currentQuestion++;

        session.setAttribute(
                "currentQuestion",
                currentQuestion
        );


        // -------------------------------------------------
        // 13. Mark answer as submitted
        // -------------------------------------------------

        session.setAttribute(
                "answerSubmitted",
                true
        );

        // Pause the timer while feedback is displayed.
        session.setAttribute(
                "remainingSeconds",
                remainingSeconds
        );

        session.removeAttribute(
                "quizEndTime"
        );


        // -------------------------------------------------
        // 14. Go to feedback page
        // -------------------------------------------------

        response.sendRedirect("feedback");
    }
}
