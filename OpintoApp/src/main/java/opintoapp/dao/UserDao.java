package opintoapp.dao;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.List;
import opintoapp.domain.User;

public class UserDao {

    private Database db;

    public UserDao(Database db) {
        this.db = db;
    }

    public void create(User user) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User (username, name, password)"
                + "VALUES(?, ?, ?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getName());
        String hashed = BCrypt.hashpw(user.getPswd(), BCrypt.gensalt());
        stmt.setString(3, hashed);

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

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
