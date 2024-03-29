package opintoapp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import opintoapp.dao.Database;
import opintoapp.domain.StudyService;

/**
 * Käyttöliittymän rakentava ja ohjelman käynnistävä pääluokka.
 * 
 */
public class OpintoAppMain extends Application {

    private final double height = 800;
    private final double width = 750;
    private StudyService studyService;
    private Database database;
    private Scene loginScene;
    private Scene newUserScene;
    private Scene welcomeScene;
    private WelcomeSceneController welcomescenecontroller;
    private Stage stage;

    @Override
    public void init() throws Exception {
        this.database = new Database("jdbc:sqlite:./OpintoApp.db");
        this.studyService = new StudyService(this.database);

        this.loginScene = buildSceneFor("/fxml/Login.fxml");

        this.newUserScene = buildSceneFor("/fxml/NewUser.fxml");

        this.welcomeScene = buildSceneFor("/fxml/Welcome.fxml");
        
        this.welcomeScene.getStylesheets().add(getClass().getResource("/welcome.css").toExternalForm());
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("OpintoApp");
        setLoginScene();
        stage.show();
    }

    /**
     * Asettaa ikkunaan Log in -näkymän.
     */
    public void setLoginScene() {
        this.stage.setScene(loginScene);
    }

    /**
     * Asettaa ikkunaan New User -näkymän.
     */
    public void setNewUserScene() {
        this.stage.setScene(newUserScene);
    }

    /**
     * Asettaa ikkunaan sovelluksen päänäkymän ja alustaa päänäkymään tervetuloviestin, kurssilistan ym.
     */
    public void setWelcomeScene() {
        this.stage.setScene(welcomeScene);
        this.welcomescenecontroller.setActionTarget();
        this.welcomescenecontroller.setCourseList();
        this.welcomescenecontroller.setAverageAndTotal();
    }

    /**
     * Rakentaa Scene-olion eli näkymän FXML-tiedostolle.
     * @param pathToFxmlFile polku tiedostoon
     * @return Scene
     * @throws Exception 
     */
    public Scene buildSceneFor(String pathToFxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathToFxmlFile));
        Parent parent = loader.load();
        
        if (pathToFxmlFile.equals("/fxml/Welcome.fxml")) {
            this.welcomescenecontroller = loader.getController();
        }
        
        setServiceAndApplication(loader.getController());
        
        return new Scene(parent, height, width);
    }

    /**
     * Asettaa scene-olion kontrolleriluokalle service- ja application-luokat.
     * @param controller scenelle määritelty kontrolleri
     */
    public void setServiceAndApplication(UiController controller) {
        controller.setService(this.studyService);
        controller.setApplication(this);
    }

    public static void main(String[] args) {

        launch(args);

    }

}
