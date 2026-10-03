    <%@ page import="java.util.List" %>

<!DOCTYPE html>

<html>

<head>

    <title>Quiz Attempts</title>

    <link rel="stylesheet"
          href="../css/style.css">

</head>

<body>

<div class="dashboard-container">

    <div class="top-bar">

        <h1>Quiz Attempts</h1>

        <div>

            <a href="dashboard">
                Admin Dashboard
            </a>

            <a href="../logout">
                Logout
            </a>

        </div>

    </div>


    <div class="welcome">

        <h2>
            Manage Quiz Attempts
        </h2>

        <p>
            Allow users to retry a quiz after their first attempt.
        </p>

    </div>


    <%
        List<String[]> attempts =
                (List<String[]>)
                request.getAttribute("attempts");

        if (attempts != null &&
            !attempts.isEmpty()) {
    %>


    <table class="history-table">

        <tr>

            <th>User</th>

            <th>Quiz</th>

            <th>Score</th>

            <th>Status</th>

            <th>Action</th>

        </tr>


        <%

            for (String[] attempt : attempts) {

        %>


        <tr>

            <td>
                <%= attempt[2] %>
            </td>

            <td>
                <%= attempt[3] %>
            </td>

            <td>
                <%= attempt[4] %>
                /
                <%= attempt[5] %>
            </td>


            <td>

                <%
                    if ("true".equals(attempt[6])) {
                %>

                    Retry Allowed

                <%
                    } else {
                %>

                    Attempted

                <%
                    }
                %>

            </td>


            <td>

                <%
                    if (!"true".equals(attempt[6])) {
                %>

                <a
                    class="start-button"
                    href="allow-retry?userId=<%= attempt[0] %>&quizId=<%= attempt[1] %>"
                >
                    Allow Retry
                </a>

                <%
                    } else {
                %>

                    <span class="quiz-given">
                        Retry Allowed
                    </span>

                <%
                    }
                %>

            </td>

        </tr>


        <%

            }

        %>


    </table>


    <%

        } else {

    %>

        <p>
            No quiz attempts found.
        </p>

    <%

        }

    %>

</div>

</body>

</html>