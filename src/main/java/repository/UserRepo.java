package repository;

import connection.JdbcConnection;
import model.User;

import java.sql.*;

public class UserRepo {
    JdbcConnection jdbcConnection = new JdbcConnection();
    Connection connection = jdbcConnection.getConnection();

    public UserRepo() throws SQLException {

    }

    public void save(User user) throws SQLException {
        String query = "INSERT INTO users(name, username, email, password) values (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        int result = preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (result > 0) {
            System.out.println("+Successfully registered");
            if (resultSet.next()) {
                int autoIncrementValue = resultSet.getInt(1);
                user.setId(autoIncrementValue);
                System.out.println("Your id is: [ " + user.getId() + " ]");
            }
        } else {
            System.out.println("!Failed to register");
        }
    }

    public void editUsername(String currentUsername, String newUsername) throws SQLException {
        int id = findIdByUsername(currentUsername);
        if (id != -1) {
            String query = "update users set username=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newUsername);
            preparedStatement.setInt(2, id);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("+Successfully changed username");
            } else System.out.println("!Failed to change username");
        }else {
            System.out.println("!Failed to find the username");
        }


    }

    public void delete(int id) throws SQLException {
        String query = "delete from users where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("-Successfully deleted the row");
        } else System.out.println("!Deletion failed ");
    }

    public boolean isUsernameExists(String username) throws SQLException {
        String query = "select username from users where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        //Simplified version of return true or false
        return resultSet.next();
    }

    public boolean isEmailExists(String email) throws SQLException {
        String query = "select email from users where email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        //Simplified version of return true or false
        return resultSet.next();
    }

    public int findIdByUsername(String username) throws SQLException {
        String query = "select id from users where username=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return -1;
    }
}
