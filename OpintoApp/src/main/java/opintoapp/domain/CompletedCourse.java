package opintoapp.domain;

public class CompletedCourse extends Course {

    private int grade;

    public CompletedCourse(String name, int points, int grade) {
        super(name, points);
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return this.getName() + ", points:" + this.getPoints() + ", grade: " + this.getGrade();
    }

}
