# Online Quiz Application

## Project Overview

**Online Quiz Application** is a Java-based web application for conducting multiple-choice quizzes online.

Users can register, log in, select quizzes, answer questions one by one, receive immediate feedback, view their score and history, and check the leaderboard.

Administrators can create and delete quizzes, manage questions, view quiz attempts, and allow users to retry completed quizzes.

## Technologies Used

- Java 17
- Java Servlets
- JSP
- MySQL
- JDBC
- HTML5
- CSS3
- JavaScript
- Maven
- Apache Tomcat 10
- VS Code

## Main Features

### User Features

- User registration and login
- Password hashing using PBKDF2WithHmacSHA256
- View available quizzes
- One question at a time
- Immediate correct/incorrect feedback
- Automatic score calculation
- Quiz result and history
- Leaderboard
- Quiz marked as **Already Given** after completion
- Retry available only after admin permission
- Retried quiz updates the existing result instead of creating a duplicate leaderboard entry

### Admin Features

- Admin login
- Create and delete quizzes
- Add, edit and delete questions
- View user quiz attempts
- Allow a user to retry a completed quiz

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

## Database

Database name:

online_quiz

Main tables:

users
quizzes
questions
results

The complete database setup and sample questions are provided in:

OnlineQuizApplication\database.sql


## Project Structure

OnlineQuizApplication/
├── pom.xml
├── database.sql
├── README.md
├── src/
│   └── main/
│       ├── java/
│       │   └── com/quiz/
│       │       ├── DBConnection.java
│       │       ├── PasswordUtil.java
│       │       ├── TestConnection.java
│       │       ├── dao/
│       │       │   ├── QuestionDAO.java
│       │       │   ├── QuizDAO.java
│       │       │   ├── ResultDAO.java
│       │       │   └── UserDAO.java
│       │       ├── model/
│       │       │   ├── Question.java
│       │       │   ├── Quiz.java
│       │       │   └── User.java
│       │       └── servlet/
│       │           ├── AddQuestionServlet.java
│       │           ├── AddQuizServlet.java
│       │           ├── AdminAttemptsServlet.java
│       │           ├── AdminDashboardServlet.java
│       │           ├── AdminFilter.java
│       │           ├── AllowRetryServlet.java
│       │           ├── AnswerServlet.java
│       │           ├── DeleteQuestionServlet.java
│       │           ├── DeleteQuizServlet.java
│       │           ├── EditQuestionServlet.java
│       │           ├── FeedbackServlet.java
│       │           ├── HistoryServlet.java
│       │           ├── LeaderboardServlet.java
│       │           ├── LoginServlet.java
│       │           ├── LogoutServlet.java
│       │           ├── QuestionManagementServlet.java
│       │           ├── QuizListServlet.java
│       │           ├── QuizServlet.java
│       │           ├── RegisterServlet.java
│       │           ├── ResultServlet.java
│       │           └── StartQuizServlet.java
│       └── webapp/
│           ├── index.html
│           ├── login.html
│           ├── register.html
│           ├── dashboard.jsp
│           ├── quiz.jsp
│           ├── result.jsp
│           ├── history.jsp
│           ├── leaderboard.jsp
│           ├── feedback.jsp
│           ├── run.txt
│           ├── css/
│           │   └── style.css
│           ├── admin/
│           │   ├── add-question.jsp
│           │   ├── add-quiz.jsp
│           │   ├── attempts.jsp
│           │   ├── dashboard.jsp
│           │   ├── edit-question.jsp
│           │   └── questions.jsp
│           └── WEB-INF/
│               └── web.xml
└── target/                         (Maven-generated build output)
    ├── OnlineQuizApplication.war
    ├── classes/
    ├── maven-archiver/
    ├── maven-status/
    └── OnlineQuizApplication/      (expanded WAR)

# Running Procedure

## 1. Install Requirements

Install:

- JDK 17
- Maven
- MySQL Server and MySQL Workbench
- Apache Tomcat 10
- VS Code

Check Java:

```powershell
java -version

Check Maven:

```powershell
mvn -version

## 2. Open the Project

Open the project in VS Code. The project should contain:

pom.xml
database.sql

## 3. Create the Database

Open **MySQL Workbench**.

Open:

database.sql

Run the complete SQL file.

It creates the `online_quiz` database, tables, quizzes and questions.

Verify the tables:

```sql
USE online_quiz;
SHOW TABLES;

Check the number of questions:

```sql
SELECT COUNT(*) AS total_questions
FROM questions;

Expected result:

100

## 4. Configure MySQL Password

Open:

src/main/java/com/quiz/DBConnection.java

Set your local MySQL password:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/online_quiz";

private static final String USER = "root";

private static final String PASSWORD =
        "YOUR_MYSQL_PASSWORD";


Replace `YOUR_MYSQL_PASSWORD` with your local MySQL password.

## 5. Build the Project

Open the terminal in the project folder and run:

```powershell
mvn clean package

You should see:

BUILD SUCCESS

The WAR file will be created at:

target\OnlineQuizApplication.war

## 6. Configure Java for Tomcat

If Tomcat requires `JAVA_HOME`, set it in PowerShell:

```powershell
$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot" 
( if it falls check the exact jdk path and replace it)

Check:

```powershell
java -version

## 7. Deploy to Tomcat

Copy:
target\OnlineQuizApplication.war

to the Tomcat `webapps` folder, for example:

C:\Users\user\OneDrive\Desktop\apache-tomcat-10.1.52\webapps\

## 8. Start Tomcat

Open PowerShell:

```powershell
cd C:\Users\sahil\OneDrive\Desktop\apache-tomcat-10.1.52\bin

Start Tomcat:

```powershell
.\startup.bat

## 9. Run the Application

Open your browser:

http://localhost:8080/OnlineQuizApplication/

## 10. Create an Admin

Register a user through the application first.

Then open MySQL Workbench and run:

```sql
USE online_quiz;

UPDATE users
SET role = 'ADMIN'
WHERE username = 'admin'; (username can be any)

SELECT id, username, role
FROM users
WHERE username = 'admin';

The role should show:

ADMIN

Log in again to open the Admin Dashboard.

## User Workflow

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

## Admin Workflow

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

## Retry System

After a user completes a quiz, it is shown as:

"✓ Quiz Already Given"

The user cannot immediately attempt the same quiz again.

The admin can open:
Admin Dashboard → Quiz Attempts → Allow Retry

The user will then see:
"Retry Quiz"

After retrying, the existing result is updated instead of creating another leaderboard entry.

## Security

The project uses:

- PBKDF2WithHmacSHA256 password hashing with a random salt
- Prepared statements for database operations
- HTTP sessions for login state
- Admin role checking using `AdminFilter`

## Common Problems

### Database connection error

Check that:

- MySQL Server is running
- Database name is `online_quiz`
- Username and password are correct
- MySQL is using port `3306`

### 404 error

Make sure `OnlineQuizApplication.war` is inside Tomcat's `webapps` folder and Tomcat is running.

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

and check the first error shown in the terminal.

## Project Objective

The objective of this project is to develop a simple online quiz platform while demonstrating Java web development, JDBC, MySQL, authentication, session management, CRUD operations and role-based access control.

## Author

**Sahil Datkhile**  
B.Sc. Information Technology  
**Project:** Online Quiz Application

## License

This project is developed for educational and academic purposes.
