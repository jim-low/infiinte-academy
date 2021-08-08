package system;

public class Course {
    // type your code here, all the best
    private String courseName;
    private double courseFee;
    private String fieldOfStudy;
    public final static String[] AVAILABLE_FIELDS = {"", "", "", ""};
    public final static int MAX_STUDENTS = 100;
    
    public Course(String courseName, String fieldOfStudy) {
        this(courseName, 0.0, fieldOfStudy);
    }
    
    public Course(double courseFee, String fieldOfStudy) {
        this("", courseFee, fieldOfStudy);
    }

    public Course(String courseName, double courseFee, String fieldOfStudy) {
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.fieldOfStudy = fieldOfStudy;
    }
    
    public String toString(){
        return String.format("%-20s %-10s %-20s\n", courseName, courseFee, fieldOfStudy);
    }
    
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }
}