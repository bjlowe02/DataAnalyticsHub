package rmit.dataanalyticshub;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HubController {
    public HubModel hubModel = new HubModel();
    private User currentUser;

    //FXML variables
    @FXML
    private Label lblWelcome;
    @FXML
    private Pane paneProfile;
    @FXML
    private Pane panePost;
    @FXML
    private Pane paneWelcome;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnPost;
    @FXML
    private Label lblFName;
    @FXML
    private Label lblLName;
    @FXML
    private Label lblID;
    @FXML
    private Label lblVIP;

    public void setUser(User user){
        //sets current user
        this.currentUser = user;
        //Prepares window and greets user with welcome message
        //Hide panes
        paneProfile.setVisible(false);
        panePost.setVisible(false);
        //display current user
        lblWelcome.setText("Welcome back, " +
                user.getFirstName() + "!");
    }

    @FXML
    protected void onBtnProfileClick(javafx.scene.input.MouseEvent mouseEvent) {
        //puts stores user details into GUI
/*        lblFName.setText(currentUser.getFirstName());
        lblLName.setText(currentUser.getLastName());
        lblID.setText(String.valueOf(currentUser.getID()));
        lblVIP.setText(String.valueOf(currentUser.isVIP()));*/
        //hide other panes
        paneWelcome.setVisible(false);
        panePost.setVisible(false);
        //show profile pane
        paneProfile.setVisible(true);
    }
}
