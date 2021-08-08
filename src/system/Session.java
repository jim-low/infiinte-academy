package system;

import personnel.*;

public class Session {
    // type your code here, all the best
    private Slot slot;
    private Course course;
    private Instructor instructor;

    public Session(Slot slot, Course course, Instructor instructor) {
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
        return String.format("%-20s %-10s %-20s\n", slot, course, instructor);
    }
}