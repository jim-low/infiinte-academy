package personnel;

import java.util.Scanner;
import management.Academy;

public class Person {
//all private and put getter and setter
    private String name;
    private String gender;
    private String email;
    private boolean paidFee;

    public Person(){
        this("","","");
    }
    
    public Person(Person person){
        this(person.name,person.gender,person.email);
    }
    
    public Person(String name, String email) {
        this(name,"",email);
        
    }
    
    public Person(String name, String gender, String email) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.paidFee = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public boolean hasPaidFee(){
        return false;
    }

    public void setPaidFee(boolean paidFee) {
        this.paidFee = paidFee;
    }
    
    
    
    
    public static void main(String[] args) {
        Person p1 = new Person () {};

        System.out.print(" Enter Your Full Name                : ");
        p1.name = Academy.scan.nextLine();                
        System.out.print(" Enter Your Gender (Male / Female)   : ");
        p1.gender = Academy.scan.nextLine();
        System.out.print(" Enter Your Full Email Address       : ");
        p1.email = Academy.scan.nextLine();
        System.out.print(" Have you paid your fees? (Yes / No) : ");
        p1.paidFee = Academy.scan.equals(p1);
        
        
    }
    
    
}

