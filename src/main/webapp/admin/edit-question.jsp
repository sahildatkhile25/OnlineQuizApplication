<%@ page import="com.quiz.model.Question" %>

<%
    Question question =
            (Question) request.getAttribute("question");
%>

<!DOCTYPE html>

<html>

<head>

    <title>Edit Question</title>

    <link rel="stylesheet"
          href="../css/style.css">

</head>

<body>

<div class="container">

    <h1>Edit Question</h1>

    <form action="edit-question" method="post">

        <input
            type="hidden"
            name="id"
            value="<%= question.getId() %>"
        >

        <input
            type="hidden"
            name="quizId"
            value="<%= question.getQuizId() %>"
        >


        <label>
            Question
        </label>

        <input
            type="text"
            name="questionText"
            value="<%= question.getQuestionText() %>"
            required
        >


        <label>
            Option A
        </label>

        <input
            type="text"
            name="optionA"
            value="<%= question.getOptionA() %>"
            required
        >


        <label>
            Option B
        </label>

        <input
            type="text"
            name="optionB"
            value="<%= question.getOptionB() %>"
            required
        >


        <label>
            Option C
        </label>

        <input
            type="text"
            name="optionC"
            value="<%= question.getOptionC() %>"
            required
        >


        <label>
            Option D
        </label>

        <input
            type="text"
            name="optionD"
            value="<%= question.getOptionD() %>"
            required
        >


        <label>
            Correct Answer
        </label>

        <select
            name="correctAnswer"
            required
        >

            <option
                value="A"
                <%= "A".equals(question.getCorrectAnswer())
                    ? "selected"
                    : "" %>
            >
                A
            </option>

            <option
                value="B"
                <%= "B".equals(question.getCorrectAnswer())
                    ? "selected"
                    : "" %>
            >
                B
            </option>

            <option
                value="C"
                <%= "C".equals(question.getCorrectAnswer())
                    ? "selected"
                    : "" %>
            >
                C
            </option>

            <option
                value="D"
                <%= "D".equals(question.getCorrectAnswer())
                    ? "selected"
                    : "" %>
            >
                D
            </option>

        </select>


        <button type="submit">
            Update Question
        </button>

    </form>


    <p>

        <a href="questions?quizId=<%= question.getQuizId() %>">
            Back to Questions
        </a>

    </p>

</div>

</body>

</html>