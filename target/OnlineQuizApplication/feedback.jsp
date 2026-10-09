<!DOCTYPE html>

<%
    String feedback =
            (String) request.getAttribute("feedback");

    String feedbackType =
            (String) request.getAttribute("feedbackType");

    Boolean quizFinished =
            (Boolean) request.getAttribute("quizFinished");

    boolean finished =
            Boolean.TRUE.equals(quizFinished);

    String feedbackClass =
            "correct".equals(feedbackType)
                    ? "feedback-correct"
                    : "feedback-incorrect";

    String formAction =
            finished ? "result" : "quiz";

    String buttonText =
            finished ? "View Result" : "Next Question";
%>

<html>

<head>

    <title>Answer - Online Quiz</title>

    <link rel="stylesheet" href="css/style.css">

</head>

<body>
<div class="container">

    <h1>Answer Result</h1>

    <h2 class="<%= feedbackClass %>">
        <%= feedback %>
    </h2>

    <form action="<%= formAction %>"
          method="get">

        <button type="submit">
            <%= buttonText %>
        </button>

    </form>

</div>
</body>

</html>
