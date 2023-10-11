package rmit.dataanalyticshub;

import java.sql.*;

public class LoginModel {
    Connection connection;

    public LoginModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    public boolean isLogin(String ID, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * " +
                "FROM users " +
                "WHERE ID = ? and Password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ID);
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

    public User getUserFromID(int ID) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * " +
                "FROM users " +
                "WHERE ID = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(ID));

            resultSet = preparedStatement.executeQuery();

            String firstName = "";
            String lastName = "";
            boolean VIP = false;

            while(resultSet.next()) {
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                VIP = resultSet.getBoolean("VIP");
            }
            return new User(ID, firstName, lastName, VIP);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}

