package system;

import management.Academy;
import personnel.*;

public class Session {
    // type your code here, all the best
    private Slot slot;
    private Course course;
    private Instructor instructor;

    public Session(Slot slot, Course course, Instructor instructor) {
        this.slot = slot;
        this.course = course;
        this.instructor = instructor;
    }
    
    public static Session createSession(Instructor instructor){
        Slot.listSlots();
        System.out.println();
        System.out.print("Enter your preferred slot: ");
        int slotSelection = Academy.scan.nextInt();
        Slot selectedSlot = Slot.search(slotSelection - 1);
        
        Course.listCourses();
        System.out.println();
        System.out.print("Enter your desired course: ");
        int courseSelection = Academy.scan.nextInt();
        Course selectedCourse = Course.search(courseSelection);
        
        return new Session(selectedSlot, selectedCourse, instructor);
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    public String toString(){
        return "Slot: " + this.slot.toString() + "\n" + 
               "Course Name: " + this.course.getCourseName() + "\n" +
               "Instructor name: " + this.instructor.getName();
    }
}