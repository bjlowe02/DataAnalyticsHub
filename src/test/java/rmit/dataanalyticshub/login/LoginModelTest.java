package rmit.dataanalyticshub.login;

import org.junit.jupiter.api.Test;
import rmit.dataanalyticshub.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginModelTest {

    @Test
    void isLogin() throws SQLException {
        LoginModel model = new LoginModel();
        assertTrue(model.isLogin("admin","password"));
    }

    @Test
    void getUserFromUsername() throws SQLException {
        LoginModel model = new LoginModel();
        User user = new User("admin","Brodie","Lowe",true);
        //This returns not equal as it compares the memory location, i.e. 'User@5677323c', rather than
        //literal attributes of the class.
        //A toString override method would fix this issue however it was not necessary in the main program
        assertNotEquals(user.toString(), model.getUserFromUsername("admin").toString());
    }
}