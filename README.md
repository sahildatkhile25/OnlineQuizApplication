# Online Quiz Application

## Project Overview

**Online Quiz Application** is a Java-based web application for conducting multiple-choice quizzes online.

Users can register, log in, select quizzes, answer questions one by one, receive immediate feedback, view their final score, check quiz history, and view the leaderboard.

Administrators can create and delete quizzes, manage questions, view quiz attempts, and allow users to retry completed quizzes.

## Technologies Used

- Java 17
- Jakarta Servlet API 6.0.0
- JSP
- MySQL
- MySQL Connector/J 9.4.0
- JDBC
- HTML5
- CSS3
- JavaScript
- Maven
- Maven Compiler Plugin 3.13.0
- Maven WAR Plugin 3.4.0
- Apache Tomcat 10
- VS Code

## Main Features

### User Features

- User registration and login
- Password hashing using `PBKDF2WithHmacSHA256`
- View available quizzes
- Start a selected quiz
- Random question order for every quiz attempt
- One question shown at a time
- Question difficulty shown on the quiz page
- Timed quiz based on total number of questions
- Timer pauses during feedback
- Timer resumes on the next question
- Timer redirects to timeout when it reaches `0`
- Immediate correct/incorrect feedback
- Automatic score calculation
- Final quiz result
- Quiz history
- Leaderboard
- Quiz marked as **Already Given** after completion
- Retry available only after admin permission
- Retried quiz updates the existing result instead of creating a duplicate leaderboard entry

### Admin Features

- Admin login
- Admin-only dashboard
- Create and delete quizzes
- Add, edit, and delete questions
- Add and edit question difficulty
- View user quiz attempts
- Allow a user to retry a completed quiz

## Quiz Timer Flow

Each quiz gets `30 seconds x number of questions`.

Example:

```text
10 questions x 30 seconds = 300 seconds
```

Timer flow:

```text
Start Quiz
   ↓
Load random questions
   ↓
Start timer
   ↓
Display question
   ↓
Submit answer
   ↓
Pause timer
   ↓
Show feedback
   ↓
Next Question
   ↓
Resume timer
   ↓
Timer reaches 0
   ↓
Redirect to timeout/result
```

Important timer files:

- `StartQuizServlet.java` starts the quiz timer.
- `QuizServlet.java` displays questions and resumes the timer after feedback.
- `AnswerServlet.java` checks the timer before accepting an answer and pauses it for feedback.
- `FeedbackServlet.java` shows feedback while the timer is paused.
- `TimeoutServlet.java` handles expired quizzes.
- `quiz.jsp` displays the countdown timer in the browser.

## Quiz Data

The project database contains 10 topics with 10 questions each:

1. Java Programming
2. Python Programming
3. SQL Basics
4. HTML Basics
5. CSS Basics
6. JavaScript Basics
7. C Programming
8. C++ Programming
9. DBMS Basics
10. Computer Networks

**Total: 10 quizzes and 100 questions.**

Each question contains:

- Question text
- Four options
- Correct answer
- Difficulty level

Questions are fetched randomly using `ORDER BY RAND()` in `QuestionDAO.java`, so every quiz attempt can show questions in a different order.

## Database

Database name:

```text
online_quiz
```

Main tables:

```text
users
quizzes
questions
results
```

The complete database setup and sample questions are provided in:

```text
OnlineQuizApplication/database.sql
```

The database script creates:

- `online_quiz` database
- `users` table
- `quizzes` table
- `questions` table
- `results` table
- 10 quiz topics
- 100 sample questions
- Question difficulty values
- Retry tracking using `can_retry`

Important:

`database.sql` is intended for a fresh project database. Do not run it on a database that already contains important data.

## Project Structure

```text
OnlineQuizApplication/
|-- pom.xml
|-- database.sql
|-- README.md
|-- src/
|   `-- main/
|       |-- java/
|       |   `-- com/quiz/
|       |       |-- DBConnection.java
|       |       |-- PasswordUtil.java
|       |       |-- TestConnection.java
|       |       |-- dao/
|       |       |   |-- QuestionDAO.java
|       |       |   |-- QuizDAO.java
|       |       |   |-- ResultDAO.java
|       |       |   `-- UserDAO.java
|       |       |-- model/
|       |       |   |-- Question.java
|       |       |   |-- Quiz.java
|       |       |   `-- User.java
|       |       `-- servlet/
|       |           |-- AddQuestionServlet.java
|       |           |-- AddQuizServlet.java
|       |           |-- AdminAttemptsServlet.java
|       |           |-- AdminDashboardServlet.java
|       |           |-- AdminFilter.java
|       |           |-- AllowRetryServlet.java
|       |           |-- AnswerServlet.java
|       |           |-- DeleteQuestionServlet.java
|       |           |-- DeleteQuizServlet.java
|       |           |-- EditQuestionServlet.java
|       |           |-- FeedbackServlet.java
|       |           |-- HistoryServlet.java
|       |           |-- LeaderboardServlet.java
|       |           |-- LoginServlet.java
|       |           |-- LogoutServlet.java
|       |           |-- QuestionManagementServlet.java
|       |           |-- QuizListServlet.java
|       |           |-- QuizServlet.java
|       |           |-- RegisterServlet.java
|       |           |-- ResultServlet.java
|       |           |-- StartQuizServlet.java
|       |           `-- TimeoutServlet.java
|       `-- webapp/
|           |-- index.html
|           |-- login.html
|           |-- register.html
|           |-- dashboard.jsp
|           |-- quiz.jsp
|           |-- feedback.jsp
|           |-- result.jsp
|           |-- history.jsp
|           |-- leaderboard.jsp
|           |-- run.txt
|           |-- css/
|           |   `-- style.css
|           |-- admin/
|           |   |-- add-question.jsp
|           |   |-- add-quiz.jsp
|           |   |-- attempts.jsp
|           |   |-- dashboard.jsp
|           |   |-- edit-question.jsp
|           |   `-- questions.jsp
|           `-- WEB-INF/
|               `-- web.xml
`-- target/
    |-- OnlineQuizApplication.war
    |-- classes/
    |-- maven-archiver/
    |-- maven-status/
    `-- OnlineQuizApplication/
```

Note:

`target/` is generated by Maven. It can be deleted and recreated by running `mvn clean package`.

## Running Procedure

### 1. Install Requirements

Install:

- JDK 17
- Maven
- MySQL Server
- MySQL Workbench
- Apache Tomcat 10
- VS Code

Check Java:

```powershell
java -version
```

Check Maven:

```powershell
mvn -version
```

### 2. Open the Project

Open the project folder in VS Code.

The project should contain:

```text
pom.xml
database.sql
src/
```

### 3. Create the Database

Open **MySQL Workbench**.

Open:

```text
database.sql
```

Run the complete SQL file.

It creates the `online_quiz` database, tables, quizzes, questions, and sample data.

Verify the tables:

```sql
USE online_quiz;
SHOW TABLES;
```

Check the number of questions:

```sql
SELECT COUNT(*) AS total_questions
FROM questions;
```

Expected result:

```text
100
```

### 4. Configure MySQL Password

Open:

```text
src/main/java/com/quiz/DBConnection.java
```

Set your local MySQL username and password:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/online_quiz";

private static final String USER = "root";

private static final String PASSWORD =
        "YOUR_MYSQL_PASSWORD";
```

Replace `YOUR_MYSQL_PASSWORD` with your local MySQL password.

### 5. Build the Project

Open the terminal in the project folder and run:

```powershell
mvn clean package
```

You should see:

```text
BUILD SUCCESS
```

The WAR file will be created at:

```text
target/OnlineQuizApplication.war
```

### 6. Configure Java for Tomcat

If Tomcat requires `JAVA_HOME`, set it in PowerShell:

```powershell
$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot"
```

If it fails, check the exact JDK path on your computer and replace the path.

Check Java again:

```powershell
java -version
```

### 7. Deploy to Tomcat

Copy:

```text
target/OnlineQuizApplication.war
```

to the Tomcat `webapps` folder, for example:

```text
C:\Users\sahil\OneDrive\Desktop\apache-tomcat-10.1.52\webapps\
```

### 8. Start Tomcat

Open PowerShell and go to the Tomcat `bin` folder:

```powershell
cd C:\Users\sahil\OneDrive\Desktop\apache-tomcat-10.1.52\bin
```

Start Tomcat:

```powershell
.\startup.bat
```

### 9. Run the Application

Open your browser:

```text
http://localhost:8080/OnlineQuizApplication/
```

### 10. Create an Admin

Register a normal user through the application first.

Then open MySQL Workbench and run:

```sql
USE online_quiz;

UPDATE users
SET role = 'ADMIN'
WHERE username = 'admin';

SELECT id, username, role
FROM users
WHERE username = 'admin';
```

Replace `admin` with the username you registered.

The role should show:

```text
ADMIN
```

Log in again to open the Admin Dashboard.

## User Workflow

```text
Register
   ↓
Login
   ↓
Quiz Dashboard
   ↓
Select Quiz
   ↓
Answer Questions
   ↓
Immediate Feedback
   ↓
Final Score
   ↓
History / Leaderboard
```

## Admin Workflow

```text
Admin Login
   ↓
Admin Dashboard
   ↓
Manage Quizzes
   ↓
Manage Questions
   ↓
View Quiz Attempts
   ↓
Allow Retry
```

## Retry System

After a user completes a quiz, it is shown as:

```text
✓ Quiz Already Given
```

The user cannot immediately attempt the same quiz again.

The admin can open:

```text
Admin Dashboard -> Quiz Attempts -> Allow Retry
```

The user will then see:

```text
Retry Quiz
```

After retrying, the existing result is updated instead of creating another leaderboard entry.

## Security

The project uses:

- `PBKDF2WithHmacSHA256` password hashing with a random salt
- Prepared statements for database operations
- HTTP sessions for login state
- Admin role checking using `AdminFilter`
- Server-side timer validation before accepting answers

## Common Problems

### Database connection error

Check that:

- MySQL Server is running
- Database name is `online_quiz`
- Username and password are correct in `DBConnection.java`
- MySQL is using port `3306`

### 404 error

Check that:

- `OnlineQuizApplication.war` is inside Tomcat's `webapps` folder
- Tomcat is running
- The browser URL is correct:

```text
http://localhost:8080/OnlineQuizApplication/
```

### JAVA_HOME error

Set:

```powershell
$env:JAVA_HOME="YOUR_JDK_17_PATH"
```

Then start Tomcat again.

### Maven build error

Run:

```powershell
mvn clean package
```

Then check the first error shown in the terminal.

### Timer reaches 0

When the timer reaches `0`, the quiz is no longer available and the application redirects to the timeout/result flow.

This is expected behavior.

## Project Objective

The objective of this project is to develop a simple online quiz platform while demonstrating Java web development, JDBC, MySQL, authentication, session management, CRUD operations, role-based access control, quiz timers, and basic result tracking.

## Author

**Sahil Datkhile**  
B.Sc. Information Technology  
**Project:** Online Quiz Application

## License

This project is developed for educational and academic purposes.
