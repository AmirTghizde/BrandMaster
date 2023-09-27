package repository;

import connection.JdbcConnection;
import model.User;

import java.sql.*;

public class UserRepo {
    JdbcConnection jdbcConnection = new JdbcConnection();
    Connection connection = jdbcConnection.getConnection();

    public UserRepo() throws SQLException {

    }


    public User login(String username) throws SQLException {
        String query="select * from users where username=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,username);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (resultSet.next()){
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }
        return null;
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

        printResult(result);
            if (resultSet.next()) {
                int autoIncrementValue = resultSet.getInt(1);
                user.setId(autoIncrementValue);
            }
    }
    public void editName(int id,String name) throws SQLException {
        String query = "update users set name=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();

        printResult(result);
    }

    public void editUsername(String currentUsername, String newUsername) throws SQLException {
        //Makes the user to edit their username easier by getting the old and new username than getting the userID.
        int id = findIdByUsername(currentUsername);
        if (id != -1) {
            String query = "update users set username=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newUsername);
            preparedStatement.setInt(2, id);
            int result = preparedStatement.executeUpdate();

            printResult(result);
        } else {
            System.out.println("!Failed to find the username");
        }
    }

    public void editEmail(String currentEmail, String newEmail) throws SQLException {
        int id = findIdByEmail(currentEmail);
        if (id != -1) {
            String query = "update users set email=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newEmail);
            preparedStatement.setInt(2, id);
            int result = preparedStatement.executeUpdate();

            printResult(result);
        } else {
            System.out.println("!Failed to find the email");
        }
    }
    public void editPassword(int id,String password) throws SQLException {
        String query = "update users set password=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, password);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();

        printResult(result);
    }

    public void delete(String username) throws SQLException {
        //getting the username as a delete parameter felt more real
        int id = findIdByUsername(username);
        if (id != -1) {
            String query = "delete from users where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();

            printResult(result);
        } else {
            System.out.println("!Username not found");
        }

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

    public int findIdByEmail(String email) throws SQLException {
        String query = "select id from users where email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return -1;
    }
    public void printResult(int result) {
        if (result > 0) {
            System.out.println("+Successful");
        } else System.out.println("!Failed");
    }
}
