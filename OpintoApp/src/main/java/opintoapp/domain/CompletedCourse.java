package opintoapp.domain;

public class CompletedCourse extends Course {

    private int grade;

    public CompletedCourse(String name, int points, String semester, int grade) {
        super(name, points, semester);
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return super.toString() +  ", grade: " + this.getGrade();
    }

}
