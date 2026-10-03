<%@ page import="java.util.List" %>

<!DOCTYPE html>

<html>

<head>

    <title>Quiz History</title>

    <link rel="stylesheet" href="css/style.css">

</head>

<body>

<div class="dashboard-container">

    <div class="top-bar">

        <h1>Quiz History</h1>

        <a href="quiz-list">Dashboard</a>

    </div>

    <div class="welcome">

        <h2>
            Previous Attempts
        </h2>

    </div>

    <%
        List<String[]> results =
                (List<String[]>)
                request.getAttribute("results");

        if (results != null &&
                !results.isEmpty()) {
    %>

    <table class="history-table">

        <tr>

            <th>Quiz</th>

            <th>Score</th>

            <th>Total</th>

            <th>Date</th>

        </tr>

        <%

            for (String[] result : results) {

        %>

        <tr>

            <td>
                <%= result[0] %>
            </td>

            <td>
                <%= result[1] %>
            </td>

            <td>
                <%= result[2] %>
            </td>

            <td>
                <%= result[3] %>
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
        You have not attempted any quiz yet.
    </p>

    <%

        }

    %>

</div>

</body>

</html>