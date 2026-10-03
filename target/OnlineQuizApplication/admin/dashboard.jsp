<%@ page import="java.util.List" %>
<%@ page import="com.quiz.model.Quiz" %>

<!DOCTYPE html>

<html>

<head>

    <title>Admin Dashboard</title>

    <link rel="stylesheet"
          href="../css/style.css">

</head>

<body>

<div class="dashboard-container">

    <div class="top-bar">

        <h1>Admin Dashboard</h1>

        <div>
            
            <a href="attempts">
                Quiz Attempts
            </a>

            <a href="../quiz-list">
                User Dashboard
            </a>

            <a href="../logout">
                Logout
            </a>

        </div>

    </div>

    <div class="welcome">

        <h2>
            Quiz Management
        </h2>

        <p>
            Create and manage quizzes.
        </p>

        <a class="start-button"
           href="add-quiz">
            Add New Quiz
        </a>

    </div>

    <h2>Existing Quizzes</h2>

    <div class="quiz-grid">

        <%

            List<Quiz> quizzes =
                (List<Quiz>)
                request.getAttribute("quizzes");

            if (quizzes != null &&
                !quizzes.isEmpty()) {

                for (Quiz quiz : quizzes) {

        %>

        <div class="quiz-card">

            <h3>
                <%= quiz.getTitle() %>
            </h3>

            <p>
                Topic:
                <%= quiz.getTopic() %>
            </p>

           <a class="start-button"
   href="questions?quizId=<%= quiz.getId() %>">

    Manage Questions

</a>

<a class="start-button"
   href="delete-quiz?id=<%= quiz.getId() %>">

    Delete

</a>

        </div>

        <%

                }

            } else {

        %>

        <p>No quizzes found.</p>

        <%

            }

        %>

    </div>

</div>

</body>

</html>