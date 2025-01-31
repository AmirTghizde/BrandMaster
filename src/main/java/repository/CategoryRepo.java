package repository;

import model.Category;

import java.sql.*;

public class CategoryRepo {
    private  final  Connection connection;

    public CategoryRepo(Connection connection) {
        this.connection = connection;
    }

    public void save(Category category) throws SQLException {
        String query = "insert into category (name, description) values (?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setString(2, category.getDescription());
        int result = preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        printResult(result);
        if (resultSet.next()) {
            int autoIncrementValue = resultSet.getInt(1);
            category.setId(autoIncrementValue);
            System.out.println("Category id is: " + category.getId());
        }
    }

    public void editName(int id, String newName) throws SQLException {
        String query = "update category set name=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newName);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();

        printResult(result);
    }

    public void editDescription(int id, String Description) throws SQLException {
        String query = "update category set description=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, Description);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();

        printResult(result);
    }

    public void delete(int id) throws SQLException {
        String query = "delete from category where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        int result = preparedStatement.executeUpdate();

        printResult(result);
    }

    public boolean isNameExists(String name) throws SQLException {
        String query = "select name from category where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        //Simplified version of return true or false
        return resultSet.next();
    }

    public void showCategory() throws SQLException {
        String query = "select * from category";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = (resultSet.getInt("id"));
            String name = (resultSet.getString("name"));
            String description = (resultSet.getString("description"));
            System.out.println("\t" + id + ".\t\t" + name + "\t\t" + description);
        }
    }

    public Category load(int id) throws SQLException {
        String query = "select * from category where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Category category = new Category();
            category.setId(resultSet.getInt("id"));
            category.setName(resultSet.getString("name"));
            category.setDescription(resultSet.getString("description"));
            return category;
        }
        return null;
    }

    public void printResult(int result) {
        if (result > 0) {
            System.out.println("+Successful");
        } else System.out.println("!Failed");
    }
}


