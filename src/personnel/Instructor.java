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
    private int instructorID;
    private static int nextInstructorID = 1;
    private boolean choice;
    
    public Instructor(Person person, Course course){
        this(person,"",course);
    }
    
    public Instructor(Person person, String instructorID){
        this(person, instructorID, null);
    }
    
    public Instructor(Person person, String instructorID, Course course){
        super(person);
        this.instructorID = nextInstructorID;
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
            System.out.println(qualifications);
        }
    }
    
    public void removeQualification(String qualification){
        qualifications.remove(qualification);   
    }
    
    public static Instructor search(String name){
        Instructor found = null;
        for (Instructor instructor : instructorList) {
            if(instructor.getName().equals(name)){
                found = instructor;
                break;
            }
        }
        return found;
    }
    
    public String toString(){
        return "";
    }
    
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    public static void add(Instructor instructor){
        instructorList.add(instructor);
    }
    
    public static void remove(Instructor instructor){
        instructorList.remove(instructor);
    }   
    
    @Override
    public void addReservation(Session session){
        classes.add(session);
        
    }
    
    @Override
    public void listReservation(){
        System.out.print("          RESERVATION LIST           ");
        System.out.print("-------------------------------------");
        for (int i = 0; i < classes.size() ; i++) {
                System.out.print(i+".");
                System.out.println(" " +classes.get(i));
            }
        System.out.print("-------------------------------------");
    }
    
    @Override
    public void editReservation(int index, Session session){
        classes.set(index, session);
    }
    
    @Override
    public void removeReservation(Session session){
        classes.remove(session);
    }
}
