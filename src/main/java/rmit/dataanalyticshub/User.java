package rmit.dataanalyticshub;

public class User {
    private int ID;
    private String firstName;
    private String lastName;

    public User(int ID, String firstName, String lastName) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
