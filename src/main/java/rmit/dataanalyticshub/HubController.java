package rmit.dataanalyticshub;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

public class HubController {
    public HubModel hubModel = new HubModel();

    @FXML
    private Label lblWelcome;
    @FXML
    private Pane paneProfile;
    @FXML
    private Pane panePost;

    public void setUser(User user){
        //Hide panes
        paneProfile.setVisible(false);
        panePost.setVisible(false);
        //display current user
        lblWelcome.setText("Welcome back, " +
                user.getFirstName() + "!");
    }
}
