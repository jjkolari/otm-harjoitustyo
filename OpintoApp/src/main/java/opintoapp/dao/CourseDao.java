package opintoapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import opintoapp.domain.*;

public class CourseDao implements Dao {

    private Database db;

    public CourseDao(Database db) {
        this.db = db;
    }

    @Override
    public Object findOne(String s, String s2) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<CompletedCourse> getAll(User u) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Course "
                + "WHERE user_username = ?");
        stmt.setString(1, u.getUsername());
        ResultSet rs = stmt.executeQuery();
        List<CompletedCourse> courses = new ArrayList<>();
        while (rs.next()) {
            courses.add(new CompletedCourse(rs.getString("name"), rs.getInt("credits"), rs.getInt("grade")));
        }
        
        rs.close();
        stmt.close();
        conn.close();
        return courses;
    }

    public void create(CompletedCourse c, User u) throws SQLException {
        Connection conn = this.db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Course (name, credits, grade, user_username)"
                + "VALUES (?, ?, ?, ?)");
        stmt.setString(1, c.getName());
        stmt.setInt(2, c.getPoints());
        stmt.setInt(3, c.getGrade());
        stmt.setString(4, u.getUsername());

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

}
