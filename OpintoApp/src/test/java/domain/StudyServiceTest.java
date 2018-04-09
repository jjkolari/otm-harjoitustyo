
package domain;

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
    
}
