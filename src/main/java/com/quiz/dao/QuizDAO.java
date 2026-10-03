package com.quiz.dao;

import com.quiz.DBConnection;
import com.quiz.model.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {

    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM quizzes ORDER BY id";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()
        ) {
            while (result.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(result.getInt("id"));
                quiz.setTitle(result.getString("title"));
                quiz.setTopic(result.getString("topic"));
                quizzes.add(quiz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quizzes;
    }

    public boolean addQuiz(Quiz quiz) {
        String sql = "INSERT INTO quizzes (title, topic) VALUES (?, ?)";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, quiz.getTitle());
            statement.setString(2, quiz.getTopic());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

   public boolean deleteQuiz(int quizId) {

    String deleteResults =
            "DELETE FROM results WHERE quiz_id = ?";

    String deleteQuestions =
            "DELETE FROM questions WHERE quiz_id = ?";

    String deleteQuiz =
            "DELETE FROM quizzes WHERE id = ?";

    try (
            Connection connection =
                    DBConnection.getConnection()
    ) {

        connection.setAutoCommit(false);

        try (
                PreparedStatement resultStatement =
                        connection.prepareStatement(deleteResults);

                PreparedStatement questionStatement =
                        connection.prepareStatement(deleteQuestions);

                PreparedStatement quizStatement =
                        connection.prepareStatement(deleteQuiz)
        ) {

            resultStatement.setInt(1, quizId);
            resultStatement.executeUpdate();

            questionStatement.setInt(1, quizId);
            questionStatement.executeUpdate();

            quizStatement.setInt(1, quizId);

            int rows =
                    quizStatement.executeUpdate();

            connection.commit();

            return rows > 0;

        } catch (Exception e) {

            connection.rollback();

            throw e;
        }

    } catch (Exception e) {

        e.printStackTrace();
        return false;
    }
}
public Quiz getQuizById(int quizId) {

    String sql =
            "SELECT * FROM quizzes WHERE id = ?";

    try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setInt(1, quizId);

        ResultSet result =
                statement.executeQuery();

        if (result.next()) {

            Quiz quiz = new Quiz();

            quiz.setId(
                    result.getInt("id")
            );

            quiz.setTitle(
                    result.getString("title")
            );

            quiz.setTopic(
                    result.getString("topic")
            );

            return quiz;
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return null;
}
}
