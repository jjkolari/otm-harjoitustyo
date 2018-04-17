package opintoapp.domain;

import java.util.ArrayList;
import java.util.List;
import opintoapp.dao.Database;
import opintoapp.dao.*;

public class StudyService {
    //sovelluslogiikkaluokka datan käsittelyyn

    private UserDao udao;
    private CourseDao cdao;
    private User loggedIn;

    public StudyService(Database db) {
        this.udao = new UserDao(db);
        this.cdao = new CourseDao(db, this);
    }

    public boolean createUser(String uname, String name, String pwd) {
        User u = new User(uname, name, pwd);
        try {
            udao.create(u);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean logIn(String username, String password) {
        try {
            User u = this.udao.findOne(username, password);
            this.loggedIn = u;
            if (u == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void addCourse(String name, int points, int grade) {
        CompletedCourse c = new CompletedCourse(name, points, grade);
        this.loggedIn.addCourse(c);
//        try {
//            cdao.create(c);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    public User getLoggedIn() {
        return loggedIn;
    }

    public List<Course> getUsersCourses() {
        try {
            return cdao.getAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
