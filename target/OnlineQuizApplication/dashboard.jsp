<%@ page import="java.util.List" %>
<%@ page import="com.quiz.model.Quiz" %>
<%@ page import="com.quiz.dao.ResultDAO" %>

<!DOCTYPE html>

<html>

<head>

    <title>Dashboard - Online Quiz</title>

    <link rel="stylesheet"
          href="css/style.css">

</head>

<body>


<div class="dashboard-container">


    <!-- =========================
         TOP NAVIGATION
    ========================== -->

    <div class="top-bar">

        <h1>
            Online Quiz
        </h1>


        <div>

            <a href="leaderboard">
                Leaderboard
            </a>

            <a href="history">
                History
            </a>

            <a href="logout">
                Logout
            </a>

        </div>

    </div>



    <!-- =========================
         WELCOME SECTION
    ========================== -->

    <div class="welcome">

        <h2>

            Welcome,
            <%= session.getAttribute("username") %>

        </h2>

        <p>

            Test your knowledge by selecting
            a quiz below.

        </p>

    </div>



    <!-- =========================
         ERROR MESSAGES
    ========================== -->

    <%

        String error =
                request.getParameter("error");

        if ("already-given".equals(error)) {

    %>

        <div class="message-error">

            ✓ You have already given this quiz.
            Please wait for the admin to allow a retry.

        </div>

    <%

        } else if ("no-questions".equals(error)) {

    %>

        <div class="message-error">

            This quiz does not have any questions yet.

        </div>

    <%

        }

    %>



    <!-- =========================
         AVAILABLE QUIZZES
    ========================== -->

    <h2>
        Available Quizzes
    </h2>


    <div class="quiz-grid">


        <%

            List<Quiz> quizzes =
                    (List<Quiz>)
                    request.getAttribute("quizzes");


            if (quizzes != null &&
                !quizzes.isEmpty()) {


                /*
                 * Get logged-in user's ID.
                 */
                int userId =
                        (Integer)
                        session.getAttribute("userId");


                /*
                 * ResultDAO is used to check
                 * whether the user has already
                 * attempted each quiz.
                 */
                ResultDAO resultDAO =
                        new ResultDAO();


                for (Quiz quiz : quizzes) {


                    /*
                     * Check whether this user
                     * has already attempted
                     * this quiz.
                     */
                    boolean attempted =
                            resultDAO.hasAttemptedQuiz(
                                    userId,
                                    quiz.getId()
                            );


                    /*
                     * Default:
                     * retry is not allowed.
                     */
                    boolean canRetry = false;


                    /*
                     * If user has already
                     * attempted the quiz,
                     * check whether admin
                     * has allowed retry.
                     */
                    if (attempted) {

                        canRetry =
                                resultDAO.canRetryQuiz(
                                        userId,
                                        quiz.getId()
                                );
                    }

        %>


        <!-- =========================
             QUIZ CARD
        ========================== -->

        <div class="quiz-card">


            <h3>

                <%= quiz.getTitle() %>

            </h3>


            <p>

                <strong>
                    Topic:
                </strong>

                <%= quiz.getTopic() %>

            </p>



            <!-- =========================
                 QUIZ STATUS / BUTTON
            ========================== -->

            <%

                /*
                 * CASE 1:
                 * User has never attempted
                 * this quiz.
                 */
                if (!attempted) {

            %>


                <a
                    class="start-button"
                    href="start-quiz?id=<%= quiz.getId() %>"
                >

                    Start Quiz

                </a>


            <%

                /*
                 * CASE 2:
                 * User attempted the quiz
                 * and admin allowed retry.
                 */
                } else if (canRetry) {

            %>


                <a
                    class="start-button retry-button"
                    href="start-quiz?id=<%= quiz.getId() %>"
                >

                    Retry Quiz

                </a>


            <%

                /*
                 * CASE 3:
                 * User attempted the quiz
                 * and retry is not allowed.
                 */
                } else {

            %>


                <span class="quiz-given">

                    ✓ Quiz Already Given

                </span>


            <%

                }

            %>


        </div>


        <%

                }

            } else {

        %>


        <!-- No quizzes available -->

        <div class="quiz-card">

            <h3>
                No Quizzes Available
            </h3>

            <p>
                The admin has not added any quizzes yet.
            </p>

        </div>


        <%

            }

        %>


    </div>


</div>


</body>

</html>