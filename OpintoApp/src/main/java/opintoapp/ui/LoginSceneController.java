
package opintoapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import opintoapp.domain.StudyService;

public class LoginSceneController {
    
    @FXML private Text actiontarget;
    @FXML private TextField username;
    @FXML private PasswordField password;
    private StudyService service;
    private OpintoAppMain application;
    
    public void setService(StudyService service){
        this.service = service;
    }
    
    public void setApplication(OpintoAppMain application){
        this.application = application;
    }
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        boolean logInSuccess = this.service.logIn(username.getText(), password.getText());
        if(logInSuccess){
            username.setText("");
            password.setText("");
            actiontarget.setText("");
            this.application.setWelcomeScene();
        } else {
            actiontarget.setText("Unknown user");
        }
    }
    
    @FXML
    public void handleNewUser(ActionEvent event){
        actiontarget.setText("");
        username.setText("");
        password.setText("");
        this.application.setNewUserScene();
    }
}
