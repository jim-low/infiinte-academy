package personnel;

import management.Academy;

public class Person {
    private String name;
    private String gender;
    private String email;
    private boolean paidFee;

    public Person(){
        this("", "", "");
    }

    public Person(Person person){
        this(person.name, person.gender, person.email);
    }

    public Person(String name) {
        this(name, "", "");
    }

    public Person(String name, String email) {
        this(name, "", email);

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

    public boolean hasPaidFee(){
        return false;
    }

    public void setPaidFee(boolean paidFee) {
        this.paidFee = paidFee;
    }

    public static <T> T search (String name, Class<T> type){
        T person = null;

        if(type.equals(Student.class)){
            person = type.cast(Student.search(name));
        } else if(type.equals(Instructor.class)){
            person = type.cast(Instructor.search(name));
        }

        return person;
    }
 }
