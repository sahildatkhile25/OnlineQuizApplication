<%@ page import="java.util.List" %>
<%@ page import="com.quiz.model.Question" %>
<%@ page import="com.quiz.model.Quiz" %>

<!DOCTYPE html>

<html>

<head>

    <title>Manage Questions</title>

    <link rel="stylesheet"
          href="../css/style.css">

</head>

<body>

<div class="dashboard-container">

    <div class="top-bar">

        <h1>Manage Questions</h1>

        <div>

            <a href="dashboard">
                Admin Dashboard
            </a>

            <a href="../logout">
                Logout
            </a>

        </div>

    </div>


    <%
        Quiz quiz =
                (Quiz) request.getAttribute("quiz");
    %>


    <div class="welcome">

        <h2>
            <%= quiz.getTitle() %>
        </h2>

        <p>
            Topic:
            <%= quiz.getTopic() %>
        </p>

        <a
            class="start-button"
            href="add-question?quizId=<%= quiz.getId() %>"
        >
            Add New Question
        </a>

    </div>


    <h2>Questions</h2>


    <%
        List<Question> questions =
                (List<Question>)
                request.getAttribute("questions");

        if (questions != null &&
            !questions.isEmpty()) {

            for (Question question : questions) {
    %>


    <div class="quiz-card">

        <h3>
            <%= question.getQuestionText() %>
        </h3>

        <p>
            A. <%= question.getOptionA() %>
        </p>

        <p>
            B. <%= question.getOptionB() %>
        </p>

        <p>
            C. <%= question.getOptionC() %>
        </p>

        <p>
            D. <%= question.getOptionD() %>
        </p>

        <p>
            <strong>
                Correct Answer:
                <%= question.getCorrectAnswer() %>
            </strong>
        </p>


        <a
            class="start-button"
            href="edit-question?id=<%= question.getId() %>"
        >
            Edit
        </a>


        <a
            class="start-button"
            href="delete-question?id=<%= question.getId() %>&quizId=<%= quiz.getId() %>"
        >
            Delete
        </a>

    </div>


    <%

            }

        } else {

    %>


    <p>
        No questions added yet.
    </p>


    <%

        }

    %>

</div>

</body>

</html>