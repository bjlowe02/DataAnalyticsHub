package rmit.dataanalyticshub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

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

    @FXML
    protected void onBtnCreateAccountAction(ActionEvent event){
        if (!txtFName.getText().isEmpty() ||
                !txtLName.getText().isEmpty() ||
                !txtPassword.getText().isEmpty() ||
                !txtRepeatPassword.getText().isEmpty()){

        }
    }
}
