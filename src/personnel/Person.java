package personnel;

public abstract class Person {
    // type your code here, all the best
    protected String name;
    protected String icNo;
    protected String email;
    protected boolean paidFee;

    public Person(){
        
    }
    
    public Person(String name) {
        this.name = name;
    }
    
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

}

