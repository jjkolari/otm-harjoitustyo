package opintoapp.ui;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import opintoapp.domain.Course;
import opintoapp.domain.StudyService;

/**
 * Sisäänkirjautumisen jälkeisen näkymän kontrolleriluokka.
 * 
 */
public class WelcomeSceneController extends UiController{

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
    @FXML
    private ComboBox semester;
    @FXML
    private ComboBox semesterFilter;
    private ObservableList<Course> courseList;

    /**
     * Welcome-tekstin asettaminen.
     */
    public void setActionTarget() {
        String username = this.service.getLoggedIn().getUsername();
        this.actionTarget.setText("Welcome to OpintoApp " + username);
    }

    /**
     * Asettaa kurssien keskiarvo- ja opintopistemäärä- tekstikenttien arvot.
     */
    public void setAverageAndTotal() {
        String showingSemester = "";
        if (this.semesterFilter.getValue() == null) {
            showingSemester = "All";
        } else {
            showingSemester = this.semesterFilter.getValue().toString();
        }
        double avg = this.service.averageGrade(showingSemester);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        if (avg == 0) {
            this.average.setText("");
        } else {
            this.average.setText("Average of grades: " + df.format(avg));
        }

        int total = this.service.creditsTotal(showingSemester);
        if (total == 0) {
            this.totalCredits.setText("");
        } else {
            this.totalCredits.setText("Credits earned: " + total);
        }
    }

    /**
     * Asettaa kurssilistauksen taulukkoelementtiin.
     */
    public void setCourseList() {
        this.courseList = FXCollections.observableArrayList(this.service.getUsersCourses());
        tableView.setItems(courseList);
    }

    /**
     * Filter-napin käsittely.
     */
    public void setFilteredCourseList() {
        if (this.semesterFilter.getValue() == null) {
            return;
        }
        String semester = this.semesterFilter.getValue().toString();
        this.courseList = FXCollections.observableArrayList(this.service.filterCoursesBySemester(semester));
        tableView.setItems(courseList);
        this.setAverageAndTotal();
    }

    /**
     * Add course -napin klikkaus.
     */
    public void handleAddCourse() {
        if (courseName.getText().equals("") || creditBox.getValue() == null
                || gradeBox.getValue() == null || semester.getValue() == null) {
            return;
        }

        String name = courseName.getText();
        int points = Integer.parseInt(creditBox.getValue().toString());
        String semester = this.semester.getValue().toString();
        int grade = Integer.parseInt(gradeBox.getValue().toString());
        //alla daon kautta lisäys tietokantaan, NYT TOIMII
        this.service.addCourse(name, points, semester, grade);
        this.setCourseList();
        this.setAverageAndTotal();
        this.courseName.setText("");
        this.semester.setValue(null);
        this.creditBox.setValue(null);
        this.gradeBox.setValue(null);
        this.semesterFilter.getSelectionModel().selectFirst();
    }

    /**
     * Delete-napin klikkaus.
     */
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
    
    /**
     * Uloskirjautuminen.
     */
    public void handleLogOut() {
        this.application.setLoginScene();
    }

}
