package system;
enum FIELD_OF_STUDY {
    COMPUTER_SCIENCE,
    INFORMATION_TECHNOLOGY,
    SPORT_SCIENCE,
    BUSINESS,
    ACCOUNTING,
    FINANCE,
    SCIENCE
}
public class Course {
    // type your code here, all the best
    private String courseName;
    private double courseFee;
    private int studentCount;
    private final FIELD_OF_STUDY STUDY_FIELD;
    private final static int MAX_STUDENTS = 100;
    private final static Course[] AVAILABLE_COURSES = {
        new Course("Introduction to Data Science", 400.0, FIELD_OF_STUDY.COMPUTER_SCIENCE),
        new Course("C Programming", 500.0, FIELD_OF_STUDY.COMPUTER_SCIENCE),
        new Course("Object-Oriented Programming Techniques", 500.0, FIELD_OF_STUDY.COMPUTER_SCIENCE),
        new Course("Chemistry", 400.0, FIELD_OF_STUDY.SCIENCE),
        new Course("Biology", 450.0, FIELD_OF_STUDY.SCIENCE),
        new Course("Physics", 450.0, FIELD_OF_STUDY.SCIENCE),
        new Course("Introduction to Anatomy", 400.0, FIELD_OF_STUDY.SCIENCE),
        new Course("Psycology", 450.0, FIELD_OF_STUDY.SPORT_SCIENCE),
        new Course("Auditing", 500.0, FIELD_OF_STUDY.ACCOUNTING),
        new Course("Cost and Management", 500.0, FIELD_OF_STUDY.ACCOUNTING),
        new Course("Economics", 300.0, FIELD_OF_STUDY.BUSINESS),
        new Course("Business Management/Administration", 300.0, FIELD_OF_STUDY.BUSINESS)
    };
    
    public Course(String courseName, FIELD_OF_STUDY fieldOfStudy) {
        this(courseName, 0.0, fieldOfStudy);
    }
    
    public Course(double courseFee, FIELD_OF_STUDY fieldOfStudy) {
        this("", courseFee, fieldOfStudy);
    }

    public Course(String courseName, double courseFee, FIELD_OF_STUDY fieldOfStudy) {
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.STUDY_FIELD = fieldOfStudy;
        this.studentCount = 0;
    }
    
    public static void listCourses(){
        System.out.println("\tCourse Name\t\tCourse Fee(RM)\tStudent Count\n");
        for (int i = 0; i < AVAILABLE_COURSES.length; i++) {
            Course currCourse = AVAILABLE_COURSES[i];
            System.out.printf("%d.\t%s\t\t%.2f\t%d/%d\n", i+1, currCourse.courseName, currCourse.courseFee, currCourse.studentCount, Course.MAX_STUDENTS);
        }
    }
    
    public static Course search(int index){
        if(index < 0 || index >= AVAILABLE_COURSES.length){
            return null;
        }
        
        return AVAILABLE_COURSES[index];
    }
    
    public String toString(){
        return String.format("%-20s RM%-10s %-20s\n", courseName, courseFee, STUDY_FIELD);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getFieldOfStudy() {
        return STUDY_FIELD.toString();
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }
}