<!DOCTYPE html>

<html>

<head>

    <title>Add Quiz</title>

    <link rel="stylesheet"
          href="../css/style.css">

</head>

<body>

<div class="container">

    <h1>Add New Quiz</h1>

    <form action="add-quiz" method="post">

        <label>
            Quiz Title
        </label>

        <input
            type="text"
            name="title"
            placeholder="Example: Java Advanced"
            required
        >

        <label>
            Topic
        </label>

        <input
            type="text"
            name="topic"
            placeholder="Example: Java"
            required
        >

        <button type="submit">
            Create Quiz
        </button>

    </form>

    <p>
        <a href="dashboard">
            Back to Admin Dashboard
        </a>
    </p>

</div>

</body>

</html>