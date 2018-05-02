package opintoapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import opintoapp.dao.Database;
import opintoapp.dao.*;

/**
 * Sovelluslogiikkaluokka joka tarjoaa metodit sovelluksen datan käsittelyyn,
 * metodeja kutsutaan lähinnä käyttöliittymästä.
 *
 */
public class StudyService {

    private UserDao udao;
    private CourseDao cdao;
    private User loggedIn;

    public StudyService(Database db) {
        this.udao = new UserDao(db);
        this.cdao = new CourseDao(db);
    }

    /**
     * Metodi luo uuden käyttäjän, joka voi kirjautua sovellukseen hyödyntäen 
     * UserDao-luokkaa.
     *
     * @param uname Käyttäjänimi
     * @param name Käyttäjän nimi
     * @param pwd Salasana
     * @return true jos käyttäjän luominen onnistui, false jos tapahtui virhe.
     */
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

    /**
     * Metodi kirjaa rekisteröityneen käyttäjän sisään sovellukseen hyödyntäen
     * UserDao-luokkaa.
     * @param username Käyttäjänimi
     * @param password Salasana
     * @return true jos sisäänkirjautuminen onnistui, muutoin false.
     */
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

    /**
     * Metodi lisää käyttäjän henk.koht kurssilistaan kurssin, kurssi talletetaan
     * tietokantaan käyttäen CourseDao-luokkaa.
     * @param name Kurssin nimi
     * @param points Opintopisteet
     * @param semester Lukukausi
     * @param grade Arvosana
     */
    public void addCourse(String name, int points, String semester, int grade) {
        CompletedCourse c = new CompletedCourse(name, points, semester, grade);
        try {
            cdao.create(c, this.loggedIn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Metodi palauttaa sisäänkirjautuneen käyttäjän.
     * @return käyttäjä
     */
    public User getLoggedIn() {
        return loggedIn;
    }

    /**
     * Metodi hakee tietokannasta sisäänkirjautuneen käyttäjän kaikki kurssit käyttäen 
     * CourseDao-luokkaa.
     * 
     * @return lista käyttäjän kursseista, tyhjä lista mikäli kursseja ei ole tietokannassa
     */
    public List<CompletedCourse> getUsersCourses() {
        try {
            return cdao.getAll(this.loggedIn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Hakee tietokannasta kaikki käyttäjän kurssit parametrina annetulta lukuvuodelta
     * käyttäen CourseDao-luokkaa.
     * 
     * @param semester lukuvuosi
     * @return lista kursseista ko. vuodelta
     */
    public List<CompletedCourse> filterCoursesBySemester(String semester) {
        try {
            if (semester.equals("All")) {
                return this.getUsersCourses();
            } else {
                return cdao.getSemester(semester, this.loggedIn);
            }
        } catch (Exception e) {
            System.out.println("Filter-failure: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Metodi poistaa kurssin tietokannasta jolloin se poistuu käyttäjän profiilista.
     * 
     * @param courseName kurssin nimi
     */
    public void deleteCourse(String courseName) {
        try {
            this.cdao.delete(courseName, this.loggedIn.getUsername());
        } catch (Exception e) {
            System.out.println("deletefail: " + e.getMessage());
        }
    }

    /**
     * Metodi laskee käyttäjän kurssien arvosanojen keskiarvon parametrina annetulta lukukaudelta.
     * 
     * @param semester lukukausi jolta keskiarvo halutaan
     * @return keskiarvo liukulukuna
     */
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

    /**
     * Metodi laskee käyttäjän ansaitsemat opintopisteet parametrina annetulta lukuvuodelta.
     * 
     * @param semester lukuvuosi jolta pisteet halutaan
     * @return pisteiden määrä
     */
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
