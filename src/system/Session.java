package system;

import java.util.ArrayList;
import management.Academy;
import personnel.*;

public class Session {
    private Slot slot;
    private Course course;
    private Instructor instructor;
    private final static ArrayList<Session> RESERVED_SESSIONS = new ArrayList<>();

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

    public static void addReservedSession(Session session) {
        RESERVED_SESSIONS.add(session);
    }

    public static Session getReservedSession(int index) {
        if(index < 0 || index >= RESERVED_SESSIONS.size()){
            return null;
        }

        return RESERVED_SESSIONS.get(index);
    }

    public static void listReservedSessions() {
        System.out.println("\tRESERVED SESSIONS LIST");
        System.out.println("\t======================");
        for (int i = 0; i < RESERVED_SESSIONS.size(); i++) {
            System.out.printf("%d.\t%s\n", i+1, RESERVED_SESSIONS.get(i));
        }
    }

    public static void removeReservedSession(Session session) {
        RESERVED_SESSIONS.remove(session);
    }

    public String toString(){
        return "Slot: " + this.slot.toString() + "\n" +
               "Course Name: " + this.course.getCourseName() + "\n" +
               "Instructor name: " + this.instructor.getName();
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
}

