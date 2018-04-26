package opintoapp.domain;

import javafx.beans.property.SimpleStringProperty;

/**
 * Sovelluksen käyttäjän kurssia edustava abstrakti luokka.
 *
 */
public abstract class Course {

    private SimpleStringProperty name = new SimpleStringProperty("");
    private int points;
    private String semester;

    public Course(String name, int points, String semester) {
        this.name.set(name);
        this.points = points;
        this.semester = semester;
    }

    public String getName() {
        return this.name.getValue();
    }

    public int getPoints() {
        return points;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return this.name + ", points:" + this.points;
    }

}
