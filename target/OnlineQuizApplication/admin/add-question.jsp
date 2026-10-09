<!DOCTYPE html>

<html>

<head>

    <title>Add Question</title>

    <link rel="stylesheet"
          href="../css/style.css">

</head>

<body>

<div class="container">

    <h1>Add Question</h1>

    <form action="add-question" method="post">

        <input
            type="hidden"
            name="quizId"
            value="<%= request.getAttribute("quizId") %>"
        >

        <label>
            Question
        </label>

        <input
            type="text"
            name="questionText"
            placeholder="Enter question"
            required
        >


        <label>
            Option A
        </label>

        <input
            type="text"
            name="optionA"
            placeholder="Enter option A"
            required
        >


        <label>
            Option B
        </label>

        <input
            type="text"
            name="optionB"
            placeholder="Enter option B"
            required
        >


        <label>
            Option C
        </label>

        <input
            type="text"
            name="optionC"
            placeholder="Enter option C"
            required
        >


        <label>
            Option D
        </label>

        <input
            type="text"
            name="optionD"
            placeholder="Enter option D"
            required
        >


        <label>
            Correct Answer
        </label>

        <select
            name="correctAnswer"
            required
        >

            <option value="">
                Select correct answer
            </option>

            <option value="A">
                A
            </option>

            <option value="B">
                B
            </option>

            <option value="C">
                C
            </option>

            <option value="D">
                D
            </option>

        </select>

        <label>
    Difficulty
</label>

<select
    name="difficulty"
    required
>

    <option value="">
        Select difficulty
    </option>

    <option value="Easy">
        Easy
    </option>

    <option value="Medium">
        Medium
    </option>

    <option value="Hard">
        Hard
    </option>

</select>


        <button type="submit">
            Add Question
        </button>

    </form>


    <p>

        <a href="questions?quizId=<%= request.getAttribute("quizId") %>">
            Back to Questions
        </a>

    </p>

</div>

</body>

</html>