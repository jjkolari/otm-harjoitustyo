package opintoapp.dao;

import java.sql.SQLException;
import opintoapp.domain.User;

/**
 * Interface UserDao-luokkien toteutukseen.
 *
 */
public interface UserDaoApi {

    /**
     * Tallentaa parametrina saadun käyttäjän tietokantaan.
     *
     * @param user Käyttäjä
     * @throws SQLException
     */
    void create(User user) throws SQLException;

    /**
     * Metodi etsii tietokannasta käyttäjän joka vastaa parametrina saatua
     * käyttäjänimeä ja salasanaa.
     *
     * @param username Käyttäjänimi
     * @param password Salasana cleantext-muodossa.
     * @return null mikäli salasana ei oikein, muuten palauttaa User-olion.
     * @throws SQLException
     */
    User findOne(String username, String password) throws SQLException;

}
