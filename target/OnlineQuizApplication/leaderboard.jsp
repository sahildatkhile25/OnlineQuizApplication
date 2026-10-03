<%@ page import="java.util.List" %>

<!DOCTYPE html>

<html>

<head>

    <title>Leaderboard - Online Quiz</title>

    <link rel="stylesheet"
          href="css/style.css">

</head>

<body>

<div class="dashboard-container">

    <div class="top-bar">

        <h1>🏆 Leaderboard</h1>

        <div>

            <a href="quiz-list">
                Dashboard
            </a>

            <a href="logout">
                Logout
            </a>

        </div>

    </div>


    <div class="welcome">

        <h2>
            Top 10 Scores
        </h2>

        <p>
            Highest scores achieved by users.
        </p>

    </div>


    <%
        List<String[]> leaderboard =
                (List<String[]>)
                request.getAttribute("leaderboard");

        if (leaderboard != null &&
            !leaderboard.isEmpty()) {
    %>


    <table class="history-table">

        <tr>

            <th>Rank</th>

            <th>Username</th>

            <th>Quiz</th>

            <th>Score</th>

        </tr>


        <%

            int rank = 1;

            for (String[] row : leaderboard) {

        %>


        <tr>

            <td>
                <%= rank %>
            </td>

            <td>
                <%= row[0] %>
            </td>

            <td>
                <%= row[1] %>
            </td>

            <td>
                <%= row[2] %>
                /
                <%= row[3] %>
            </td>

        </tr>


        <%

                rank++;

            }

        %>


    </table>


    <%

        } else {

    %>


    <p>
        No quiz results available yet.
    </p>


    <%

        }

    %>

</div>

</body>

</html>