
package opintoapp.domain;

public abstract class Course {
    
    private String name;
    private int points;

    public Course(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
    
    
}