
package opintoapp.ui;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import opintoapp.domain.CompletedCourse;
import opintoapp.domain.Course;
import opintoapp.domain.StudyService;
import opintoapp.domain.User;

public class WelcomeSceneController {
    
    @FXML private Text actionTarget;
    @FXML private TextField courseName;
    @FXML private ChoiceBox creditBox;
    @FXML private ChoiceBox gradeBox;
    @FXML private TableView<Course> tableView;
    private StudyService service;
    private OpintoAppMain application;
    private ObservableList<Course> courseList;
    
    public void setApplication(OpintoAppMain application) {
        this.application = application;
    }

    public void setService(StudyService service) {
        this.service = service;
    }
    
    public void setActionTarget(){
        String username = this.service.getLoggedIn().getUsername();
        this.actionTarget.setText("Welcome to OpintoApp " + username);
        this.actionTarget.setFont(new Font("Arial", 20));
    }
    
    public void setCourseList(){
        this.courseList = FXCollections.observableArrayList(this.service.getUsersCourses());
//        this.courseList = FXCollections.observableArrayList(u.getCourses());
        tableView.setItems(courseList);
    }
    
    public void handleAddCourse(){
        String name = courseName.getText();
        int points = Integer.parseInt(creditBox.getValue().toString());
        int grade = Integer.parseInt(gradeBox.getValue().toString());
        //2 seuraava riviä lisää listalle
        this.courseList = tableView.getItems();
        this.courseList.add(new CompletedCourse(name, points, grade));
        //alla daon kautta lisäys tietokantaan, ei toimi????
//        this.service.addCourse(name, points, grade);
        this.courseName.setText("");
    }
    
    public void handleLogOut(){
        this.application.setLoginScene();
    }
    
}
