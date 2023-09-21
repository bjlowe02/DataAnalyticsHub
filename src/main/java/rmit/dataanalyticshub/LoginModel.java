package rmit.dataanalyticshub;

import java.sql.*;

public class LoginModel {
    Connection connection;
    public LoginModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }
}
