
package opintoapp.dao;

import java.sql.*;
import java.util.List;
import opintoapp.domain.User;

public class UserDao implements Dao{
    
    private Database db;

    public UserDao(Database db) {
        this.db = db;
    }


    public void Create(User user) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User (username, name, password)"
                + "VALUES(?, ?, ?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getName());
        stmt.setString(3, user.getPswd());
        
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    @Override
    public Object findOne(Object k) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    //tietokantayhteys käyttäjille tulee tänne
}
