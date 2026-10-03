package com.quiz.dao;

import com.quiz.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    public boolean saveResult(
        int userId,
        int quizId,
        int score,
        int totalQuestions) {

    String sql = """
            INSERT INTO results
            (user_id, quiz_id, score, total_questions, can_retry)
            VALUES (?, ?, ?, ?, FALSE)
            ON DUPLICATE KEY UPDATE
            score = VALUES(score),
            total_questions = VALUES(total_questions),
            attempt_date = CURRENT_TIMESTAMP,
            can_retry = FALSE
            """;

    try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setInt(1, userId);
        statement.setInt(2, quizId);
        statement.setInt(3, score);
        statement.setInt(4, totalQuestions);

        int rows =
                statement.executeUpdate();

        return rows > 0;

    } catch (Exception e) {

        e.printStackTrace();
        return false;
    }
}

    public List<String[]> getResultsByUserId(int userId) {

        List<String[]> results =
                new ArrayList<>();

        String sql = """
                SELECT quizzes.title,
                       results.score,
                       results.total_questions,
                       results.attempt_date
                FROM results
                JOIN quizzes
                ON results.quiz_id = quizzes.id
                WHERE results.user_id = ?
                ORDER BY results.attempt_date DESC
                """;

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, userId);

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                String[] row = {
                        result.getString("title"),
                        String.valueOf(
                                result.getInt("score")
                        ),
                        String.valueOf(
                                result.getInt("total_questions")
                        ),
                        result.getTimestamp(
                                "attempt_date"
                        ).toString()
                };

                results.add(row);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return results;
    }

    public List<String[]> getLeaderboard() {

    List<String[]> leaderboard =
            new ArrayList<>();

    String sql = """
            SELECT users.username,
                   quizzes.title,
                   results.score,
                   results.total_questions
            FROM results
            JOIN users
            ON results.user_id = users.id
            JOIN quizzes
            ON results.quiz_id = quizzes.id
            ORDER BY results.score DESC,
                     results.attempt_date ASC
            LIMIT 10
            """;

    try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery()
    ) {

        while (result.next()) {

            String[] row = {
                    result.getString("username"),
                    result.getString("title"),
                    String.valueOf(
                            result.getInt("score")
                    ),
                    String.valueOf(
                            result.getInt("total_questions")
                    )
            };

            leaderboard.add(row);
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return leaderboard;
}

public boolean hasAttemptedQuiz(
        int userId,
        int quizId) {

    String sql =
            "SELECT id FROM results " +
            "WHERE user_id = ? AND quiz_id = ?";

    try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setInt(1, userId);
        statement.setInt(2, quizId);

        ResultSet result =
                statement.executeQuery();

        return result.next();

    } catch (Exception e) {

        e.printStackTrace();
        return false;
    }
}

public boolean canRetryQuiz(
        int userId,
        int quizId) {

    String sql =
            "SELECT can_retry FROM results " +
            "WHERE user_id = ? AND quiz_id = ?";

    try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setInt(1, userId);
        statement.setInt(2, quizId);

        ResultSet result =
                statement.executeQuery();

        if (result.next()) {

            return result.getBoolean("can_retry");
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
}
public boolean allowRetry(
        int userId,
        int quizId) {

    String sql =
            "UPDATE results " +
            "SET can_retry = TRUE " +
            "WHERE user_id = ? AND quiz_id = ?";

    try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setInt(1, userId);
        statement.setInt(2, quizId);

        int rows =
                statement.executeUpdate();

        return rows > 0;

    } catch (Exception e) {

        e.printStackTrace();
        return false;
    }
}
public List<String[]> getAllAttempts() {

    List<String[]> attempts =
            new ArrayList<>();

    String sql = """
            SELECT results.user_id,
                   results.quiz_id,
                   users.username,
                   quizzes.title,
                   results.score,
                   results.total_questions,
                   results.can_retry
            FROM results
            JOIN users
            ON results.user_id = users.id
            JOIN quizzes
            ON results.quiz_id = quizzes.id
            ORDER BY results.attempt_date DESC
            """;

    try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery()
    ) {

        while (result.next()) {

            String[] row = {
                    String.valueOf(
                            result.getInt("user_id")
                    ),

                    String.valueOf(
                            result.getInt("quiz_id")
                    ),

                    result.getString("username"),

                    result.getString("title"),

                    String.valueOf(
                            result.getInt("score")
                    ),

                    String.valueOf(
                            result.getInt("total_questions")
                    ),

                    String.valueOf(
                            result.getBoolean("can_retry")
                    )
            };

            attempts.add(row);
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return attempts;
}
}