package com.quiz.dao;

import com.quiz.DBConnection;
import com.quiz.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // Register a new user
    public boolean registerUser(User user) {

        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
    e.printStackTrace();
    return false;
}
    }


    // Find user by username
    public User getUserByUsername(String username) {

        String sql = "SELECT * FROM users WHERE username = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                User user = new User();

                user.setId(result.getInt("id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setRole(result.getString("role"));

                return user;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}