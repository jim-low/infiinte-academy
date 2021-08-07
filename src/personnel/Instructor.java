package personnel;
import java.util.ArrayList;
import system.*;
import management.Reservation;


public class Instructor implements Reservation{
    // type your code here, all the best
    private String qualification;
    private Course course;
    private Session[] studentAttendance;
    private static ArrayList<Instructor> instructorList;

    public Instructor(String qualification) {
        this.qualification = qualification;
    }

    public Instructor(Course course) {
        this.course = course;
    }

    public Instructor(String qualification, Course course) {
        this.qualification = qualification;
        this.course = course;
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
        return ("String");
    }
    
    public static void add(Instructor instructor){
        instructorList.add(instructor);
    }
}

/*

variables:
    private
    protected
    default
    public

constructors:
    constructor with no parameter
    constructor with some parameter
    constructor with all parameter

methods:
-normals methods
-getters and setters

*/