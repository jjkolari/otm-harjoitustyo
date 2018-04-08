
package opintoapp.domain;

import opintoapp.dao.Database;
import opintoapp.dao.UserDao;

public class StudyService {
    //sovelluslogiikkaluokka datan käsittelyyn
    
    private UserDao udao;
    private User loggedIn;

    public StudyService(Database db) {
        this.udao = new UserDao(db);
    }
    
    public void createUser(String name, String uname, String pwd){
        User u = new User(uname, name, pwd);
        try {
            udao.Create(u);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean logIn(String username, String password){
        try {
            User u = this.udao.findOne(username, password);
            this.loggedIn = u;
            if(u == null){
                return false;
            } else {
                return true;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public void addCourse(User u, String name, int points, int grade){
        Course c = new CompletedCourse(name, points, grade);
        u.addCourse(c);
    }
    
    
}
