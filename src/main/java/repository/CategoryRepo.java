package repository;

import connection.JdbcConnection;
import model.Category;

import java.sql.*;

public class CategoryRepo {
    JdbcConnection jdbcConnection = new JdbcConnection();
    Connection connection = jdbcConnection.getConnection();

    public CategoryRepo() throws SQLException {
    }

    public void save(Category category) throws SQLException {
        String query="insert into category (name, description) values (?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setString(2, category.getDescription());
        int result=preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        printResult(result);
            if (resultSet.next()){
                int autoIncrementValue = resultSet.getInt(1);
                category.setId(autoIncrementValue);
                System.out.println("Category id is: "+category.getId());
            }
    }

    public void editName(int id,String newName) throws SQLException {
        String query="update category set name=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,newName);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();

        printResult(result);
    }
    public void editDescription(int id,String Description) throws SQLException {
        String query="update category set description=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,Description);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();

        printResult(result);
    }
    public void delete(int id) throws SQLException {
        String query="delete from category where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result=preparedStatement.executeUpdate();

        printResult(result);
    }

    public boolean isNameExists(String name) throws SQLException {
        String query = "select name from category where name = ?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();

        //Simplified version of return true or false
        return resultSet.next();
    }

public void printResult(int result){
        if (result>0){
            System.out.println("+Successful");
        }else System.out.println("!Failed");
}
}


