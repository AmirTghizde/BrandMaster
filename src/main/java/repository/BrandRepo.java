package repository;

import connection.JdbcConnection;
import model.Brand;

import java.sql.*;

public class BrandRepo {
    JdbcConnection jdbcConnection = new JdbcConnection();
    Connection connection = jdbcConnection.getConnection();

    public BrandRepo() throws SQLException {
    }

    public void save(Brand brand) throws SQLException {
        String query = "insert into brand (name, website, description) values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, brand.getName());
        preparedStatement.setString(2, brand.getWebsite());
        preparedStatement.setString(3, brand.getDescription());
        int result = preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        printResult(result);
        if (resultSet.next()) {
            int autoIncrement = resultSet.getInt("id");
            brand.setId(autoIncrement);
            System.out.println("Brand id is: " + brand.getId());
        }

    }

    public void editName(int id, String newName) throws SQLException {
        String query = "update brand set name=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newName);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();

        printResult(result);
    }

    public void editWebsite(int id, String newWebsite) throws SQLException {
        String query = "update brand set website=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newWebsite);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();

        printResult(result);
    }

    public void editDescription(int id, String newDescription) throws SQLException {
        String query = "update brand set description=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newDescription);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();

        printResult(result);
    }

    //TODO add a delete method so when a brand is deleted the products also get deleted
    public void delete(int id) throws SQLException {
        String query = "delete from brand where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        int result = preparedStatement.executeUpdate();

        printResult(result);
    }

    public boolean isNameExists(String name) throws SQLException {
        String query = "select name from brand where name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    public void printResult(int result) {
        if (result > 0) {
            System.out.println("+Successful");
        } else System.out.println("!Failed");
    }
}
