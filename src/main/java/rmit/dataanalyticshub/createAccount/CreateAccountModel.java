package rmit.dataanalyticshub.createAccount;

import rmit.dataanalyticshub.SqliteConnection;
import rmit.dataanalyticshub.User;

import java.sql.*;

public class CreateAccountModel {

    public boolean insertUser(User user) throws SQLException {
        //Prepare SQL query
        String sql = "INSERT INTO users (username, password, firstName, lastName, VIP) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = SqliteConnection.Connector();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);){
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setBoolean(5, user.isVIP());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean doesUsernameExist(String username){
        //Prepare SQL query
        String sql = "SELECT * " +
                "FROM users " +
                "WHERE username = ?";
        try (Connection conn = SqliteConnection.Connector();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);){
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
