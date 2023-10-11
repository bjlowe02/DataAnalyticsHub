package rmit.dataanalyticshub;

public class User {
    private int ID;
    private String firstname;
    private String lastname;
    private String password;
    private boolean VIP;

    public User(String firstName, String lastname, String password){
        this.firstname = firstName;
        this.lastname = lastname;
        this.password = password;
        this.VIP = false;
    }
    public User(int ID, String firstName, String lastName, Boolean VIP) {
        this.ID = ID;
        this.firstname = firstName;
        this.lastname = lastName;
        this.VIP = VIP;
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

    public int getID() {
        return ID;
    }

    public boolean isVIP() {
        return VIP;
    }
}
