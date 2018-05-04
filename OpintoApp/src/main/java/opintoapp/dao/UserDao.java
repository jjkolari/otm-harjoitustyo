package opintoapp.dao;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.List;
import opintoapp.domain.User;

/**
 * Dao-luokka käyttäjien tietokantatalletukseen.
 * 
 */
public class UserDao {

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
    public void create(User user) throws SQLException {
        try(Connection connection = db.getConnection();
                PreparedStatement stmt = createInsertStatement(user, connection);){
            stmt.executeUpdate();
        }
    }
    
    /**
     * Apumetodi luo SQL-lauseen käyttäjän tietokantaan tallentamista varten.
     * 
     * @param user Käyttäjä
     * @param conn Tietokantayhteys
     * @return SQL-lause
     * @throws SQLException 
     */
    public PreparedStatement createInsertStatement(User user, Connection conn) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (username, name, password)"
                + "VALUES(?, ?, ?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getName());
        String hashed = BCrypt.hashpw(user.getPswd(), BCrypt.gensalt());
        stmt.setString(3, hashed);
        
        return stmt;
    }

    /**
     * Metodi etsii tietokannasta käyttäjän joka vastaa parametrina saatua käyttäjänimeä ja
     * salasanaa, salasanavertailu BCryptiä käyttäen suoritetaan tässä metodissa.
     * 
     * @param username Käyttäjänimi
     * @param password Salasana cleantext-muodossa.
     * @return null mikäli salasana ei oikein, muuten palauttaa User-olion.
     * @throws SQLException 
     */
    public User findOne(String username, String password) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("Select * From User Where "
                + "username = ? ");
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        boolean pwMatches = BCrypt.checkpw(password, rs.getString("password"));
        if (!pwMatches) {
            rs.close();
            stmt.close();
            conn.close();
            return null;
        }

        String usrname = rs.getString("username");
        String name = rs.getString("name");
        String pswd = rs.getString("password");
        User user = new User(usrname, name, pswd);

        rs.close();
        stmt.close();
        conn.close();
        return user;
    }

}
