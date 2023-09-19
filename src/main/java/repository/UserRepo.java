package repository;

import connection.JdbcConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {
    JdbcConnection jdbcConnection = new JdbcConnection();
    Connection connection = jdbcConnection.getConnection();

    public UserRepo() throws SQLException {

    }

    public void save(User user) throws SQLException {
        String query = "INSERT INTO users(name, username, email, password) values (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("+Successfully registered");
        } else {
            System.out.println("-Failed to register");
        }
    }
    public boolean isUsernameExists(String username) throws SQLException {
        String query="select username from users where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();

        //Simplified version of return true or false
        return resultSet.next();
    }
    public boolean isEmailExists(String email) throws SQLException {
        String query="select email from users where email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        ResultSet resultSet = preparedStatement.executeQuery();

        //Simplified version of return true or false
        return resultSet.next();
    }
}
