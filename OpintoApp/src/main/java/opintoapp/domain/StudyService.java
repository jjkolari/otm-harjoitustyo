package opintoapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
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
            System.out.println("login fail: " + e.getMessage());
            return false;
        }
    }

    public void addCourse(String name, int points, String semester, int grade) {
        CompletedCourse c = new CompletedCourse(name, points, semester, grade);
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
    
    public List<CompletedCourse> filterCoursesBySemester(String semester) {
        try {
            if(semester.equals("All")) {
                return this.getUsersCourses();
            } else {
                return cdao.getSemester(semester);
            }
        } catch (Exception e) {
            System.out.println("Filter-failure: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void deleteCourse(String courseName) {
        try {
            this.cdao.delete(courseName, this.loggedIn.getUsername());
        } catch (Exception e) {
            System.out.println("deletefail: " + e.getMessage());
        }
    }

    public double averageGrade(String semester) {
        try {
            List<CompletedCourse> courses = this.filterCoursesBySemester(semester);
            if (courses.size() == 0) {
                return 0;
            }
            ArrayList<Integer> grades = courses.stream()
                    .map(c -> c.getGrade())
                    .collect(Collectors.toCollection(ArrayList::new));

            OptionalDouble avg = grades.stream()
                    .mapToInt(a -> a)
                    .average();
            double a = avg.getAsDouble();
            return a;
        } catch (Exception e) {
            System.out.println("averagefail: " + e.getMessage());
            return 0;
        }

    }

    public int creditsTotal(String semester) {
        try {
            List<CompletedCourse> courses = this.filterCoursesBySemester(semester);
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
