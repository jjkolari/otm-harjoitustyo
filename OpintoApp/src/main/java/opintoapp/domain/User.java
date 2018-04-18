package opintoapp.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String name;
    private String pswd;

    public User(String username, String name, String pswd) {
        this.username = username;
        this.name = name;
        this.pswd = pswd;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPswd() {
        return pswd;
    }

//    public void addCourse(Course c) {
//        this.courses.add(c);
//    }
//
//    public List<Course> getCourses() {
//        return courses;
//    }

}
