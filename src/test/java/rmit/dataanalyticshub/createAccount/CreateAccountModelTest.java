package rmit.dataanalyticshub.createAccount;

import org.junit.jupiter.api.Test;
import rmit.dataanalyticshub.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountModelTest {

    @Test
    void insertUser() throws SQLException {
        CreateAccountModel model = new CreateAccountModel();
        //User with username bjlowe already exists and will return false
        assertFalse(model.insertUser(new User("bjlowe","Brodie","Lowe","password")));
    }
    @Test
    void doesUsernameExist(){
        CreateAccountModel model = new CreateAccountModel();
        assertTrue(model.doesUsernameExist("bjlowe"));
    }
}