
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

public class StudyServiceTest {
    
    private Database db;
    private StudyService service;
    private User signedUser;
    
    public StudyServiceTest() throws Exception{
        this.db = new Database("jdbc:sqlite:./db/TestOpintoApp.db");
        this.service = new StudyService(db);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.signedUser = new User("legituser", "veryofficialusr", "strongpswd123456");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createUserTest(){
        boolean success = this.service.createUser(signedUser.getUsername(), signedUser.getName(), signedUser.getPswd());
        assertEquals(true, success);
    }
    
    @Test
    public void signedUserCanLogIn(){
        this.service.createUser(signedUser.getUsername(), signedUser.getName(), signedUser.getPswd());
        boolean success = this.service.logIn(signedUser.getUsername(), signedUser.getPswd());
        assertEquals(true, success);
    }
    
    @Test
    public void unknownUserCannotLogIn(){
        boolean success = this.service.logIn("unknown", "123abc");
        assertEquals(false, success);
    }
    
//    @Test
//    public void addCourseForUser(){
//        int grade = 5;
//        int points = 5;
//        String name = "course1";
//        this.service.logIn(this.signedUser.getUsername(), this.signedUser.getPswd());
//        this.service.addCourse(name, points, grade);
//        Course c = new CompletedCourse(name, points, grade);
//        List<Course> l = new ArrayList<>();
//        l.add(c);
//        assertEquals(l.get(0).toString(), this.signedUser.getCourses().get(0).toString());
//    }
    
}
