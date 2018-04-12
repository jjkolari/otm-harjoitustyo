
package opintoapp.ui;

import org.mindrot.jbcrypt.BCrypt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
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
    private PasswordField password;
    
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
        if(username.getText().length() >= 3 && password.getText().length() >= 3){
            actiontarget.setText("User " + username.getText() + " created");
            String hashed = BCrypt.hashpw(password.getText(), BCrypt.gensalt());
            service.createUser(username.getText(), name.getText(), hashed);
            this.username.setText("");
            this.name.setText("");
            this.password.setText("");
            
        } else {
            actiontarget.setText("Username and password must contain at least 3 characters");
        }
    }
    
    @FXML
    public void handleGoBack(ActionEvent event){
        actiontarget.setText("");
        this.application.setLoginScene();
    }
    
}
