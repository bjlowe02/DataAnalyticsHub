package rmit.dataanalyticshub.login;

import rmit.dataanalyticshub.SqliteConnection;
import rmit.dataanalyticshub.User;

import java.sql.*;

public class LoginModel {
    Connection connection;

    public LoginModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    public boolean isLogin(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * " +
                "FROM users " +
                "WHERE username = ? and Password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            //return true if credentials match / otherwise false
            return resultSet.next();
        } catch (Exception e) {
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public User getUserFromUsername(String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * " +
                "FROM users " +
                "WHERE username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            String firstName = "";
            String lastName = "";
            boolean VIP = false;

            while(resultSet.next()) {
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                VIP = resultSet.getBoolean("VIP");
            }
            return new User(username, firstName, lastName, VIP);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}

