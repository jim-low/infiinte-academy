package personnel;
import java.util.ArrayList;
import system.*;
import management.Reservation;

public class Student implements Reservation{
    // type your code here, all the best
    private Course[] enrolledCourses;
    private Session[] reservedClasses;
    private static ArrayList<Student> studentList;
    
    public Student(){
        
    }
    public void enrollCourse(){
        
    }
    
    public void listCourse(){
        
    }
    
    public void changeCourse(){
        
    }
    
    public void dropCourse(){
        
    }
    
    public String toString(){
        return ("String");
    }
    
    public static void add(Student student){
        studentList.add(student);
    }
    
}

