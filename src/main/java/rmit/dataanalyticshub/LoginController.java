package rmit.dataanalyticshub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    public LoginModel loginModel = new LoginModel();

    @FXML
    private TextField txtID;
    @FXML
    private PasswordField txtPassword;

    @FXML
    protected void onBtnLoginAction(ActionEvent event){

    }
}