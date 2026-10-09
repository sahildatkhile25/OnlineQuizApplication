package com.quiz.dao;

import com.quiz.DBConnection;
import com.quiz.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    // Get all questions for a quiz
    public List<Question> getQuestionsByQuizId(int quizId) {

        List<Question> questions = new ArrayList<>();

        String sql =
        "SELECT * FROM questions WHERE quiz_id = ? ORDER BY RAND()";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, quizId);

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                Question question = new Question();

                question.setId(result.getInt("id"));
                question.setQuizId(result.getInt("quiz_id"));

                question.setQuestionText(
                        result.getString("question_text")
                );

                question.setOptionA(
                        result.getString("option_a")
                );

                question.setOptionB(
                        result.getString("option_b")
                );

                question.setOptionC(
                        result.getString("option_c")
                );

                question.setOptionD(
                        result.getString("option_d")
                );

                question.setCorrectAnswer(
                        result.getString("correct_answer")
                );
                question.setDifficulty(
                        result.getString("difficulty")
                );
                

                questions.add(question);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return questions;
    }


    // Add a new question
    public boolean addQuestion(Question question) {

    String sql =
            "INSERT INTO questions " +
            "(quiz_id, question_text, option_a, option_b, " +
            "option_c, option_d, correct_answer, difficulty) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setInt(
                1,
                question.getQuizId()
        );

        statement.setString(
                2,
                question.getQuestionText()
        );

        statement.setString(
                3,
                question.getOptionA()
        );

        statement.setString(
                4,
                question.getOptionB()
        );

        statement.setString(
                5,
                question.getOptionC()
        );

        statement.setString(
                6,
                question.getOptionD()
        );

        statement.setString(
                7,
                question.getCorrectAnswer()
        );

        statement.setString(
                8,
                question.getDifficulty()
        );

        int rows =
                statement.executeUpdate();

        return rows > 0;

    } catch (Exception e) {

        e.printStackTrace();
        return false;
    }
}

    // Get one question by ID
    public Question getQuestionById(int questionId) {

        String sql =
                "SELECT * FROM questions WHERE id = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, questionId);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                Question question = new Question();

                question.setId(
                        result.getInt("id")
                );

                question.setQuizId(
                        result.getInt("quiz_id")
                );

                question.setQuestionText(
                        result.getString("question_text")
                );

                question.setOptionA(
                        result.getString("option_a")
                );

                question.setOptionB(
                        result.getString("option_b")
                );

                question.setOptionC(
                        result.getString("option_c")
                );

                question.setOptionD(
                        result.getString("option_d")
                );

                question.setCorrectAnswer(
                        result.getString("correct_answer")
                );

                return question;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // Update a question
   public boolean updateQuestion(Question question) {

    String sql =
            "UPDATE questions SET " +
            "question_text = ?, " +
            "option_a = ?, " +
            "option_b = ?, " +
            "option_c = ?, " +
            "option_d = ?, " +
            "correct_answer = ?, " +
            "difficulty = ? " +
            "WHERE id = ?";

    try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setString(
                1,
                question.getQuestionText()
        );

        statement.setString(
                2,
                question.getOptionA()
        );

        statement.setString(
                3,
                question.getOptionB()
        );

        statement.setString(
                4,
                question.getOptionC()
        );

        statement.setString(
                5,
                question.getOptionD()
        );

        statement.setString(
                6,
                question.getCorrectAnswer()
        );

        statement.setString(
                7,
                question.getDifficulty()
        );

        statement.setInt(
                8,
                question.getId()
        );

        int rows =
                statement.executeUpdate();

        return rows > 0;

    } catch (Exception e) {

        e.printStackTrace();
        return false;
    }
}
    // Delete a question
    public boolean deleteQuestion(int questionId) {

        String sql =
                "DELETE FROM questions WHERE id = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, questionId);

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}