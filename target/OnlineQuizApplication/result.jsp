<!DOCTYPE html>

<html>

<head>

    <title>Result - Online Quiz</title>

    <link rel="stylesheet"
          href="css/style.css">

</head>

<body>

<div class="container">

    <h1>Quiz Completed!</h1>

    <h2>Your Result</h2>

    <h3>
        Score:
        <%= request.getAttribute("score") %>
        /
        <%= request.getAttribute("totalQuestions") %>
    </h3>


    <a href="quiz-list">

        <button>
            Back to Dashboard
        </button>

    </a>


    <a href="leaderboard">

        <button>
            View Leaderboard
        </button>

    </a>


    <a href="history">

        <button>
            View History
        </button>

    </a>

</div>

</body>

</html>