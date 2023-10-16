package rmit.dataanalyticshub.hub;

import rmit.dataanalyticshub.Post;
import rmit.dataanalyticshub.SqliteConnection;

import java.sql.*;

public class HubModel {

    public boolean insertPost(Post post){
        //Prepare SQL query
        String sql = "INSERT INTO posts (postID, content, author, likes, shares, date_time) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = SqliteConnection.Connector();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);){
            preparedStatement.setInt(1, post.getPostID());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getAuthor());
            preparedStatement.setInt(4, post.getLikes());
            preparedStatement.setInt(5, post.getShares());
            preparedStatement.setString(6, post.getDate_time());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean doesIdExist(int ID){
        //Prepare SQL query
        String query = "SELECT postID " +
                "FROM posts " +
                "WHERE postID = " + ID;
        try (Connection conn = SqliteConnection.Connector();
            Statement stmt = conn.createStatement();){

            ResultSet resultSet = stmt.executeQuery(query);
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void loadDataFromDatabase(){
        //Prepare SQL query
        String query = "SELECT * " +
                "FROM posts ";
        //TODO get parent controller rather than new or change data retrieve method
        HubController hubController = new HubController();
        try (Connection conn = SqliteConnection.Connector();
            Statement statement = conn.createStatement();){

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                hubController.listPost.add(new Post(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getInt(5),
                        rs.getString(6)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}