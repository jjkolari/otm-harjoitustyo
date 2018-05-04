
package domain;

import opintoapp.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class UserTest {
    
    private User user;
    
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
        this.user = new User("user", "name", "123");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void nameIsCorrect(){
        assertEquals("name", this.user.getName());
    }
    
    @Test
    public void userNameIsCorrect() {
        assertEquals("user", this.user.getUsername());
    }
    
    @Test
    public void pwIsCorrect() {
        assertEquals("123", this.user.getPswd());
    }
    
    @Test
    public void toStringWorks() {
        assertEquals("user, name", this.user.toString());
    }
}
