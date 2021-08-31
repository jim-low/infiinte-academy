package personnel;

import java.util.ArrayList;
import system.*;
import management.Reservation;
import management.Academy;

public class Student extends Person implements Reservation {
    private ArrayList<Course> enrolledCourses = new ArrayList<>();
    private ArrayList<Session> reservedClasses = new ArrayList<>();
    private static ArrayList<Student> studentList = new ArrayList<>();
    private String studentID;
    private static int nextStudentID = 1000;

    public Student(Person person, String studentID){
        super(person);
        this.studentID = Code.STD.toString()+nextStudentID;
        ++nextStudentID;
    }

    public void enrollCourse(Course course){
        enrolledCourses.add(course);
    }

    public void listCourse(){
        System.out.print("          COURSE LIST           \n");
        System.out.print("--------------------------------\n");
        for (int i = 0; i < enrolledCourses.size() ; i++) {
                System.out.print(i+1 +".");
                System.out.println(" " +enrolledCourses.get(i));
            }
            System.out.print("--------------------------------\n");
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
        for(int i=0; i < enrolledCourses.size(); i++){
            if(enrolledCourses.get(i).getCourseName().equals(courseName)){
                enrolledCourses.remove(enrolledCourses.get(i));
            }
        }
    }
    
    @Override
    public String toString(){
        return "Instructor ID      :" + studentID +" \n"
             + "Instructor Name    : " + this.getName() +" \n"
             + "Instructor email   : " + this.getEmail()+" \n"
             + enrolledCourses.toString();
    }
    
    public static void add(Student student){
        studentList.add(student);
    }
    
    public static void remove(Student student){
        studentList.remove(student);
    }
    
    public static Student search(String email, String password){
        Student found = null;
        for (Student student : studentList) {
            if(student.getName().equals(email) && student.getPassword().equals(password)){
                found = student;
                break;
            }
        }
        return found;
    }

    @Override
    public void addReservation(Session session){
        reservedClasses.add(session);
    }

    @Override
    public void listReservation(){
        //list session array
        System.out.print("          RESERVATION LIST           \n");
        System.out.print("-------------------------------------\n");
        for (int i = 0; i < reservedClasses.size() ; i++) {
                System.out.print(i+1 +".");
                System.out.println(" " +reservedClasses.get(i));
            }
            System.out.print("--------------------------------\n");
    }
    
    public Session getReservation(int index){
        if(index < 0 || index >= reservedClasses.size() ){
            return null;
        }
        return reservedClasses.get(index);
    }
    
    @Override
    public void editReservation(int index, Session session){
        if(index < 0 || index >= reservedClasses.size() ){
            return;
        }
        reservedClasses.set(index, session);
    }

    @Override
    public void removeReservation(Session session){
        reservedClasses.remove(session);
    }
}
