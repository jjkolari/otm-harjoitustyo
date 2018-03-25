
package domain;

import opintoapp.domain.CompletedCourse;
import opintoapp.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    private User u;
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.u = new User("uname", "name", "123");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void addCourseWorks(){
        u.addCourse(new CompletedCourse("course1", 5, 5));
        assertEquals(1, u.getCourses().size());
    }
}
