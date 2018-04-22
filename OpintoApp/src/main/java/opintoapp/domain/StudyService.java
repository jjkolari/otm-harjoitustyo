package opintoapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import opintoapp.dao.Database;
import opintoapp.dao.*;

public class StudyService {
    //sovelluslogiikkaluokka datan käsittelyyn

    private UserDao udao;
    private CourseDao cdao;
    private User loggedIn;

    public StudyService(Database db) {
        this.udao = new UserDao(db);
        this.cdao = new CourseDao(db);
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
        try {
            cdao.create(c, this.loggedIn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public User getLoggedIn() {
        return loggedIn;
    }

    public List<CompletedCourse> getUsersCourses() {
        try {
            return cdao.getAll(this.loggedIn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public double averageGrade() {
        try {
            List<CompletedCourse> courses = cdao.getAll(this.loggedIn);
            ArrayList<Integer> grades = new ArrayList<>();
            courses.stream()
                    .forEach(c -> grades.add(c.getGrade()));

            OptionalDouble avg = grades.stream()
                    .mapToInt(a -> a)
                    .average();
            return avg.getAsDouble();
        } catch (Exception e) {
            return 0;
        }

    }

    public int creditsTotal() {
        try {
            List<CompletedCourse> courses = cdao.getAll(this.loggedIn);
            ArrayList<Integer> credits = new ArrayList<>();
            courses.stream()
                    .forEach(c -> credits.add(c.getPoints()));
            int total = 0;
            for (int c : credits) {
                total += c;
            }
            return total;
        } catch (Exception e) {
            return 0;
        }
    }

}
