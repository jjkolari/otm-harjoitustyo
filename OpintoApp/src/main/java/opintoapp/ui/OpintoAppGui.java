
package opintoapp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpintoAppGui extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loginLoader.load();
        
        Scene scene = new Scene(root, 300, 275);
        
        stage.setTitle("hello fxml world");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        
        launch(args);
        
        
    }
    
    
    
    
}
