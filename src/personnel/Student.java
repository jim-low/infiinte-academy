package personnel;

import java.util.ArrayList;
import java.util.Scanner;
import system.*;
import management.Reservation;
import management.Academy;


//interface Reservation{
//    public void addReservation();
//    public void listReservations();
//    public void editReservationDetails();
//    public void removeReservation();
//}

public class Student extends Person implements Reservation {
    private Course[] enrolledCourses;
    private Session[] reservedClasses;
    private static ArrayList<Student> studentList;
    private String studentID;
    
    public Student(Person person, String studentID){
        super(person);
        this.studentID = studentID;
    }
    
    public void enrollCourse(){
        
        for (int i = 0; i < enrolledCourses.length; i++) {
            System.out.print(" Enter Course Name              : ");
            String courseName = Academy.scan.nextLine();
            System.out.print(" Enter Course Session           : ");
            String session = Academy.scan.nextLine();
            System.out.print(" Enter Session Slot   : ");
            String slot = Academy.scan.nextLine();
            System.out.print(" Enter Your Full Name : ");
            String name = Academy.scan.nextLine();
        
        }
        
//         for (int i = 0; i < courseList.length(); i++) {
//            if(courseList.get(i).getCourseName()== courseName){
//                Student student = new Student(name);\
//                System.out.println("Successfully enrolled !");
//            }
//        }
 
    }
    
    public void listCourse(){
        System.out.print("          COURSE LIST           ");
        System.out.print("--------------------------------");
        for (int i = 0; i <enrolledCourses.length ; i++) {
                System.out.print(i+".");
                System.out.println(" " +enrolledCourses[i]);
            }
            System.out.print("--------------------------------");
        }
    
    public void changeCourse(){
        
    }
    
    public void dropCourse(){
        
        System.out.print("Enter Course Name to Delete : ");
        String courseName = Academy.scan.nextLine();
        System.out.print(" Enter Your Full Name : ");
        String name = Academy.scan.nextLine();
        //for (int i = 0; i < courseList.size(); i++) {
            
        
    }
    
    public String toString(){
        return ("String");
    }
}

