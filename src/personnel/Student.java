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
    
    public static final double STUDENT_REGISTRATION_FEE = 150;

    public Student(Person person){
        super(person);
        this.studentID = SystemCodes.STD.toString() + nextStudentID;
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

    public static void add(Student student){
        studentList.add(student);
    }

    public static void remove(Student student){
        studentList.remove(student);
    }

    public static Student search(String email, String password){
        Student found = null;
        for (Student student : studentList) {
            if(student.getEmail().equals(email) && student.getPassword().equals(password)){
                found = student;
                break;
            }
        }
        return found;
    }

    public Session getReservation(int index){
        if(index < 0 || index >= reservedClasses.size() ){
            return null;
        }
        return reservedClasses.get(index);
    }

    @Override
    public void addReservation(Session session){
        reservedClasses.add(session);
    }

    @Override
    public boolean listReservations(){
        if (reservedClasses.size() == 0) {
            System.out.println("You do not have any reserved classes yet.");
            return false;
        }
        System.out.print("          RESERVATION LIST           \n");
        System.out.print("-------------------------------------\n");
        System.out.println();
        for (int i = 0; i < reservedClasses.size() ; i++) {
            System.out.print((i + 1) + ". ");
            System.out.println("Slot: " + reservedClasses.get(i).getSlot().showTime());
            System.out.println("Course Name: " + reservedClasses.get(i).getCourse().getCourseName());
            System.out.println("Instructor In Charge: " + reservedClasses.get(i).getInstructor().getName());
            System.out.println();
        }
        System.out.print("-------------------------------------\n");
        return true;
    }

    @Override
    public void editReservation(int index, Session session){
        if(index < 0 || index >= reservedClasses.size() ){
            return;
        }
        reservedClasses.set(index, session);
    }

    @Override
    public void editReservation(Session oldSession, Session newSession){
        int oldSessionIndex = reservedClasses.indexOf(oldSession);
        editReservation(oldSessionIndex, newSession);
    }

    @Override
    public void removeReservation(Session session){
        reservedClasses.remove(session);
    }

    public String getID() {
        return this.studentID;
    }

    @Override
    public String toString(){
        return "Student ID      : " + studentID +" \n"
             + "Student Name    : " + this.getName() +" \n"
             + "Student email   : " + this.getEmail()+" \n"
             + "Enrolled Classes: " + enrolledCourses.toString();
    }
}
