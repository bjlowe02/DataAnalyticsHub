package rmit.dataanalyticshub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
                    //TODO create and open main form
                    System.out.println("Success!");
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
            }
        }
    }
}