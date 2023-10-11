package rmit.dataanalyticshub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateAccountModel {
    Connection connection;
    public CreateAccountModel(){
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    public boolean insertUser(User user) throws SQLException {
        //Get user variables ready to inject
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String password = user.getPassword();
        //Prepare SQL query
        //TODO create SQL insertion
        return false;
    }
}
