package personnel;

import java.util.ArrayList;
import system.*;
import management.Reservation;
import management.Academy;

public class Instructor extends Person implements Reservation {
    private ArrayList<String> qualifications = new ArrayList<>();
    private Course course;
    private ArrayList<Session> classes = new ArrayList<>();
    private static ArrayList<Instructor> instructorList = new ArrayList<>();
    private String instructorID;
    private static int nextInstructorID = 1000;

    public Instructor(Person person, Course course){
        this(person,"",course);
    }

    public Instructor(Person person, String instructorID){
        this(person, instructorID, null);
    }

    public Instructor(Person person, String instructorID, Course course){
        super(person);
        this.instructorID = CODE.INS.toString()+nextInstructorID;;
        ++nextInstructorID;
    }

    public void addQualification(String qualification){
        qualifications.add(qualification);
    }

    public void editQualification(String qualification){
        for(int i=0; i < qualifications.size(); i++){
            if(qualifications.get(i).equals(qualification)){
                System.out.println("Enter your updated qualifcation :");
                String updated = Academy.scan.nextLine();
                qualifications.set(i,updated);
            }
        }
    }

    public void listQualification(){
        System.out.print("Your Qualifications :");
        for (int i = 0; i < qualifications.size(); i++) {
            System.out.print(i+1 +".");
            System.out.println(qualifications.get(i));
        }
    }

    public void removeQualification(String qualification){
        qualifications.remove(qualification);
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
            if(instructor.getName().equals(email) && instructor.getPassword().equals(password)){
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

    @Override
    public void addReservation(Session session){
        classes.add(session);

    }

    @Override
    public void listReservation(){
        System.out.print("          RESERVATION LIST           \n");
        System.out.print("-------------------------------------\n");
        for (int i = 0; i < classes.size() ; i++) {
                System.out.print(i+1 +".");
                System.out.println(" " +classes.get(i));
            }
        System.out.print("-------------------------------------\n");
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

    @Override
    public void removeReservation(Session session){
        classes.remove(session);
    }
}

