package opintoapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import opintoapp.domain.*;

/**
 * Dao-luokka kurssien tietokantatalletukseen.
 * 
 */
public class CourseDao {

    private Database db;

    public CourseDao(Database db) {
        this.db = db;
    }

    /**
     * Metodi hakee tietokannasta parametrina annetun käyttäjän kaikki kurssit.
     * @param u käyttäjä
     * @return Kurssiolioita sisältävä lista
     * @throws SQLException 
     */
    public List<CompletedCourse> getAll(User u) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Course "
                + "WHERE user_username = ?");
        stmt.setString(1, u.getUsername());
        ResultSet rs = stmt.executeQuery();
        List<CompletedCourse> courses = new ArrayList<>();
        while (rs.next()) {
            courses.add(new CompletedCourse(rs.getString("name"), rs.getInt("credits"), rs.getString("semester"), rs.getInt("grade")));
        }

        rs.close();
        stmt.close();
        conn.close();
        return courses;
    }

    /**
     * Metodi hakee tietokannasta käyttäjän kurssit lukukaudella rajattuna.
     * @param semester lukukausi
     * @param u käyttäjä
     * @return Lista kurssiolioita
     * @throws SQLException 
     */
    public List<CompletedCourse> getSemester(String semester, User u) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Course "
                + "WHERE semester = ? AND user_username = ?");
        stmt.setString(1, semester);
        stmt.setString(2, u.getUsername());
        ResultSet rs = stmt.executeQuery();
        List<CompletedCourse> courses = new ArrayList<>();
        while (rs.next()) {
            courses.add(new CompletedCourse(rs.getString("name"), rs.getInt("credits"), rs.getString("semester"), rs.getInt("grade")));
        }

        rs.close();
        stmt.close();
        conn.close();
        return courses;
    }

    /**
     * Tallentaa parametrina annetun kurssin tietokantaan, käyttäjän käyttäjänimi viiteavaimena.
     * 
     * @param c Kurssi
     * @param u Käyttäjä
     * @throws SQLException 
     */
    public void create(CompletedCourse c, User u) throws SQLException {
        Connection conn = this.db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Course (name, credits, grade, semester, user_username)"
                + "VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, c.getName());
        stmt.setInt(2, c.getPoints());
        stmt.setInt(3, c.getGrade());
        stmt.setString(4, c.getSemester());
        stmt.setString(5, u.getUsername());

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    /**
     * Poistaa kurssin tietokannasta.
     * 
     * @param courseName Kurssin nimi
     * @param username Käyttäjänimi johon kurssin viiteavain viittaa
     * @throws SQLException 
     */
    public void delete(String courseName, String username) throws SQLException {
        Connection conn = this.db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Course "
                + "WHERE name = ? AND user_username = ?");
        stmt.setString(1, courseName);
        stmt.setString(2, username);

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

}
