
package opintoapp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import opintoapp.dao.Database;
import opintoapp.domain.StudyService;

public class OpintoAppMain extends Application{
    
    private final double height = 600;
    private final double width = 550;
    private StudyService studyService;
    private Database database;
    private Scene loginScene;
    private Scene newUserScene;
    private Scene welcomeScene;
    private WelcomeSceneController welcomescenecontroller;
    private Stage stage;

    @Override
    public void init() throws Exception {
        this.database = new Database("jdbc:sqlite:./db/OpintoApp.db");
        this.studyService = new StudyService(this.database);
        
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent login = loginLoader.load();
        LoginSceneController loginscenecontroller = loginLoader.getController();
        loginscenecontroller.setService(this.studyService);
        loginscenecontroller.setApplication(this);
        this.loginScene = new Scene(login, height, width);
        
        FXMLLoader newUserLoader = new FXMLLoader(getClass().getResource("/fxml/NewUser.fxml"));
        Parent newUser = newUserLoader.load();
        NewUserSceneController newusercontroller = newUserLoader.getController();
        newusercontroller.setService(studyService);
        newusercontroller.setApplication(this);
        this.newUserScene = new Scene(newUser, height, width);
        
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("/fxml/Welcome.fxml"));
        Parent welcome = welcomeLoader.load();
        welcomescenecontroller = welcomeLoader.getController();
        welcomescenecontroller.setService(studyService);
        welcomescenecontroller.setApplication(this);
        this.welcomeScene = new Scene(welcome, height, width);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("OpintoApp");
        setLoginScene();
        stage.show();
    }
    
    public void setLoginScene(){
        this.stage.setScene(loginScene);
    }
    
    public void setNewUserScene(){
        this.stage.setScene(newUserScene);
    }
    
    public void setWelcomeScene(){
        this.stage.setScene(welcomeScene);
        this.welcomescenecontroller.setActionTarget();
    }
    
    public static void main(String[] args) {
        
        launch(args);
        
        
    }
    
    
    
    
}
