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

    public void showBrands() throws SQLException {
        String query = "select * from brand";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = (resultSet.getInt("id"));
            String name = (resultSet.getString("name"));
            String website = (resultSet.getString("website"));
            String description = (resultSet.getString("description"));
            System.out.println("\t"+id+".\t"+name+"\t"+website+"\t"+description );
        }
    }
    public Brand load(int id ) throws SQLException {
        String query="SELECT * FROM brand WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();

        if (resultSet.next()){
            Brand brand = new Brand();
            brand.setId(resultSet.getInt("id"));
            brand.setName(resultSet.getString("name"));
            brand.setWebsite(resultSet.getString("website"));
            brand.setDescription(resultSet.getString("description"));
            return brand;
        }return null;
    }

    public void printResult(int result) {
        if (result > 0) {
            System.out.println("+Successful");
        } else System.out.println("!Failed");
    }
}
