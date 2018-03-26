
package opintoapp.domain;

import opintoapp.dao.UserDao;

public class StudyService {
    //sovelluslogiikkaluokka datan käsittelyyn
    
    private UserDao udao;

    public StudyService(UserDao udao) {
        this.udao = udao;
    }
    
    public void createUser(String name, String uname, String pwd){
        User u = new User(uname, name, pwd);
        //udao.save(u); tässä sit tallennetaan
    }
    
    
}
