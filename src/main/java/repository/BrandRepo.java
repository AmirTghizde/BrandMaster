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
        String query="insert into brand (name, website, description) values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, brand.getName());
        preparedStatement.setString(2, brand.getWebsite());
        preparedStatement.setString(3, brand.getDescription());
        int result=preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (result>0){
            System.out.println("+Successfully added to database");
            if (resultSet.next()){
                int autoIncrement = resultSet.getInt("id");
                brand.setId(autoIncrement);
                System.out.println("Brand id is: "+brand.getId());
            }
        }else {
            System.out.println("!Failed");
        }
    }

    public boolean isNameExists(String name) throws SQLException {
        String query="select name from brand where name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        ResultSet resultSet =preparedStatement.executeQuery();

        return resultSet.next();
    }
}
