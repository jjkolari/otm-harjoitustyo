package opintoapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import opintoapp.domain.*;

/**
 * Dao-luokka kurssien tietokantatalletukseen.
 *
 */
public class CourseDao {

    private Database db;
    private User u;

    public CourseDao(Database db) {
        this.db = db;
    }

    /**
     * Metodi hakee tietokannasta parametrina annetun käyttäjän kaikki kurssit.
     *
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
     *
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
     * Tallentaa parametrina annetun kurssin tietokantaan, käyttäjän
     * käyttäjänimi viiteavaimena.
     *
     * @param c Kurssi
     * @param u Käyttäjä
     * @throws SQLException
     */
    public void create(CompletedCourse c, User u) throws SQLException {
        Consumer<PreparedStatement> statement = (stmt) -> {
            try {
                stmt.setString(1, c.getName());
                stmt.setInt(2, c.getPoints());
                stmt.setInt(3, c.getGrade());
                stmt.setString(4, c.getSemester());
                stmt.setString(5, u.getUsername());
            } catch (Exception e) {
            }
        };
        
        this.db.deleteUpdateOrInsert("INSERT INTO Course (name, credits, grade, semester, user_username)"
                + "VALUES (?, ?, ?, ?, ?)", statement);
    }

    /**
     * Poistaa kurssin tietokannasta.
     *
     * @param courseName Kurssin nimi
     * @param u Käyttäjä, jonka listauksesta kurssi poistetaan
     * @throws SQLException
     */
    public void delete(String courseName, User u) throws SQLException {
        Consumer<PreparedStatement> statement = (stmt) -> {
            try {
                stmt.setString(1, courseName);
                stmt.setString(2, u.getUsername());
            } catch (Exception e) {
            }
        };

        this.db.deleteUpdateOrInsert("DELETE FROM Course "
                + "WHERE name = ? AND user_username = ?", statement);

    }
}
