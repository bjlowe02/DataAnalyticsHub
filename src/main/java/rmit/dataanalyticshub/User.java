package rmit.dataanalyticshub;

public class User {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private boolean VIP;

    public User(String username, String firstName, String lastname, String password){
        this.username = username;
        this.firstname = firstName;
        this.lastname = lastname;
        this.password = password;
        this.VIP = false;
    }
    public User(String username, String firstName, String lastName, Boolean VIP) {
        this.username = username;
        this.firstname = firstName;
        this.lastname = lastName;
        this.VIP = VIP;
    }
    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstname = firstName;
        this.lastname = lastName;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isVIP() {
        return VIP;
    }
}
