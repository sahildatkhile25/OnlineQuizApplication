<%@ page import="com.quiz.model.Question" %>

<%
    Question question =
            (Question) request.getAttribute("question");

    int questionNumber =
            (Integer) request.getAttribute("questionNumber");

    int totalQuestions =
            (Integer) request.getAttribute("totalQuestions");
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

    <div class="quiz-question">

        <h3>
            <%= question.getQuestionText() %>
        </h3>

        <form action="answer" method="post">

            <label>
                <input
                    type="radio"
                    name="answer"
                    value="A"
                    required
                >
                A. <%= question.getOptionA() %>
            </label>

            <br>

            <label>
                <input
                    type="radio"
                    name="answer"
                    value="B"
                >
                B. <%= question.getOptionB() %>
            </label>

            <br>

            <label>
                <input
                    type="radio"
                    name="answer"
                    value="C"
                >
                C. <%= question.getOptionC() %>
            </label>

            <br>

            <label>
                <input
                    type="radio"
                    name="answer"
                    value="D"
                >
                D. <%= question.getOptionD() %>
            </label>

            <button type="submit">
                Submit Answer
            </button>

        </form>

    </div>

</div>

</body>

</html>