package rmit.dataanalyticshub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateAccountModel {

    public boolean insertUser(User user) throws SQLException {
        //Get user variables ready to inject
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String password = user.getPassword();
        Boolean VIP =  user.isVIP();
        //Prepare SQL query
        String sql = "INSERT INTO users (id, password, firstName, lastName, VIP)" +
                " VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = SqliteConnection.Connector();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);){
            //ID set to auto-increment in sqlite
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstname);
            preparedStatement.setString(4, lastname);
            preparedStatement.setBoolean(5, VIP);

            int result = preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex){
            return false;
        }
    }
}
