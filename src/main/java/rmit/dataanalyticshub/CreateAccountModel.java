package rmit.dataanalyticshub;

import java.sql.*;

public class CreateAccountModel {

    public boolean insertUser(User user) throws SQLException {
        //Get user variables ready to inject
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String password = user.getPassword();
        boolean VIP =  user.isVIP();
        //Prepare SQL query
        String sql = "INSERT INTO users (id, password, firstName, lastName, VIP) " +
                "VALUES (?, ?, ?, ?, ?)";

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

    public int getLastInsertedID(){
        try (Connection conn = SqliteConnection.Connector();
            Statement statement = conn.createStatement()){
            //gets ID from last row in db
            //auto-increment will always create (max + 1),
            //therefore we can get the newest user ID at max
                String query ="SELECT ID " +
                        "FROM users " +
                        "WHERE ROWID " +
                        "IN ( SELECT max( ROWID ) FROM users )";
            try (ResultSet resultset = statement.executeQuery(query)){
                int ID = resultset.getInt("ID");
                return ID;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
