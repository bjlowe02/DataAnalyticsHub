package login.newAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountController {
    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRepeatPassword;
    @FXML
    private Button btnCreateAccount;

    CreateAccountModel model = new CreateAccountModel();

    @FXML
    protected void onBtnCreateAccountAction(ActionEvent event) {
        if (!txtFName.getText().isEmpty() ||
                !txtLName.getText().isEmpty() ||
                !txtPassword.getText().isEmpty() ||
                !txtRepeatPassword.getText().isEmpty()){

            if (txtPassword.getText().equals(txtRepeatPassword.getText())){
                //Gather input
                String firstname = txtFName.getText();
                String lastname = txtLName.getText();
                String password = txtPassword.getText();
                //New user is created without ID and will be added via auto-increment in db
                User newAcc = new User(firstname,lastname,password);
                //add user to sqlite
                try {
                    if (model.insertUser(newAcc)){
                        //Display new login details
                        //Gets ID from db auto-increment and password which is inputted earlier
                        JOptionPane.showMessageDialog(null,
                                "Account created!\n" +
                                        "Please store this details to log-in.\n" +
                                        "ID: " + model.getLastInsertedID() + "\n" +
                                        "Password: " + password,
                                "Success!", JOptionPane.PLAIN_MESSAGE);
                        //Open last form
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
                            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                            Stage stage = new Stage();
                            stage.getIcons().add(new Image("file:src/icon.png"));
                            stage.setTitle("Data Analytics Hub | Login");
                            stage.setScene(scene);
                            stage.show();
                        //close current form
                        ((Node)event.getSource()).getScene().getWindow().hide();
                    } else {
                        //warning message: SQL insert error
                        JOptionPane.showMessageDialog(null,
                                "There was an issue adding new account to database!\n" +
                                        "Please try again!",
                                "Account Error!", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                //warning message: passwords don't match
                JOptionPane.showMessageDialog(null,
                        "Passwords do not match!\n" +
                                "Please try again!",
                        "Password Error!", JOptionPane.WARNING_MESSAGE);
                //clear input fields
                txtFName.clear();
                txtLName.clear();
                txtPassword.clear();
                txtRepeatPassword.clear();
                }
        } else {
            //warning message: empty field(s)
            JOptionPane.showMessageDialog(null,
                    "One or more fields were empty!\n" +
                            "Please complete details and try again!",
                    "Empty Field(s)!", JOptionPane.WARNING_MESSAGE);
        }
    }
}
