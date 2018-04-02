
package opintoapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import opintoapp.domain.StudyService;

public class LoginSceneController {
    
    @FXML private Text actiontarget;
    private StudyService service;
    private OpintoAppMain application;
    
    public void setService(StudyService service){
        this.service = service;
    }
    
    public void setApplication(OpintoAppMain application){
        this.application = application;
    }
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }
    
    @FXML
    public void handleNewUser(ActionEvent event){
        this.application.setNewUserScene();
    }
}