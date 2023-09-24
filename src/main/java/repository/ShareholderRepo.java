package repository;

import connection.JdbcConnection;
import model.Shareholder;

import java.sql.*;

public class ShareholderRepo {
    JdbcConnection jdbcConnection = new JdbcConnection();
    Connection connection = jdbcConnection.getConnection();

    public ShareholderRepo() throws SQLException {
    }
    public void save(Shareholder shareholder,int brandID) throws SQLException {
        String query="insert into shareholder(name, phonenumber, nationalcode) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, shareholder.getName());
        preparedStatement.setInt(2, shareholder.getPhoneNumber());
        preparedStatement.setInt(3, shareholder.getNationalCode());
        int result=preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (result>0){
            System.out.println("+Successfully added to database");
            if (resultSet.next()){
                int autoIncrement = resultSet.getInt("id");
                shareholder.setId(autoIncrement);
                saveToShareholder_Brand(shareholder.getId(),brandID);
                System.out.println("Shareholder id is: "+shareholder.getId());
            }
        }else {
            System.out.println("!Failed");
        }
    }
    public void saveToShareholder_Brand(int shareholderID,int brandID) throws SQLException {
        String query="insert into shareholder_brand(shareholder_id, brand_id) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, shareholderID);
        preparedStatement.setInt(2, brandID);
        int result=preparedStatement.executeUpdate();
        if (result>0){
            System.out.println("+Successfully added to database");
        }else {
            System.out.println("!Failed");
        }
    }
    public void editName(int id,String newName) throws SQLException {
        String query="update shareholder set name=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,newName);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        if (result>0){
            System.out.println("+Successfully changed name");
        }else System.out.println("!failed to change name");
    }
    public void editPhoneNumber(int id,int newPhoneNumber) throws SQLException {
        String query="update shareholder set phonenumber=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,newPhoneNumber);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        if (result>0){
            System.out.println("+Successfully changed phoneNumber");
        }else System.out.println("!failed to change phoneNumber");
    }
    public void editNationalCode(int id,int newNationalCode) throws SQLException {
        String query="update shareholder set nationalcode=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,newNationalCode);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        if (result>0){
            System.out.println("+Successfully changed nationalCode");
        }else System.out.println("!failed to change nationalCode");
    }

    public void delete(int id) throws SQLException {
        deleteFromShareholder_Brand(id);
        String query="delete from shareholder where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result=preparedStatement.executeUpdate();

        if (result>0){
            System.out.println("-Successfully deleted");
        }else {
            System.out.println("!Failed to delete");
        }
    }
    public void deleteFromShareholder_Brand(int id) throws SQLException {
        String query="delete  from shareholder_brand where shareholder_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    public boolean isNationalCodeExists(int nationalCode) throws SQLException {
        String query="select nationalcode from shareholder where nationalcode=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,nationalCode);
        ResultSet resultSet =preparedStatement.executeQuery();

        return resultSet.next();
    }
}
