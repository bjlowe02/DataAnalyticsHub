package rmit.dataanalyticshub;

public class Post {
    private int postID;
    private String content;
    private String author;
    private int likes;
    private int shares;
    private String date_time;

    public Post(int postID, String content, String author, int likes, int shares, String date_time){
        this.postID = postID;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.shares = shares;
        this.date_time = date_time;
    }
}
