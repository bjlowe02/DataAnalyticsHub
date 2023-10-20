package rmit.dataanalyticshub.hub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rmit.dataanalyticshub.Post;
import rmit.dataanalyticshub.SqliteConnection;
import rmit.dataanalyticshub.User;

import java.sql.*;
import java.util.ArrayList;

public class HubModel {

    public boolean insertPost(Post post) {
        //Prepare SQL query
        String sql = "INSERT INTO posts (postID, content, author, likes, shares, date_time) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = SqliteConnection.Connector();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);) {
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

    public boolean doesIdExist(int ID) {
        //Prepare SQL query
        String query = "SELECT postID " +
                "FROM posts " +
                "WHERE postID = " + ID;
        try (Connection conn = SqliteConnection.Connector();
             Statement stmt = conn.createStatement();) {

            ResultSet resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean doesUsernameExist(String username) {
        //Prepare SQL query
        String query = "SELECT username " +
                "FROM users " +
                "WHERE username = ?";
        try (Connection conn = SqliteConnection.Connector();
             PreparedStatement preparedStatement = conn.prepareStatement(query);) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public void updateUserDetails(User user, String username) {
        //Prepare SQL query
        String sql = "UPDATE users" +
                    " SET username = ?," +
                    " firstname = ?," +
                    " lastname = ?" +
                    " WHERE username = ?";
        try (Connection conn = SqliteConnection.Connector();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public int[] getShareDistribution(){
        int count1 = 0; //0-99
        int count2 = 0; //100-199
        int count3 = 0; //1000+

        //prepare sql query
        String query = "SELECT shares " +
                "FROM posts ";
        try (Connection conn = SqliteConnection.Connector();
        Statement stmt = conn.createStatement()){

            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                int share = resultSet.getInt("shares");
                if (share >= 0 && share <= 100)
                    count1++;
                else if (share >= 100 && share <= 199)
                    count2++;
                else if (share >= 1000)
                    count3++;
            }
            return new int[]{count1,count2,count3};
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ObservableList<Post> loadDataFromDatabase() throws SQLException {
        Connection conn = SqliteConnection.Connector();
        ObservableList<Post> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM posts");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Post(rs.getInt("postID"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getInt("likes"),
                        rs.getInt("shares"),
                        rs.getString("date_time")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            conn.close();
        }
    }

    public ObservableList<Post> loadDataFromDatabase(int postID) throws SQLException {
        Connection conn = SqliteConnection.Connector();
        ObservableList<Post> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM posts WHERE postID = " + postID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Post(rs.getInt("postID"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getInt("likes"),
                        rs.getInt("shares"),
                        rs.getString("date_time")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            conn.close();
        }
    }

    public ObservableList<Post> loadDataFromDatabase(int noPosts, boolean sortByLikes_Shares) throws SQLException {
        Connection conn = SqliteConnection.Connector();
        ObservableList<Post> list = FXCollections.observableArrayList();
        String query;
        //will sort by likes/shares depending on bool
        if (sortByLikes_Shares) { //sort by likes
            query = "SELECT * FROM posts ORDER BY likes DESC LIMIT " + noPosts;
        } else { //sort by shares
            query = "SELECT * FROM posts ORDER BY shares DESC LIMIT " + noPosts;
        }
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Post(rs.getInt("postID"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getInt("likes"),
                        rs.getInt("shares"),
                        rs.getString("date_time")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            conn.close();
        }
    }

    public boolean removePost(int ID) {
        try (Connection conn = SqliteConnection.Connector();
             Statement stmt = conn.createStatement();) {
            String sql = "DELETE FROM posts " +
                    "WHERE postID = " + ID;

            int result = stmt.executeUpdate(sql);
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public Post getPost(int ID){
        //Prepare SQL query
        String query = "SELECT * " +
                "FROM posts " +
                "WHERE postID = " + ID;
        try (Connection conn = SqliteConnection.Connector();
             Statement stmt = conn.createStatement();) {

            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                String content = resultSet.getString(2);
                String author = resultSet.getString(3);
                int likes = resultSet.getInt(4);
                int shares = resultSet.getInt(5);
                String date_time = resultSet.getString(6);
                //return post
                return new Post(ID,content,author,likes,shares,date_time);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean setUserVIP(String username) {
        //Prepare SQL query
        String sql = "UPDATE users" +
                " SET VIP = 1" +
                " WHERE username = ?";
        try (Connection conn = SqliteConnection.Connector();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);) {

            preparedStatement.setString(1, username);
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}