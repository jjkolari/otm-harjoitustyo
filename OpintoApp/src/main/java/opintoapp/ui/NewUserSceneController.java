package opintoapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import opintoapp.domain.StudyService;

public class NewUserSceneController extends UiController {

    @FXML
    private Text actiontarget;

    @FXML
    private TextField username;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    public void handleSubmitButtonAction(ActionEvent event) {
        if (username.getText().length() >= 3 && password.getText().length() >= 3) {
            actiontarget.setText("User " + username.getText() + " created");

            this.service.createUser(username.getText(), name.getText(), password.getText());
            this.username.setText("");
            this.name.setText("");
            this.password.setText("");

        } else {
            actiontarget.setText("Username and password must contain at least 3 characters");
        }
    }

    @FXML
    public void handleGoBack(ActionEvent event) {
        actiontarget.setText("");
        this.application.setLoginScene();
    }

}
