package rmit.dataanalyticshub;

public class User {
    private int ID;
    private String firstName;
    private String lastName;
    private boolean VIP;

    public User(int ID, String firstName, String lastName) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.VIP = false;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getID() {
        return ID;
    }

    public boolean isVIP() {
        return VIP;
    }
}
