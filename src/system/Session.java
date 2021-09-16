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
        System.out.print("Enter your preferred slot (0 to abort): ");
        int slotSelection = Academy.scan.nextInt();
        Slot selectedSlot = Slot.search(slotSelection - 1);

        while (selectedSlot == null) {
            if (slotSelection == 0) {
                return null;
            }

            System.out.println("Incorrect Range!");
            System.out.println();
            System.out.print("Enter your preferred slot (0 to abort): ");
            slotSelection = Academy.scan.nextInt();
            selectedSlot = Slot.search(slotSelection - 1);
        }

        System.out.println();
        Course.listCourses();
        System.out.println();
        System.out.print("Enter your desired course (0 to abort): ");
        int courseSelection = Academy.scan.nextInt();
        Course selectedCourse = Course.search(courseSelection - 1);

        while (selectedCourse == null) {
            if (courseSelection == 0) {
                return null;
            }

            System.out.println("Incorrect Range!");
            System.out.println();
            System.out.print("Enter your desired course (0 to abort): ");
            courseSelection = Academy.scan.nextInt();
            selectedCourse = Course.search(courseSelection - 1);
        }

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
        System.out.println("\t\t\tRESERVED SESSIONS LIST");
        System.out.println("\t==================================================");
        for (int i = 0; i < RESERVED_SESSIONS.size(); i++) {
            Slot slot = RESERVED_SESSIONS.get(i).getSlot();
            Course course = RESERVED_SESSIONS.get(i).getCourse();
            Instructor instructor = RESERVED_SESSIONS.get(i).getInstructor();
            System.out.printf("\t%d. %s\n", i + 1, "Slot: " + slot.showTime());
            System.out.println("\t   Course Name: " + course.getCourseName());
            System.out.println("\t   Course Fee: " + course.getCourseFee());
            System.out.println("\t   Instructor name: " + instructor.getName());
            System.out.println("\t   Instructor email: " + instructor.getEmail());
            System.out.println();
        }
    }

    public static void removeReservedSession(Session session) {
        RESERVED_SESSIONS.remove(session);
    }

    public String toString(){
        return "Slot: " + this.slot.showTime() + "\n" +
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

