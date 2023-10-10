package rmit.dataanalyticshub;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HubController {
    public HubModel hubModel = new HubModel();

    //FXML variables
    //Welcome pane
    @FXML
    private Label lblWelcome;
    @FXML
    private Pane paneWelcome;
    //Welcome pane
    //Navigation buttons
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnPost;
    //Navigation buttons
    //Profile
    @FXML
    private Pane paneProfile;
    @FXML
    private Label lblFName;
    @FXML
    private Label lblLName;
    @FXML
    private Label lblID;
    @FXML
    private Label lblVIP;
    @FXML
    private Button btnEditProfile;
    @FXML
    private FlowPane paneDetails;
    //Profile
    //Post add
    @FXML
    private Pane panePost;
    @FXML
    private TextField txtAddPostID;
    @FXML
    private TextField txtAddContent;
    @FXML
    private TextField txtAddAuthor;
    @FXML
    private TextField txtAddLikes;
    @FXML
    private TextField txtAddShares;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Spinner<Integer> spinHour;
    @FXML
    private Spinner<Integer> spinMinute;
    @FXML
    private Button btnAddPost;
    //Post add

    public void setCurrentUser(User user) {
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

        //TODO if not VIP hide special functions
    }

    @FXML
    protected void onBtnAction(ActionEvent event) { //show/hide relevant panes on different button clicks
        //always hide welcome pane as it will not be accessed again
        paneWelcome.setVisible(false);

        if (event.getSource() == btnProfile) {   //on Profile click
            //show/hide panes
            panePost.setVisible(false);
            paneProfile.setVisible(true);
            //set button effect
            btnProfile.setStyle("-fx-background-color: white;" +
                    "-fx-text-fill: #6F5CC2;");
            btnPost.setStyle("-fx-background-color: #6F5CC2;" +
                    "-fx-text-fill: white;");
        } else if (event.getSource() == btnPost) {  //on Post click
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

    @FXML
    protected void onKeyPressed(KeyEvent event) {
        // force the field to be numeric only
        //ID text field
        txtAddPostID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtAddPostID.setText(newValue.replaceAll("\\D", ""));
                }
            }
        });
        //No. of likes text field
        txtAddLikes.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtAddLikes.setText(newValue.replaceAll("\\D", ""));
                }
            }
        });
        //No. of shares text field
        txtAddShares.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtAddShares.setText(newValue.replaceAll("\\D", ""));
                }
            }
        });
    }

    @FXML
    protected void onBtnAddAction(ActionEvent event) {
        try {
            int ID = Integer.parseInt(txtAddPostID.getText());
            String content = txtAddContent.getText();
            String author = txtAddAuthor.getText();
            int likes = Integer.parseInt(txtAddLikes.getText());
            int shares = Integer.parseInt(txtAddShares.getText());
            String date = datePicker.getValue().toString();
            System.out.println(date);
        } catch (Exception ex) {

        }
    }

    @FXML
    protected void onBtnEditProfileAction(ActionEvent event) {
/*        int currentPos = 0;
        Map<Integer, TextField> toInsert = new HashMap<>(); // map with TextFields that need to be inserted at position
        for (Iterator<Node> iterator = paneDetails.getChildren().iterator(); iterator.hasNext(); ) {
            Node child = iterator.next();
            if (child instanceof Label) {
                    Label lbl = (Label)child;
                    TextField text = new TextField(lbl.getText());
                    iterator.remove(); // remove the label that is at index currentPos
                    toInsert.put(currentPos, text);
            }
            currentPos++;
        }
        for (Integer pos : toInsert.keySet()) {
            TextField field = toInsert.get(pos);
            paneDetails.getChildren().add(pos, field); // Add the Text field at the old position of the Label
        }*/
    }
}
