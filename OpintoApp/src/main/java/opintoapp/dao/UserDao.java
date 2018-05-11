package opintoapp.dao;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.function.Consumer;
import opintoapp.domain.User;

/**
 * Dao-luokka käyttäjien tietokantatalletukseen.
 *
 */
public class UserDao implements UserDaoApi {

    private Database db;

    public UserDao(Database db) {
        this.db = db;
    }

    /**
     * Tallentaa parametrina saadun käyttäjän tietokantaan.
     *
     * @param user Käyttäjä
     * @throws SQLException
     */
    @Override
    public void create(User user) throws SQLException {
        Consumer<PreparedStatement> statement = (stmt) -> {
            try {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getName());
                String hashed = BCrypt.hashpw(user.getPswd(), BCrypt.gensalt());
                stmt.setString(3, hashed);
            } catch (Exception e) {
            }
        };

        this.db.deleteUpdateOrInsert("INSERT INTO User (username, name, password)"
                + "VALUES(?, ?, ?)", statement);
    }

    /**
     * Metodi etsii tietokannasta käyttäjän joka vastaa parametrina saatua
     * käyttäjänimeä ja salasanaa.
     *
     * @param username Käyttäjänimi
     * @param password Salasana cleantext-muodossa.
     * @return null mikäli salasana ei oikein, muuten palauttaa User-olion.
     * @throws SQLException
     */
    @Override
    public User findOne(String username, String password) throws SQLException {
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement("Select * From User Where "
                        + "username = ? ");) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            boolean pwCorrectAndUserReturned = checkResultSetAndPassword(rs, password);
            if (!pwCorrectAndUserReturned) {
                return null;
            }

            String usrname = rs.getString("username");
            String name = rs.getString("name");
            String pswd = rs.getString("password");
            User user = new User(usrname, name, pswd);

            rs.close();
            return user;
        }
    }

    /**
     * Apumetodi, joka tarkastaa että tietokannasta palautuu käyttäjänimeä
     * vastaava käyttäjä ja salasana on oikein, salasanavertailu BCryptiä
     * käyttäen suoritetaan tässä metodissa.
     *
     * @param rs Tietokantakyselyn resultset
     * @param password salasana
     * @return
     * @throws SQLException
     */
    public boolean checkResultSetAndPassword(ResultSet rs, String password) throws SQLException {
        boolean userFound = rs.next();
        if (!userFound) {
            return false;
        }
        boolean pwMatches = BCrypt.checkpw(password, rs.getString("password"));
        if (!pwMatches) {
            rs.close();
            return false;
        }

        return true;
    }

}
