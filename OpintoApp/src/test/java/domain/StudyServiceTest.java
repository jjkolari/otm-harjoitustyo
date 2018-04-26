package domain;

import java.util.ArrayList;
import java.util.List;
import opintoapp.dao.Database;
import opintoapp.domain.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;

public class StudyServiceTest {

    static private Database db;
    private StudyService service;
    private User signedUser;
    private CompletedCourse course;

    public StudyServiceTest() throws Exception {
        this.db = new Database("jdbc:sqlite:./TestOpintoApp.db");
        this.service = new StudyService(db);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM User WHERE "
                    + "username = 'legituser'");
            stmt.executeUpdate();
            stmt.close();
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Course "
                    + "WHERE name = 'course1'");
            stmt2.executeUpdate();
            stmt2.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Before
    public void setUp() {
        this.signedUser = new User("legituser", "veryofficialusr", "strongpswd123456");
        this.course = new CompletedCourse("course1", 5, "2017-2018", 5);
        this.service.createUser(this.signedUser.getUsername(), this.signedUser.getName(), this.signedUser.getPswd());
        this.service.logIn(this.signedUser.getUsername(), this.signedUser.getPswd());
        this.service.addCourse(this.course.getName(), this.course.getPoints(), this.course.getSemester(), this.course.getGrade());
    }

    @After
    public void tearDown() {

    }

    @Test
    public void getLoggedInTest() {
        User u = this.service.getLoggedIn();
        assertEquals(this.signedUser.toString(), u.toString());
    }

    @Test
    public void createUserTest() {
        boolean success = this.service.createUser("username", "name", "123");
        assertEquals(true, success);
    }

    @Test
    public void signedUserCanLogIn() {
        this.service.createUser("user", "name", "123");
        boolean success = this.service.logIn("user", "123");
        assertEquals(true, success);
    }

    @Test
    public void unknownUserCannotLogIn() {
        boolean success = this.service.logIn("unknown", "123abc");
        assertEquals(false, success);
    }

    @Test
    public void addCourseForUser() {
        List<CompletedCourse> l = new ArrayList<>();
        l.add(this.course);
        assertEquals(l.get(0).toString(), this.service.getUsersCourses().get(0).toString());
    }

    @Test
    public void totalCreditsTest() {
        assertEquals(this.service.getUsersCourses().size() * 5, this.service.creditsTotal("All"));
    }

    @Test
    public void averageTest() {
        assertEquals(5.0, this.service.averageGrade("All"), 0.1);
    }

}
