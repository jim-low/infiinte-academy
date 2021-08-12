package personnel;

import java.util.ArrayList;
import system.*;
import management.Reservation;

public class Instructor extends Person implements Reservation{
    // type your code here, all the best
    private String qualification;
    private Course course;
    private Session[] studentAttendance;
    private static ArrayList<Instructor> instructorList;

    public Instructor(String name, String icNo, String gender, String email, String qualification) {
        this(name, icNo, gender, email, qualification, null);
    }

    public Instructor(String name, String icNo, String gender, String email, Course course) {
        this(name, icNo, gender, email, "", course);
    }

    public Instructor(String name, String icNo, String gender, String email, String qualification, Course course) {
        super(name, icNo, gender, email);
        this.qualification = qualification;
        this.course = course;
    }
    
    public static void add(Instructor instructor){
        instructorList.add(instructor);
    }
    
    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    public String toString(){
        return "";
    }
}
