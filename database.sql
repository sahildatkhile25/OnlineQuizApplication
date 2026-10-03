-- ============================================================
-- Online Quiz Application - Database Setup
-- Database: online_quiz
-- Java 17 + Servlet + MySQL
--
-- This file creates:
--   1. Database
--   2. Users table
--   3. Quizzes table
--   4. Questions table
--   5. Results table
--   6. 10 quizzes/topics
--   7. 100 questions (10 per quiz)
--
-- IMPORTANT:
-- This script is intended for a fresh project database.
-- Do not run it on a database containing important data.
-- ============================================================


-- ============================================================
-- 1. CREATE DATABASE
-- ============================================================

CREATE DATABASE IF NOT EXISTS online_quiz;

USE online_quiz;


-- ============================================================
-- 2. USERS TABLE
-- ============================================================

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER'
);


-- ============================================================
-- 3. QUIZZES TABLE
-- ============================================================

CREATE TABLE IF NOT EXISTS quizzes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    topic VARCHAR(100) NOT NULL
);


-- ============================================================
-- 4. QUESTIONS TABLE
-- ============================================================

CREATE TABLE IF NOT EXISTS questions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quiz_id INT NOT NULL,
    question_text VARCHAR(255) NOT NULL,
    option_a VARCHAR(100) NOT NULL,
    option_b VARCHAR(100) NOT NULL,
    option_c VARCHAR(100) NOT NULL,
    option_d VARCHAR(100) NOT NULL,
    correct_answer CHAR(1) NOT NULL,

    FOREIGN KEY (quiz_id)
        REFERENCES quizzes(id)
        ON DELETE CASCADE
);


-- ============================================================
-- 5. RESULTS TABLE
-- ============================================================
-- can_retry:
-- FALSE = user has completed the quiz and cannot retry
-- TRUE  = admin has allowed the user to retry
--
-- UNIQUE(user_id, quiz_id):
-- One user has only one result row for each quiz.
-- A retry updates the existing score instead of creating
-- another leaderboard row.
-- ============================================================

CREATE TABLE IF NOT EXISTS results (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    quiz_id INT NOT NULL,
    score INT NOT NULL,
    total_questions INT NOT NULL,
    attempt_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    can_retry BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    FOREIGN KEY (quiz_id)
        REFERENCES quizzes(id)
        ON DELETE CASCADE,

    UNIQUE (user_id, quiz_id)
);


-- ============================================================
-- 6. INSERT 10 QUIZZES / TOPICS
-- ============================================================

INSERT INTO quizzes (title, topic)
VALUES
('Java Programming', 'Java'),
('Python Programming', 'Python'),
('SQL Basics', 'SQL'),
('HTML Basics', 'HTML'),
('CSS Basics', 'CSS'),
('JavaScript Basics', 'JavaScript'),
('C Programming', 'C'),
('C++ Programming', 'C++'),
('DBMS Basics', 'DBMS'),
('Computer Networks', 'Computer Networks');


-- ============================================================
-- 7. JAVA PROGRAMMING - 10 QUESTIONS
-- ============================================================

INSERT INTO questions
(quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES
((SELECT id FROM quizzes WHERE title='Java Programming'),
 'Which keyword is used to create a class in Java?',
 'class', 'Class', 'new', 'object', 'A'),

((SELECT id FROM quizzes WHERE title='Java Programming'),
 'Which method is the starting point of a Java program?',
 'start()', 'main()', 'run()', 'execute()', 'B'),

((SELECT id FROM quizzes WHERE title='Java Programming'),
 'Which data type is used to store whole numbers?',
 'float', 'double', 'int', 'char', 'C'),

((SELECT id FROM quizzes WHERE title='Java Programming'),
 'Which keyword is used to inherit a class?',
 'implements', 'extends', 'inherits', 'super', 'B'),

((SELECT id FROM quizzes WHERE title='Java Programming'),
 'Which symbol is used to end a Java statement?',
 '.', ':', ';', ',', 'C'),

((SELECT id FROM quizzes WHERE title='Java Programming'),
 'Which concept allows the same method name with different parameters?',
 'Inheritance', 'Encapsulation', 'Polymorphism', 'Abstraction', 'C'),

((SELECT id FROM quizzes WHERE title='Java Programming'),
 'Which keyword is used to create an object?',
 'class', 'object', 'new', 'create', 'C'),

((SELECT id FROM quizzes WHERE title='Java Programming'),
 'Which keyword is used to prevent inheritance?',
 'static', 'final', 'private', 'const', 'B'),

((SELECT id FROM quizzes WHERE title='Java Programming'),
 'Which package contains the Scanner class?',
 'java.io', 'java.util', 'java.lang', 'java.sql', 'B'),

((SELECT id FROM quizzes WHERE title='Java Programming'),
 'Which operator is used for logical AND?',
 '&', '&&', '||', '!', 'B');


-- ============================================================
-- 8. PYTHON PROGRAMMING - 10 QUESTIONS
-- ============================================================

INSERT INTO questions
(quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES
((SELECT id FROM quizzes WHERE title='Python Programming'),
 'Which function is used to display output in Python?',
 'display()', 'echo()', 'print()', 'show()', 'C'),

((SELECT id FROM quizzes WHERE title='Python Programming'),
 'Which symbol is used to write a comment in Python?',
 '//', '/*', '#', '--', 'C'),

((SELECT id FROM quizzes WHERE title='Python Programming'),
 'Which data type stores multiple values in an ordered collection?',
 'list', 'int', 'bool', 'float', 'A'),

((SELECT id FROM quizzes WHERE title='Python Programming'),
 'Which keyword is used to define a function?',
 'function', 'def', 'fun', 'define', 'B'),

((SELECT id FROM quizzes WHERE title='Python Programming'),
 'Which function returns the length of a list?',
 'count()', 'length()', 'size()', 'len()', 'D'),

((SELECT id FROM quizzes WHERE title='Python Programming'),
 'Which data type stores key-value pairs?',
 'list', 'tuple', 'dictionary', 'set', 'C'),

((SELECT id FROM quizzes WHERE title='Python Programming'),
 'Which keyword is used for a conditional statement?',
 'if', 'when', 'check', 'condition', 'A'),

((SELECT id FROM quizzes WHERE title='Python Programming'),
 'Which extension is commonly used for Python files?',
 '.java', '.py', '.python', '.pt', 'B'),

((SELECT id FROM quizzes WHERE title='Python Programming'),
 'Which keyword is used to create a loop over a sequence?',
 'loop', 'repeat', 'for', 'iterate', 'C'),

((SELECT id FROM quizzes WHERE title='Python Programming'),
 'Which value represents a Boolean true value in Python?',
 'true', 'TRUE', 'True', '1', 'C');


-- ============================================================
-- 9. SQL BASICS - 10 QUESTIONS
-- ============================================================

INSERT INTO questions
(quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES
((SELECT id FROM quizzes WHERE title='SQL Basics'),
 'Which command is used to retrieve data from a table?',
 'GET', 'SELECT', 'FETCH', 'READ', 'B'),

((SELECT id FROM quizzes WHERE title='SQL Basics'),
 'Which command is used to add new records?',
 'ADD', 'INSERT', 'CREATE', 'PUT', 'B'),

((SELECT id FROM quizzes WHERE title='SQL Basics'),
 'Which command is used to modify existing records?',
 'CHANGE', 'MODIFY', 'UPDATE', 'ALTER', 'C'),

((SELECT id FROM quizzes WHERE title='SQL Basics'),
 'Which command removes records from a table?',
 'REMOVE', 'DELETE', 'DROP', 'CLEAR', 'B'),

((SELECT id FROM quizzes WHERE title='SQL Basics'),
 'Which clause is used to filter records?',
 'WHERE', 'FILTER', 'HAVING', 'SEARCH', 'A'),

((SELECT id FROM quizzes WHERE title='SQL Basics'),
 'Which keyword is used to sort query results?',
 'SORT', 'ORDER BY', 'GROUP BY', 'ARRANGE', 'B'),

((SELECT id FROM quizzes WHERE title='SQL Basics'),
 'Which function returns the number of rows?',
 'SUM()', 'COUNT()', 'TOTAL()', 'NUMBER()', 'B'),

((SELECT id FROM quizzes WHERE title='SQL Basics'),
 'Which command creates a new table?',
 'MAKE TABLE', 'NEW TABLE', 'CREATE TABLE', 'ADD TABLE', 'C'),

((SELECT id FROM quizzes WHERE title='SQL Basics'),
 'Which keyword removes duplicate results?',
 'UNIQUE', 'DISTINCT', 'REMOVE', 'DIFFERENT', 'B'),

((SELECT id FROM quizzes WHERE title='SQL Basics'),
 'Which symbol is used for all columns in SELECT?',
 '#', '*', '%', '&', 'B');


-- ============================================================
-- 10. HTML BASICS - 10 QUESTIONS
-- ============================================================

INSERT INTO questions
(quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES
((SELECT id FROM quizzes WHERE title='HTML Basics'),
 'What does HTML stand for?',
 'Hyper Text Markup Language',
 'High Text Machine Language',
 'Hyperlink Text Management Language',
 'Home Tool Markup Language', 'A'),

((SELECT id FROM quizzes WHERE title='HTML Basics'),
 'Which tag is used for the largest heading?',
 '<h6>', '<head>', '<h1>', '<heading>', 'C'),

((SELECT id FROM quizzes WHERE title='HTML Basics'),
 'Which tag is used to create a paragraph?',
 '<para>', '<p>', '<paragraph>', '<text>', 'B'),

((SELECT id FROM quizzes WHERE title='HTML Basics'),
 'Which tag creates a hyperlink?',
 '<link>', '<a>', '<href>', '<url>', 'B'),

((SELECT id FROM quizzes WHERE title='HTML Basics'),
 'Which tag is used to display an image?',
 '<image>', '<img>', '<picture>', '<src>', 'B'),

((SELECT id FROM quizzes WHERE title='HTML Basics'),
 'Which attribute specifies an image source?',
 'href', 'src', 'link', 'path', 'B'),

((SELECT id FROM quizzes WHERE title='HTML Basics'),
 'Which tag creates an unordered list?',
 '<ol>', '<ul>', '<list>', '<li>', 'B'),

((SELECT id FROM quizzes WHERE title='HTML Basics'),
 'Which tag creates a table row?',
 '<td>', '<tr>', '<row>', '<table-row>', 'B'),

((SELECT id FROM quizzes WHERE title='HTML Basics'),
 'Which tag is used to create a form?',
 '<input>', '<form>', '<data>', '<field>', 'B'),

((SELECT id FROM quizzes WHERE title='HTML Basics'),
 'Which tag is used for a line break?',
 '<break>', '<lb>', '<br>', '<line>', 'C');


-- ============================================================
-- 11. CSS BASICS - 10 QUESTIONS
-- ============================================================

INSERT INTO questions
(quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES
((SELECT id FROM quizzes WHERE title='CSS Basics'),
 'What does CSS stand for?',
 'Computer Style Sheets',
 'Cascading Style Sheets',
 'Creative Style System',
 'Colorful Style Sheets', 'B'),

((SELECT id FROM quizzes WHERE title='CSS Basics'),
 'Which property changes text color?',
 'font-color', 'text-color', 'color', 'foreground', 'C'),

((SELECT id FROM quizzes WHERE title='CSS Basics'),
 'Which property changes the background color?',
 'background-color', 'bgcolor', 'background', 'color-background', 'A'),

((SELECT id FROM quizzes WHERE title='CSS Basics'),
 'Which symbol represents a class selector?',
 '#', '.', '*', '@', 'B'),

((SELECT id FROM quizzes WHERE title='CSS Basics'),
 'Which symbol represents an ID selector?',
 '.', '#', '*', '&', 'B'),

((SELECT id FROM quizzes WHERE title='CSS Basics'),
 'Which property changes text size?',
 'text-size', 'font-size', 'size', 'font-height', 'B'),

((SELECT id FROM quizzes WHERE title='CSS Basics'),
 'Which property makes text bold?',
 'font-weight', 'text-bold', 'font-style', 'bold', 'A'),

((SELECT id FROM quizzes WHERE title='CSS Basics'),
 'Which property controls the space inside an element?',
 'margin', 'padding', 'spacing', 'border', 'B'),

((SELECT id FROM quizzes WHERE title='CSS Basics'),
 'Which property controls the space outside an element?',
 'padding', 'margin', 'space', 'outside', 'B'),

((SELECT id FROM quizzes WHERE title='CSS Basics'),
 'Which CSS layout system is useful for one-dimensional layouts?',
 'Grid', 'Flexbox', 'Table', 'Float', 'B');


-- ============================================================
-- 12. JAVASCRIPT BASICS - 10 QUESTIONS
-- ============================================================

INSERT INTO questions
(quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES
((SELECT id FROM quizzes WHERE title='JavaScript Basics'),
 'Which keyword declares a variable in JavaScript?',
 'var', 'variable', 'declare', 'letvar', 'A'),

((SELECT id FROM quizzes WHERE title='JavaScript Basics'),
 'Which keyword can declare a block-scoped variable?',
 'var', 'let', 'define', 'variable', 'B'),

((SELECT id FROM quizzes WHERE title='JavaScript Basics'),
 'Which keyword declares a constant?',
 'constant', 'const', 'fixed', 'final', 'B'),

((SELECT id FROM quizzes WHERE title='JavaScript Basics'),
 'Which method displays a message in the browser console?',
 'console.log()', 'print()', 'display()', 'log.console()', 'A'),

((SELECT id FROM quizzes WHERE title='JavaScript Basics'),
 'Which symbol is used for strict equality?',
 '=', '==', '===', '!=', 'C'),

((SELECT id FROM quizzes WHERE title='JavaScript Basics'),
 'Which method adds an element to the end of an array?',
 'add()', 'push()', 'append()', 'insert()', 'B'),

((SELECT id FROM quizzes WHERE title='JavaScript Basics'),
 'Which keyword defines a function?',
 'function', 'def', 'func', 'method', 'A'),

((SELECT id FROM quizzes WHERE title='JavaScript Basics'),
 'Which object represents the web page?',
 'window', 'document', 'browser', 'page', 'B'),

((SELECT id FROM quizzes WHERE title='JavaScript Basics'),
 'Which event occurs when a button is clicked?',
 'onpress', 'onclick', 'onbutton', 'clickbutton', 'B'),

((SELECT id FROM quizzes WHERE title='JavaScript Basics'),
 'Which value represents absence of a value?',
 'empty', 'null', 'none', 'voidvalue', 'B');


-- ============================================================
-- 13. C PROGRAMMING - 10 QUESTIONS
-- ============================================================

INSERT INTO questions
(quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES
((SELECT id FROM quizzes WHERE title='C Programming'),
 'Which function is the starting point of a C program?',
 'start()', 'main()', 'run()', 'begin()', 'B'),

((SELECT id FROM quizzes WHERE title='C Programming'),
 'Which header file is used for printf()?',
 '<string.h>', '<stdio.h>', '<math.h>', '<stdlib.h>', 'B'),

((SELECT id FROM quizzes WHERE title='C Programming'),
 'Which symbol ends a C statement?',
 ':', '.', ';', ',', 'C'),

((SELECT id FROM quizzes WHERE title='C Programming'),
 'Which data type stores integers?',
 'float', 'char', 'int', 'double', 'C'),

((SELECT id FROM quizzes WHERE title='C Programming'),
 'Which operator is used to get the address of a variable?',
 '*', '&', '#', '@', 'B'),

((SELECT id FROM quizzes WHERE title='C Programming'),
 'Which loop executes at least once?',
 'for', 'while', 'do-while', 'foreach', 'C'),

((SELECT id FROM quizzes WHERE title='C Programming'),
 'Which symbol is used for a single-line comment?',
 '/*', '//', '#', '--', 'B'),

((SELECT id FROM quizzes WHERE title='C Programming'),
 'Which function is used to read formatted input?',
 'printf()', 'scanf()', 'input()', 'read()', 'B'),

((SELECT id FROM quizzes WHERE title='C Programming'),
 'Which data type stores a single character?',
 'string', 'char', 'character', 'text', 'B'),

((SELECT id FROM quizzes WHERE title='C Programming'),
 'Which keyword is used to return a value from a function?',
 'send', 'return', 'output', 'give', 'B');


-- ============================================================
-- 14. C++ PROGRAMMING - 10 QUESTIONS
-- ============================================================

INSERT INTO questions
(quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES
((SELECT id FROM quizzes WHERE title='C++ Programming'),
 'Which extension is commonly used for C++ source files?',
 '.java', '.cpp', '.py', '.html', 'B'),

((SELECT id FROM quizzes WHERE title='C++ Programming'),
 'Which keyword creates a class?',
 'class', 'Class', 'structclass', 'object', 'A'),

((SELECT id FROM quizzes WHERE title='C++ Programming'),
 'Which operator is used with cout?',
 '>>', '<<', '=>', '<=', 'B'),

((SELECT id FROM quizzes WHERE title='C++ Programming'),
 'Which object is commonly used for output?',
 'cin', 'cout', 'print', 'output', 'B'),

((SELECT id FROM quizzes WHERE title='C++ Programming'),
 'Which object is commonly used for input?',
 'cin', 'cout', 'input', 'scan', 'A'),

((SELECT id FROM quizzes WHERE title='C++ Programming'),
 'Which concept allows multiple forms?',
 'Inheritance', 'Polymorphism', 'Compilation', 'Looping', 'B'),

((SELECT id FROM quizzes WHERE title='C++ Programming'),
 'Which symbol is used for scope resolution?',
 '.', '::', '->', ':', 'B'),

((SELECT id FROM quizzes WHERE title='C++ Programming'),
 'Which keyword is used to create an object dynamically?',
 'malloc', 'new', 'create', 'object', 'B'),

((SELECT id FROM quizzes WHERE title='C++ Programming'),
 'Which feature allows a class to inherit another class?',
 'Inheritance', 'Encapsulation', 'Overloading', 'Casting', 'A'),

((SELECT id FROM quizzes WHERE title='C++ Programming'),
 'Which header is commonly used for cout and cin?',
 '<stdio.h>', '<iostream>', '<input.h>', '<stream.h>', 'B');


-- ============================================================
-- 15. DBMS BASICS - 10 QUESTIONS
-- ============================================================

INSERT INTO questions
(quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES
((SELECT id FROM quizzes WHERE title='DBMS Basics'),
 'What does DBMS stand for?',
 'Database Management System',
 'Data Backup Management System',
 'Database Machine System',
 'Data Management Software', 'A'),

((SELECT id FROM quizzes WHERE title='DBMS Basics'),
 'Which key uniquely identifies a record?',
 'Foreign Key', 'Primary Key', 'Candidate Key', 'Normal Key', 'B'),

((SELECT id FROM quizzes WHERE title='DBMS Basics'),
 'Which key connects two tables?',
 'Primary Key', 'Foreign Key', 'Super Key', 'Unique Key', 'B'),

((SELECT id FROM quizzes WHERE title='DBMS Basics'),
 'Which command is used to retrieve data?',
 'INSERT', 'SELECT', 'UPDATE', 'DELETE', 'B'),

((SELECT id FROM quizzes WHERE title='DBMS Basics'),
 'What is normalization used for?',
 'Increasing duplication',
 'Reducing data redundancy',
 'Deleting tables',
 'Increasing storage', 'B'),

((SELECT id FROM quizzes WHERE title='DBMS Basics'),
 'Which relationship connects one record to many records?',
 'One-to-one', 'One-to-many', 'Many-to-one only', 'None', 'B'),

((SELECT id FROM quizzes WHERE title='DBMS Basics'),
 'Which constraint prevents NULL values?',
 'UNIQUE', 'NOT NULL', 'CHECK', 'DEFAULT', 'B'),

((SELECT id FROM quizzes WHERE title='DBMS Basics'),
 'Which constraint ensures unique values?',
 'UNIQUE', 'CHECK', 'DEFAULT', 'NULL', 'A'),

((SELECT id FROM quizzes WHERE title='DBMS Basics'),
 'Which SQL command removes a table?',
 'DELETE', 'REMOVE', 'DROP', 'CLEAR', 'C'),

((SELECT id FROM quizzes WHERE title='DBMS Basics'),
 'Which database object stores data in rows and columns?',
 'Table', 'Query', 'View only', 'Index', 'A');


-- ============================================================
-- 16. COMPUTER NETWORKS - 10 QUESTIONS
-- ============================================================

INSERT INTO questions
(quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES
((SELECT id FROM quizzes WHERE title='Computer Networks'),
 'What does LAN stand for?',
 'Local Area Network',
 'Large Area Network',
 'Long Area Network',
 'Local Access Node', 'A'),

((SELECT id FROM quizzes WHERE title='Computer Networks'),
 'What does WAN stand for?',
 'Wide Area Network',
 'Wireless Area Network',
 'Web Area Network',
 'World Access Network', 'A'),

((SELECT id FROM quizzes WHERE title='Computer Networks'),
 'Which device connects different networks?',
 'Switch', 'Router', 'Hub', 'Repeater', 'B'),

((SELECT id FROM quizzes WHERE title='Computer Networks'),
 'Which protocol is used for web pages?',
 'FTP', 'HTTP', 'SMTP', 'SSH', 'B'),

((SELECT id FROM quizzes WHERE title='Computer Networks'),
 'Which protocol is commonly used to send email?',
 'SMTP', 'HTTP', 'FTP', 'DNS', 'A'),

((SELECT id FROM quizzes WHERE title='Computer Networks'),
 'What does IP stand for?',
 'Internet Protocol',
 'Internal Process',
 'Internet Process',
 'Input Protocol', 'A'),

((SELECT id FROM quizzes WHERE title='Computer Networks'),
 'Which device forwards data within a local network?',
 'Router', 'Switch', 'Modem', 'Firewall', 'B'),

((SELECT id FROM quizzes WHERE title='Computer Networks'),
 'Which protocol converts domain names into IP addresses?',
 'HTTP', 'DNS', 'FTP', 'TCP', 'B'),

((SELECT id FROM quizzes WHERE title='Computer Networks'),
 'Which protocol provides reliable data transmission?',
 'UDP', 'TCP', 'IP', 'HTTP', 'B'),

((SELECT id FROM quizzes WHERE title='Computer Networks'),
 'Which device is used to connect a computer to the internet?',
 'Keyboard', 'Modem', 'Monitor', 'Printer', 'B');


-- ============================================================
-- 17. VERIFICATION QUERIES
-- ============================================================

-- Show all quizzes
SELECT
    id,
    title,
    topic
FROM quizzes
ORDER BY id;


-- Show number of questions for every quiz
SELECT
    quizzes.id,
    quizzes.title,
    quizzes.topic,
    COUNT(questions.id) AS total_questions
FROM quizzes
LEFT JOIN questions
    ON quizzes.id = questions.quiz_id
GROUP BY
    quizzes.id,
    quizzes.title,
    quizzes.topic
ORDER BY quizzes.id;


-- Total number of questions
SELECT COUNT(*) AS total_questions
FROM questions;


-- Expected result:
-- 10 quizzes
-- 100 questions
