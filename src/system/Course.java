package system;

public class Course {
    // type your code here, all the best
    private String courseName;
    private double courseFee;
    private final String FIELD_OF_STUDY;
    public final static String[] AVAILABLE_FIELDS = {"", "", "", ""};
    public final static int MAX_STUDENTS = 100;

    public Course(String courseName, String FIELD_OF_STUDY) {
        this(courseName, 0.0, FIELD_OF_STUDY);
    }

    public Course(double courseFee, String FIELD_OF_STUDY) {
        this("", courseFee, FIELD_OF_STUDY);
    }

    public Course(String courseName, double courseFee, String FIELD_OF_STUDY) {
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.FIELD_OF_STUDY = FIELD_OF_STUDY;
    }

    public String toString(){
        return String.format("%-20s %-10s %-20s\n", courseName, courseFee, FIELD_OF_STUDY);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFieldOfStudy() {
        return FIELD_OF_STUDY;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }
}

