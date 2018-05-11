package opintoapp.dao;

import java.sql.SQLException;
import java.util.List;
import opintoapp.domain.CompletedCourse;
import opintoapp.domain.User;

/**
 * Interface CourseDao-luokkien toteutukseen.
 * 
 */
public interface CourseDaoApi {

    /**
     * Tallentaa parametrina annetun kurssin tietokantaan, käyttäjän
     * käyttäjänimi viiteavaimena.
     *
     * @param c Kurssi
     * @param u Käyttäjä
     * @throws SQLException
     */
    void create(CompletedCourse c, User u) throws SQLException;

    /**
     * Poistaa kurssin tietokannasta.
     *
     * @param courseName Kurssin nimi
     * @param u Käyttäjä, jonka listauksesta kurssi poistetaan
     * @throws SQLException
     */
    void delete(String courseName, User u) throws SQLException;

    /**
     * Metodi hakee tietokannasta parametrina annetun käyttäjän kaikki kurssit.
     *
     * @param u käyttäjä
     * @return Kurssiolioita sisältävä lista
     * @throws SQLException
     */
    List<CompletedCourse> getAll(User u) throws SQLException;

    /**
     * Metodi hakee tietokannasta käyttäjän kurssit lukukaudella rajattuna.
     *
     * @param semester lukukausi
     * @param u käyttäjä
     * @return Lista kurssiolioita
     * @throws SQLException
     */
    List<CompletedCourse> getSemester(String semester, User u) throws SQLException;
    
}
