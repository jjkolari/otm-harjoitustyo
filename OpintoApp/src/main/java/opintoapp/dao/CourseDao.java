package opintoapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import opintoapp.domain.*;

public class CourseDao implements Dao {

    private Database db;
    private StudyService service;

    public CourseDao(Database db, StudyService service) {
        this.db = db;
        this.service = service;
    }

    @Override
    public Object findOne(String s, String s2) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Course> getAll() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("Select * From Course "
                + "Where user_username = ?");
        stmt.setString(0, this.service.getLoggedIn().getUsername());
        ResultSet rs = stmt.executeQuery();
        List<Course> courses = new ArrayList<>();
        while (rs.next()) {
            courses.add(new CompletedCourse(rs.getString("name"), rs.getInt("credits"), rs.getInt("grade")));
        }

        rs.close();
        stmt.close();
        conn.close();
        return courses;
    }

    public void create(CompletedCourse c) throws SQLException {
        Connection conn = this.db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Course (name, credits, grade, user_username)"
                + "VALUES (?, ?, ?, ?)");
        stmt.setString(0, c.getName());
        stmt.setInt(1, c.getPoints());
        stmt.setInt(2, c.getGrade());
        stmt.setString(3, this.service.getLoggedIn().getUsername());

        System.out.println(c.toString());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

}
