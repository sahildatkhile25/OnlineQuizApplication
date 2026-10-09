<%@ page import="com.quiz.model.Question" %>

<%
    Question question =
            (Question) request.getAttribute("question");

    Integer questionNumber =
            (Integer) request.getAttribute("questionNumber");

    Integer totalQuestions =
            (Integer) request.getAttribute("totalQuestions");

    Object timeRemainingObject =
            request.getAttribute("timeRemaining");

    long timeRemaining =
            0;

    if (timeRemainingObject instanceof Number) {

        timeRemaining =
                ((Number) timeRemainingObject).longValue();
    }

    if (question == null ||
            questionNumber == null ||
            totalQuestions == null) {

        response.sendRedirect("quiz-list");
        return;
    }
%>

<!DOCTYPE html>

<html>

<head>

    <title>Quiz - Online Quiz</title>

    <link rel="stylesheet" href="css/style.css">

</head>

<body>

<div class="container">

    <h1>Online Quiz</h1>

    <h2>
        Question <%= questionNumber %>
        of
        <%= totalQuestions %>
    </h2>

    <div class="quiz-timer">
        Time Remaining:
        <span id="timer"><%= timeRemaining %></span>
        seconds
    </div>

    <div class="quiz-question">

        <h3>
            <%= question.getQuestionText() %>
        </h3>

        <% if (question.getDifficulty() != null &&
                !question.getDifficulty().trim().isEmpty()) { %>

            <p class="difficulty-badge">
                Difficulty:
                <%= question.getDifficulty() %>
            </p>

        <% } %>

        <form action="answer"
              method="post"
              id="answerForm">

            <label>
                <input type="radio"
                       name="answer"
                       value="A"
                       required>
                A. <%= question.getOptionA() %>
            </label>

            <label>
                <input type="radio"
                       name="answer"
                       value="B">
                B. <%= question.getOptionB() %>
            </label>

            <label>
                <input type="radio"
                       name="answer"
                       value="C">
                C. <%= question.getOptionC() %>
            </label>

            <label>
                <input type="radio"
                       name="answer"
                       value="D">
                D. <%= question.getOptionD() %>
            </label>

            <button type="submit"
                    id="submitButton">
                Submit Answer
            </button>

        </form>

    </div>

</div>

<script>
    const timer = document.getElementById("timer");
    const submitButton = document.getElementById("submitButton");

    let timeRemaining =
        parseInt(timer.textContent.trim(), 10);

    if (isNaN(timeRemaining) || timeRemaining < 0) {
        timeRemaining = 0;
    }

    function finishQuiz() {
        timer.textContent = "0";

        if (submitButton) {
            submitButton.disabled = true;
        }

        window.location.href = "timeout";
    }

    if (timeRemaining <= 0) {
        finishQuiz();
    } else {
        const interval = setInterval(function () {

            timeRemaining--;

            if (timeRemaining <= 0) {

                clearInterval(interval);
                finishQuiz();
                return;
            }

            timer.textContent = timeRemaining;

        }, 1000);
    }
</script>
</body>

</html>
