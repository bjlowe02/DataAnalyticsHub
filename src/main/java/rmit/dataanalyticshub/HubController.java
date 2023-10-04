package rmit.dataanalyticshub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;

public class HubController {
    public HubModel hubModel = new HubModel();

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
    @FXML
    private DatePicker datePicker;
    @FXML
    private Spinner<Integer> spinHour;
    @FXML
    private Spinner<Integer> spinMinute;

    public void setCurrentUser(User user){
        paneWelcome.setVisible(true);
        paneWelcome.requestFocus();
        //puts stores user details into GUI
        lblFName.setText(user.getFirstName());
        lblLName.setText(user.getLastName());
        lblID.setText(String.valueOf(user.getID()));
        lblVIP.setText(String.valueOf(user.isVIP()));
        //welcome message
        lblWelcome.setText("Welcome Back, " +
                user.getFirstName() + "!");
    }
    @FXML
    protected void onBtnAction(ActionEvent event) { //show/hide relevant panes on different button clicks
        //always hide welcome pane as it will not be accessed again
        paneWelcome.setVisible(false);
        //on Profile click
        if (event.getSource() == btnProfile){
            //show/hide panes
            panePost.setVisible(false);
            paneProfile.setVisible(true);
            //set button effect
            btnProfile.setStyle("-fx-background-color: white;" +
                    "-fx-text-fill: #6F5CC2;");
            btnPost.setStyle("-fx-background-color: #6F5CC2;" +
                    "-fx-text-fill: white;");
        } else if (event.getSource() == btnPost) {
            //show/hide panels
            paneProfile.setVisible(false);
            panePost.setVisible(true);
            //set button effect
            btnPost.setStyle("-fx-background-color: white;" +
                    "-fx-text-fill: #6F5CC2;");
            btnProfile.setStyle("-fx-background-color: #6F5CC2;" +
                    "-fx-text-fill: white;");
        }
    }
}
