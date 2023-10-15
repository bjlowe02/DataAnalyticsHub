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
}