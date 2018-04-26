package opintoapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import opintoapp.domain.*;

public class CourseDao {

    private Database db;

    public CourseDao(Database db) {
        this.db = db;
    }

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

    public List<CompletedCourse> getSemester(String semester) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Course "
                + "WHERE semester = ?");
        stmt.setString(1, semester);
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
