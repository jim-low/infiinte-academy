package personnel;

import java.util.ArrayList;
import system.*;
import management.Reservation;

public class Instructor extends Person implements Reservation {
    private Course course;
    private ArrayList<Session> classes = new ArrayList<>();
    private static ArrayList<Instructor> instructorList = new ArrayList<>();
    private String instructorID;
    private static int nextInstructorID = 1000;

    public static final double INSTRUCTOR_REGISTRATION_FEE = 400;

    public Instructor(Person person){
        this(person, null);
    }

    public Instructor(Person person, Course course){
        super(person);
        this.instructorID = SystemCodes.INS.toString() + nextInstructorID;;
        this.course = course;
        ++nextInstructorID;
    }

    public static void add(Instructor instructor){
        instructorList.add(instructor);
    }

    public static void remove(Instructor instructor){
        instructorList.remove(instructor);
    }

    public static Instructor search(String email, String password){
        Instructor found = null;
        for (Instructor instructor : instructorList) {
            if(instructor.getEmail().equals(email) && instructor.getPassword().equals(password)){
                found = instructor;
                break;
            }
        }
        return found;
    }

    public String toString(){
        return "Instructor ID      :" + instructorID +" \n"
             + "Instructor Name    : " + this.getName() +" \n"
             + "Instructor email   : " + this.getEmail()+" \n"
             + course.toString();
    }

     public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getID() {
        return this.instructorID;
    }

    @Override
    public void addReservation(Session session){
        classes.add(session);
        Session.addReservedSession(session);
    }

    @Override
    public boolean listReservations(){
        if (classes.size() == 0) {
            System.out.println("You do not have any reserved sessions yet.");
            return false;
        }
        System.out.print("                           RESERVATION LIST\n");
        System.out.print("--------------------------------------------------------------------\n");
        System.out.println();
        for (int i = 0; i < classes.size() ; i++) {
            System.out.print((i + 1) + ". ");
            System.out.println("Time Slot: " + classes.get(i).getSlot().showTime());
            System.out.println("Course Name: " + classes.get(i).getCourse().getCourseName());
            System.out.println();
        }
        System.out.print("--------------------------------------------------------------------\n");
        return true;
    }

    public Session getReservation(int index){
        if(index < 0 || index >= classes.size() ){
            return null;
        }
        return classes.get(index);
    }

    @Override
    public void editReservation(int index, Session session){
        if( index <0 || index >= classes.size()){
            return;
        }
        classes.set(index, session);
    }

    public void editReservation(Session oldSession, Session newSession){
        int oldSessionIndex = classes.indexOf(oldSession);
        editReservation(oldSessionIndex, newSession);
    }

    @Override
    public void removeReservation(Session session){
        classes.remove(session);
    }
}

