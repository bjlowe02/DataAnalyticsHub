package rmit.dataanalyticshub.hub;

import org.junit.jupiter.api.Test;
import rmit.dataanalyticshub.Post;

import static org.junit.jupiter.api.Assertions.*;

class HubModelTest {

    @Test
    void insertPost() {
        HubModel model = new HubModel();
        //Will return false as post with that ID already exists
        assertFalse(model.insertPost(new Post(1,"First Post",
                "Brodie",99,24,"20/10/2023 18:53")));
    }

    @Test
    void doesIdExist() {
        HubModel model = new HubModel();
        //Return true as ID does exist in db
        assertTrue(model.doesIdExist(1));
    }

    @Test
    void doesUsernameExist() {
        HubModel model = new HubModel();
        //Return true as user is already taken
        assertTrue(model.doesUsernameExist("admin"));
    }
}