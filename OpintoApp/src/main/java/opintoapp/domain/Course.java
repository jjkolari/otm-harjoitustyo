package opintoapp.domain;

import javafx.beans.property.SimpleStringProperty;

public abstract class Course {

    private SimpleStringProperty name = new SimpleStringProperty("");
    private int points;

    public Course(String name, int points) {
        this.name.set(name);
        this.points = points;
    }

    public String getName() {
        return this.name.getValue();
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return this.name + ", points:" + this.points;
    }

}
