package rmit.dataanalyticshub;

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

public class LoginController {
    public LoginModel loginModel = new LoginModel();

    @FXML
    private TextField txtID;
    @FXML
    private PasswordField txtPassword;
    @FXML
    public Button btnLogin;

    @FXML
    protected void onBtnLoginAction(ActionEvent event){
        //handle empty input(s)
        if (txtID.getText().isEmpty() || txtPassword.getText().isEmpty()){
            //warning message
            JOptionPane.showMessageDialog(null,
                    "ID and/or Password cannot be empty!\n" +
                            "Please try again!",
                    "Bad Input!", JOptionPane.WARNING_MESSAGE);
            //clear input fields
            txtID.clear();
            txtPassword.clear();
        } else { //good input will continue to check against db
            try {
                if (loginModel.isLogin(txtID.getText(), txtPassword.getText())){
                    //TODO store user details

                    //close current form
                    ((Node)event.getSource()).getScene().getWindow().hide();
                    //open next from with logged-in user
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Hub.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image("file:src/icon.png"));
                    stage.setTitle("Data Analytics Hub | Login");
                    stage.setScene(scene);
                    stage.show();


                } else {
                    //warning message
                    JOptionPane.showMessageDialog(null,
                            "ID and/or Password are incorrect!\n" +
                                    "Please try again!",
                            "Login Failed!", JOptionPane.INFORMATION_MESSAGE);
                    //clear input fields
                    txtID.clear();
                    txtPassword.clear();
                }
            } catch (SQLException e) {
                System.out.println(e);
            } catch (IOException e) {
                //System.out.println(e);
            }
        }
    }
}