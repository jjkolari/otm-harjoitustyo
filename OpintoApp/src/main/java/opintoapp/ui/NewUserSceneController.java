
package opintoapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import opintoapp.domain.StudyService;

public class NewUserSceneController {
    
    @FXML private Text actiontarget;
    private OpintoAppMain application;
    private StudyService service;
    
    
    public void setApplication(OpintoAppMain application){
        this.application = application;
    }
    
    public void setService(StudyService service){
        this.service = service;
    }
    
    @FXML 
    public void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign up button pressed");
    }
    
    @FXML
    public void handleGoBack(ActionEvent event){
        this.application.setLoginScene();
    }
    
}
