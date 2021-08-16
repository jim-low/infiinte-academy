package personnel;

import java.util.ArrayList;
import system.*;
import management.Reservation;
import management.Academy;


//interface Reservation{
//    public void addReservation();
//    public void listReservations();
//    public void editReservationDetails();
//    public void removeReservation();
//}

public class Instructor extends Person implements Reservation {
    private String[] qualifications = new String[10]; //add, list, edit, remove
    private Course course;
    private Session[] classes;
    private static ArrayList<Instructor> instructorList; 
    private String instructorID;
    private boolean choice;
    
    public Instructor(Person person, String instructorID){
        super(person);
        this.instructorID = instructorID;
    }
    
    public Instructor(String qualifications){
        
    }
    
    public Instructor(Course course){
        
    }
    
    public Instructor(String qualifications, Course course){
        
    }
    
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void addQualification(){
        do{    
            for (int i = 0; i < qualifications.length ; i++) {
                System.out.print("Enter your Highest Qualification :");
                qualifications[i] =Academy.scan.nextLine();
                System.out.print("Do you want to continue adding your Qualifications ?");
                choice = Academy.scan.nextBoolean();
                
            }
        }while(choice == false );
        System.out.print("You have reached the input maximum of 10.");         
    }
    
    public void editQualification(){
       
    }
    
    public void listQualification(){
        System.out.print("Your Qualifications :");
        for (int i = 0; i < qualifications.length; i++) {
            System.out.println(qualifications[i]);
        }
    }
    
    public void removeQualification(){
       
    }
    
    public String toString(){
        return "";
    }
    
    public static void add(Instructor instructor){
        instructorList.add(instructor);
    }
    
    public static void remove(Instructor instructor){
        instructorList.remove(instructor);
    }   
}
