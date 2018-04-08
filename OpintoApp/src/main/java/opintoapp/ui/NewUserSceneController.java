
package opintoapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import opintoapp.domain.StudyService;

public class NewUserSceneController {
    
    @FXML 
    private Text actiontarget;
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField password;
    
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
        if(username.getText().length() > 3 && password.getText().length() > 3){
            service.createUser(name.getText(), username.getText(), password.getText());
        } else {
            actiontarget.setText("Username and password must contain at least 3 characters");
        }
    }
    
    @FXML
    public void handleGoBack(ActionEvent event){
        this.application.setLoginScene();
    }
    
}
