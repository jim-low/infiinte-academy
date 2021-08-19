package personnel;

import java.util.ArrayList;
import system.*;
import management.Reservation;
import management.Academy;

public class Student extends Person implements Reservation {
    private ArrayList<Course> enrolledCourses = new ArrayList<>();
    private ArrayList<Session> reservedClasses = new ArrayList<>();
    private static ArrayList<Student> studentList = new ArrayList<>();
    private int studentID;
    private static int nextStudentID = 1;
    
    public Student(Person person, int studentID){
        super(person);
        this.studentID = nextStudentID;
        ++nextStudentID;
    }
    
    public void enrollCourse(Course course){
        enrolledCourses.add(course);
    }
    
    public void listCourse(){
        System.out.print("          COURSE LIST           ");
        System.out.print("--------------------------------");
        for (int i = 0; i < enrolledCourses.size() ; i++) {
                System.out.print(i+".");
                System.out.println(" " +enrolledCourses.get(i));
            }
            System.out.print("--------------------------------");
        }
  
    public void changeCourse(String courseName){
        for(int i=0; i < enrolledCourses.size(); i++){
            if(enrolledCourses.get(i).getCourseName().equals(courseName)){
                System.out.println("Enter your updated course :");
                String updatedC = Academy.scan.nextLine();
                enrolledCourses.get(i).setCourseName(updatedC);
            }
        }
    }
    
    public void dropCourse(String courseName){
        //call 2 methods in one line, course der getCourseName and euqals
        for(int i=0; i < enrolledCourses.size(); i++){
            if(enrolledCourses.get(i).getCourseName().equals(courseName)){
                enrolledCourses.remove(enrolledCourses.get(i));
            }
        }
    }

    public static Student search(String name){
        Student found = null;
        for (Student student : studentList) {
            if(student.getName().equals(name)){
                found = student;
                break;
            }
        }
        return found;
    }
    
    @Override
    public String toString(){
        return super.toString() + String.format("Student ID : %s \n", studentID);
    }
    
    public void addReservation(Session session){
        reservedClasses.add(session);
    }
    
    @Override
    public void listReservation(){
        //list session array
        System.out.print("          RESERVATION LIST           ");
        System.out.print("-------------------------------------");
        for (int i = 0; i < reservedClasses.size() ; i++) {
                System.out.print(i+".");
                System.out.println(" " +reservedClasses.get(i));
            }
            System.out.print("--------------------------------");
    }
    
    public void editReservation(int index, Session session){
        reservedClasses.set(index, session);
    }
    
    public void removeReservation(Session session){
        reservedClasses.remove(session);
    }
}

