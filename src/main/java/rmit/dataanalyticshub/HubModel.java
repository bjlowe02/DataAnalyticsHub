package rmit.dataanalyticshub;

import java.sql.*;

public class HubModel {
    static Connection connection;
    public HubModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }
}
