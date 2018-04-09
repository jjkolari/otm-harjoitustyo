
package opintoapp.ui;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import opintoapp.domain.StudyService;

public class WelcomeSceneController {
    
    @FXML private Text actionTarget;
    private StudyService service;
    private OpintoAppMain application;
    
    public void setApplication(OpintoAppMain application) {
        this.application = application;
    }

    public void setService(StudyService service) {
        this.service = service;
    }
    
    public void setActionTarget(){
        String username = this.service.getLoggedIn().getUsername();
        this.actionTarget.setText("Welcome to OpintoApp " + username);
    }
    
    public void handleLogOut(){
        this.application.setLoginScene();
    }
    
}
