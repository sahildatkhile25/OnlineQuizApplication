<!DOCTYPE html>

<html>

<head>

    <title>Answer - Online Quiz</title>

    <link rel="stylesheet" href="css/style.css">

</head>

<body>

<div class="container">

    <h1>Answer Result</h1>

    <h2>
        <%= request.getAttribute("feedback") %>
    </h2>

    <form action="quiz" method="get">

        <button type="submit">
            Next Question
        </button>

    </form>

</div>

</body>

</html>