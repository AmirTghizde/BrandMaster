package repository;

import connection.JdbcConnection;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ProductRepo {
    JdbcConnection jdbcConnection = new JdbcConnection();
    Connection connection = jdbcConnection.getConnection();

    public ProductRepo() throws SQLException {
    }

    public void save(Product product) throws SQLException {
        String query="insert into product(name, create_date, category_id, brand_id)  values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,product.getName());
        preparedStatement.setString(2,product.getCreateDate());
        preparedStatement.setInt(3,product.getCategory().getId());
        preparedStatement.setInt(4,product.getBrand().getId());
        int result=preparedStatement.executeUpdate();
        printResult(result);
    }

    public void editName(int id,String newName) throws SQLException {
        String query="update product set name=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,newName);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();

        printResult(result);
    }

    public void editCreateDate(int id,String newCreateDate) throws SQLException {
        String query="update product set create_date=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,newCreateDate);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();

        printResult(result);
    }
     public void editCategory_id(int id,int category_id) throws SQLException {
         String query="update product set category_id=? where id=?";
         PreparedStatement preparedStatement=connection.prepareStatement(query);
         preparedStatement.setInt(1,category_id);
         preparedStatement.setInt(2,id);
         int result=preparedStatement.executeUpdate();

         printResult(result);
     }

    public void editBrand_id(int id,int brand_id) throws SQLException {
        String query="update product set brand_id=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,brand_id);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();

        printResult(result);
    }


    public void delete(int id) throws SQLException {
        String query="delete from product where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result=preparedStatement.executeUpdate();
        printResult(result);
    }
    public void showProducts() throws SQLException {
        String query = "select * from product";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = (resultSet.getInt("id"));
            String name = (resultSet.getString("name"));
            String description = (resultSet.getString("create_date"));
            int categoryID = (resultSet.getInt("category_id"));
            int brandID= (resultSet.getInt("brand_id"));
            System.out.println("\t"+id+".\t\t"+name+"\t\t"+description+"\t\t"+categoryID+"\t\t"+brandID);
        }
    }
    public void printResult(int result) {
        if (result > 0) {
            System.out.println("+Successful");
        } else System.out.println("!Failed");
    }
}
