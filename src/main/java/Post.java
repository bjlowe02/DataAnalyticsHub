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

    public int getPostID() {
        return postID;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public int getLikes() {
        return likes;
    }

    public int getShares() {
        return shares;
    }

    public String getDate_time() {
        return date_time;
    }
}
