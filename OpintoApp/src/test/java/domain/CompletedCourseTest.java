
package domain;

import opintoapp.domain.CompletedCourse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CompletedCourseTest {
    
    private CompletedCourse course;
    
    public CompletedCourseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.course = new CompletedCourse("name", 5, "2018", 5);
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void gradeIsCorrect() {
         assertEquals(5, this.course.getGrade());
     }
     
     @Test
     public void nameIsCorrect() {
         assertEquals("name", this.course.getName());
     }
     
     @Test
     public void creditsAreCorrect() {
         assertEquals(5, this.course.getPoints());
     }
     
     @Test
     public void semesterIsCorrect() {
         assertEquals("2018", this.course.getSemester());
     }
     
     @Test
     public void toStringTest() {
         assertEquals("name, credits: 5, grade: 5", this.course.toString());
     }
}
