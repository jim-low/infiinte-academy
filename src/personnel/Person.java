package personnel;

public abstract class Person {
    // type your code here, all the best
    protected String name;
    protected String icNo;
    protected String gender;
    protected String email;
    protected boolean paidFee;

    public Person(){
        this("", "", "", "");
    }

    public Person(String name) {
        this(name, "", "", "");
    }

    public Person(String name, String email) {
        this(name, "", "", email);
    }

    public Person(String name, String icNo, String gender, String email) {
        this.name = name;
        this.icNo = icNo;
        this.gender = gender;
        this.email = email;
        this.paidFee = false;
    }
}

