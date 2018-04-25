package opintoapp.ui;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    @FXML
    private Text actionTarget;
    @FXML
    private TextField courseName;
    @FXML
    private ChoiceBox creditBox;
    @FXML
    private ChoiceBox gradeBox;
    @FXML
    private TableView<Course> tableView;
    @FXML
    private Text average;
    @FXML
    private Text totalCredits;
    @FXML
    private TextField delCourseName;
    private StudyService service;
    private OpintoAppMain application;
    private ObservableList<Course> courseList;

    public void setApplication(OpintoAppMain application) {
        this.application = application;
    }

    public void setService(StudyService service) {
        this.service = service;
    }

    public void setActionTarget() {
        String username = this.service.getLoggedIn().getUsername();
        this.actionTarget.setText("Welcome to OpintoApp " + username);
    }

    public void setAverageAndTotal() {
        double avg = this.service.averageGrade();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        if (avg == 0) {
            this.average.setText("");
        } else {
            this.average.setText("Average of grades: " + df.format(avg));
        }

        int total = this.service.creditsTotal();
        if (total == 0) {
            this.totalCredits.setText("");
        } else {
            this.totalCredits.setText("Credits earned: " + total);
        }
    }

    public void setCourseList() {
        this.courseList = FXCollections.observableArrayList(this.service.getUsersCourses());
        tableView.setItems(courseList);
    }

    public void handleAddCourse() {
        String name = courseName.getText();
        int points = Integer.parseInt(creditBox.getValue().toString());
        int grade = Integer.parseInt(gradeBox.getValue().toString());
        //alla daon kautta lisäys tietokantaan, NYT TOIMII
        this.service.addCourse(name, points, grade);
        this.setCourseList();
        this.setAverageAndTotal();
        this.courseName.setText("");
        this.creditBox.setValue(null);
        this.gradeBox.setValue(null);
    }

    public void handleDelete() {
        String courseName = this.delCourseName.getText();
        if (courseName.length() == 0) {
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Delete " + courseName + "?", ButtonType.YES, ButtonType.CANCEL);
            alert.setTitle("Are you sure?");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                this.service.deleteCourse(courseName);
                this.setCourseList();
                this.setAverageAndTotal();
                this.delCourseName.setText("");
            } else {
                this.delCourseName.setText("");
                return;
            }
        }
    }

    public void handleLogOut() {
        this.application.setLoginScene();
    }

}
