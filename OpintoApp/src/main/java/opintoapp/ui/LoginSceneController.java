package opintoapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import opintoapp.domain.StudyService;

/**
 * Log in -näkymän kontrolleriluokka.
 * 
 */
public class LoginSceneController extends UiController {

    @FXML
    private Text actiontarget;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    /**
     * Log in -napin klikkaus
     * @param event 
     */
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        boolean logInSuccess = this.service.logIn(username.getText(), password.getText());
        if (logInSuccess) {
            username.setText("");
            password.setText("");
            actiontarget.setText("");
            this.application.setWelcomeScene();
        } else {
            actiontarget.setText("Unknown user");
        }
    }

    /**
     * new user -napin klikkaus.
     * @param event 
     */
    @FXML
    public void handleNewUser(ActionEvent event) {
        actiontarget.setText("");
        username.setText("");
        password.setText("");
        this.application.setNewUserScene();
    }
}
