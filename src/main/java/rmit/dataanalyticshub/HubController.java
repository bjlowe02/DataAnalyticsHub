package rmit.dataanalyticshub;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class HubController {
    public HubModel hubModel = new HubModel();

    @FXML
    private Label txtCurrentUser;

    public void setUser(User user){
        //display current user
        txtCurrentUser.setText(user.getFullName());
    }
}
