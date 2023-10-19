package rmit.dataanalyticshub.hub;

import rmit.dataanalyticshub.*;
import javafx.event.*;
import javafx.fxml.*;
import java.util.*;
import javafx.stage.*;
import java.io.*;
import javax.swing.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class HubController implements Initializable{
    public HubModel hubModel = new HubModel();
    private User currentUser;

    //FXML variables
    //Welcome pane
    @FXML
    private Label lblWelcome;
    @FXML
    private Pane paneWelcome;
    @FXML
    private Pane paneVIP;
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
    private Label lblPreviewDate;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Spinner<Integer> spinHour;
    @FXML
    private Spinner<Integer> spinMinute;
    @FXML
    private Button btnAddPost;
    //Post add
    //Post retrieve
    @FXML
    private TextField txtPostID;
    @FXML
    private TextField txtNoPostsRetrieve;
    @FXML
    private TableView<Post> table;
        @FXML private TableColumn<Post, Integer> colId;
        @FXML private TableColumn<Post, String> colContent;
        @FXML private TableColumn<Post, String> colAuthor;
        @FXML private TableColumn<Post, Integer> colLikes;
        @FXML private TableColumn<Post, Integer> colShares;
        @FXML private TableColumn<Post, String> colDate_time;
    @FXML
    private Button btnRetrieveViaID;
    @FXML
    private Button btnRetrieveViaLikes;
    @FXML
    private Button btnRetrieveViaShares;
    //Post retrieve
    //Post remove
    @FXML
    private Button btnRemovePost;
    @FXML
    private TextField txtRemovePostID;
    //Post remove
    //Post export
    @FXML
    private TextField txtExportPostID;
    //Post export
    //Post import
    //Post import
    //Visualise data
    @FXML
    private Tab visualiseData;
    //Visualise data

    public void setCurrentUser(User user) {
        this.currentUser = user;
        //puts stores user details into GUI
        lblFName.setText(user.getFirstname());
        lblLName.setText(user.getLastname());
        lblID.setText(String.valueOf(user.getUsername()));
        lblVIP.setText(String.valueOf(user.isVIP()));
        //welcome message
        lblWelcome.setText("Welcome Back, " +
                user.getFirstname() + "!");
        if (user.isVIP()){
            //Hide prompt to promote to VIP
            paneVIP.setVisible(false);
        } else {
            //disable VIP functions
            visualiseData.setDisable(true);
        }
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Show welcome
        paneWelcome.setVisible(true);
        paneWelcome.requestFocus();
        //Set columns
        colId.setCellValueFactory(new PropertyValueFactory<Post, Integer>("postID"));
        colContent.setCellValueFactory(new PropertyValueFactory<Post, String>("content"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Post, String>("author"));
        colLikes.setCellValueFactory(new PropertyValueFactory<Post, Integer>("likes"));
        colShares.setCellValueFactory(new PropertyValueFactory<Post, Integer>("shares"));
        colDate_time.setCellValueFactory(new PropertyValueFactory<Post, String>("date_time"));
        //get posts and display
        try {
            table.setItems(hubModel.loadDataFromDatabase());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    protected void onRetrieveBtnAction(ActionEvent event) throws SQLException {
        //Each button will use the same method but with different overrides
        if (event.getSource() == btnRetrieveViaID) { //get post by ID
            if (!txtPostID.getText().isEmpty()){
                int ID = Integer.parseInt(txtPostID.getText());
                table.setItems(hubModel.loadDataFromDatabase(ID));
                //clear other unused field
                txtNoPostsRetrieve.clear();
            } //else do nothing
        } else if (event.getSource() == btnRetrieveViaLikes) { //sort by likes, N posts
            if (!txtNoPostsRetrieve.getText().isEmpty()) {
                int noPosts = Integer.parseInt(txtNoPostsRetrieve.getText());
                table.setItems(hubModel.loadDataFromDatabase(noPosts, true));
                //clear other unused field
                txtPostID.clear();
            } //else do nothing
        } else if (event.getSource() == btnRetrieveViaShares) { //sort by shares, N posts
            if (!txtNoPostsRetrieve.getText().isEmpty()) {
                int noPosts = Integer.parseInt(txtNoPostsRetrieve.getText());
                table.setItems(hubModel.loadDataFromDatabase(noPosts, false));
                //clear other unused field
                txtPostID.clear();
            } //else do nothing
        }
    }
    @FXML
    protected void onBtnRemovePostAction(ActionEvent event){
        if (!txtRemovePostID.getText().isEmpty()){
            int ID = Integer.parseInt(txtRemovePostID.getText());
            if (hubModel.doesIdExist(ID)){
                if (hubModel.removePost(ID)){
                    //updates posts table with removed post
                    try {
                        table.setItems(hubModel.loadDataFromDatabase());
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    //success message
                    JOptionPane.showMessageDialog(null,
                            "Post removed successfully from collection!",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            //warning message: empty input
            JOptionPane.showMessageDialog(null,
                    "Field cannot be empty!\n" +
                            "Please try again!",
                    "Input Empty!", JOptionPane.WARNING_MESSAGE);
        }
    }
    @FXML
    protected void onBtnExportPostAction(ActionEvent event){
        if (!txtExportPostID.getText().isEmpty()){
            int ID = Integer.parseInt(txtExportPostID.getText());
            if (hubModel.doesIdExist(ID)){
                try {
                    //get post
                    String content = convertToCSV(hubModel.getPost(
                            Integer.parseInt(txtExportPostID.getText())));

                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV (Comma delimited)", "*.csv"));

                    File file = fileChooser.showSaveDialog(new Stage());
                    if  (file != null) {
                        if (!file.getName().contains(".")) {
                            file = new File(file.getAbsolutePath() + ".txt");
                        }
                        saveFile(file, content);
                        //success message
                        JOptionPane.showMessageDialog(null,
                                "Post has been successfully Exported!",
                                "Success!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    //clear input
                    txtExportPostID.clear();
                } catch (Exception e){
                    System.out.println(e.getMessage());
                    //warning message: Export failed
                    JOptionPane.showMessageDialog(null,
                            "The post could not be exported!\n" +
                                    "Please try again!",
                            "Export failure!", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                //warning message: ID doesn't exist
                JOptionPane.showMessageDialog(null,
                        "This post ID does not exist!\n" +
                                "Please try again with a valid ID!",
                        "Invalid ID!", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            //warning message: empty input
            JOptionPane.showMessageDialog(null,
                    "Field cannot be empty!\n" +
                            "Please try again!",
                    "Input Empty!", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void saveFile(File file, String content){
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(content);
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private static String convertToCSV(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<Field> fieldList = Arrays.asList(fields);

        StringBuilder csvData = new StringBuilder();

        // Append headers
        fieldList.forEach(field -> csvData.append(field.getName()).append(","));

        csvData.deleteCharAt(csvData.length() - 1);
        csvData.append("\n");

        // Append values
        fieldList.forEach(field -> {
            field.setAccessible(true);
            try {
                csvData.append(field.get(object)).append(",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        csvData.deleteCharAt(csvData.length() - 1);

        return csvData.toString();
    }
    @FXML
    protected void onMenuBtnAction(ActionEvent event) { //show/hide relevant panes on different button clicks
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
        txtAddPostID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtAddPostID.setText(newValue.replaceAll("\\D", ""));
            }
        });
        //No. of likes text field
        txtAddLikes.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtAddLikes.setText(newValue.replaceAll("\\D", ""));
            }
        });
        //No. of shares text field
        txtAddShares.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtAddShares.setText(newValue.replaceAll("\\D", ""));
            }
        });
        //Retrieve post ID text field
        txtPostID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPostID.setText(newValue.replaceAll("\\D", ""));
            }
        });
        //Retrieve No. Posts to retrieve text field
        txtNoPostsRetrieve.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtNoPostsRetrieve.setText(newValue.replaceAll("\\D", ""));
            }
        });
        //Remove post via ID text field
        txtRemovePostID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtRemovePostID.setText(newValue.replaceAll("\\D", ""));
            }
        });
        //Remove post via ID text field
        txtExportPostID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtExportPostID.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }
    @FXML
    protected void onBtnAddAction(ActionEvent event) {
        //Check fields are not empty
        if (!txtAddPostID.getText().isEmpty() ||
                !txtAddContent.getText().isEmpty() ||
                !txtAddAuthor.getText().isEmpty() ||
                !txtAddLikes.getText().isEmpty() ||
                !txtAddShares.getText().isEmpty() ||
                !datePicker.getValue().toString().isEmpty())

            try {
                int postID = Integer.parseInt(txtAddPostID.getText());
                String content = txtAddContent.getText();
                String author = txtAddAuthor.getText();
                int likes = Integer.parseInt(txtAddLikes.getText());
                int shares = Integer.parseInt(txtAddShares.getText());
                String date_Time = lblPreviewDate.getText();
                if (!hubModel.doesIdExist(postID)){
                    //create new post
                    Post post = new Post(postID,content,author,likes,shares,date_Time);
                    if (hubModel.insertPost(post)){
                        //Confirmation message
                        JOptionPane.showMessageDialog(null,
                                "Post successfully added to the database!",
                                "Post success!", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        //warning message: SQL insert error
                        JOptionPane.showMessageDialog(null,
                                "There was an issue adding new post to database!\n" +
                                        "Please try again!",
                                "Post Error!", JOptionPane.WARNING_MESSAGE);
                        }
                } else {
                    //warning message: ID already exists
                    JOptionPane.showMessageDialog(null,
                            "A post with this ID already exists in the collection!\n" +
                                    "Please try again with a different ID!",
                            "Post Error!", JOptionPane.WARNING_MESSAGE);
                }

            } catch (Exception e) {
                //warning message: empty input
                JOptionPane.showMessageDialog(null,
                        "Field cannot be empty!\n" +
                                "Please try again!",
                        "Input Empty!", JOptionPane.WARNING_MESSAGE);
            } finally {
                //Clear input
                txtAddPostID.clear();
                txtAddContent.clear();
                txtAddAuthor.clear();
                txtAddLikes.clear();
                txtAddShares.clear();
                datePicker.getEditor().clear();
                spinHour.getValueFactory().setValue(0);
                spinMinute.getValueFactory().setValue(0);
                lblPreviewDate.setText("00/00/0000 00:00");
            }
    }
    @FXML
    protected void onLnkVipPressed(ActionEvent event){
        if (hubModel.setUserVIP(currentUser.getUsername())){
            //Confirmation message
            JOptionPane.showMessageDialog(null,
                    "You are now a VIP member!\n" +
                            "Please log out and back in for this to take affect.",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //warning message: sql error
            JOptionPane.showMessageDialog(null,
                    "Something went wrong updating you VIP status!\n" +
                            "Please try again!",
                    "Database error!", JOptionPane.WARNING_MESSAGE);
        }
    }
    @FXML
    protected void onDateChanged(ActionEvent event){
        displayDateChange();
    }
    @FXML
    protected void onTimeChanged(Event event){
        displayDateChange();
    }
    //The above two events are responsible for getting datePicker and hour/minute Spinner changes, respectively.
    //'onDateChange' gets date picker changes and 'onTimeChange' is responsible for both key and mouse events of spinners.
    //They of course trigger the same method below
    private void displayDateChange(){
        //Format date time and display in preview label
        LocalDate date = datePicker.getValue();
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String hour = String.format("%02d", spinHour.getValue());
        String minute = String.format("%02d", spinMinute.getValue());
        String date_time = DTF.format(date) + " " +
                hour + ":" + minute;
        lblPreviewDate.setText(date_time);
    }
    @FXML
    protected void onBtnEditProfileAction(ActionEvent event) {
        //TODO allow user to edit profile details
    }
}
