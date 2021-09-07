package payment;
import management.Reservation;
import personnel.Instructor;
import personnel.Person;
import personnel.Student;
import system.SystemCodes;

public interface Receipt {
    static int receiptCode = 1000;

    public static <T> void generateReceipt(Person person, Class<T> transactionType) {
        String referenceID = "";
        if(person.getClass().equals(Student.class)) {
            referenceID = ((Student) person).getID();
        } else if(person.getClass().equals(Instructor.class)) {
            referenceID = ((Instructor) person).getID();
        }

        if (transactionType.equals(Student.class)) {
            generateRegistrationReceipt(person, referenceID);
        } else if (transactionType.equals(Instructor.class)) {
            generateRegistrationReceipt(person, referenceID);
        } else {
            generateCourseReceipt(person, referenceID);
        }
    }

    static void generateRegistrationReceipt(Person person, String reference) {
        String receiptID = SystemCodes.RPT.toString() + receiptCode;
        System.out.println("\t\t\t\tRegistration Receipt (Student)\n");
        System.out.println("\t\t\t\t\t\t\t\tReceipt ID :" + receiptID);
        System.out.println("\t\t\t\t\t\t\t\tDate : " + Transaction.createDate());
        System.out.println("\t\t\t\t\t\t\t\tReference ID : " + reference);
        System.out.println("Duration of payment : " + "xxxx\n"); //not done yet
        System.out.println("Payment method : " + "Online\n");
        System.out.println("Receipt for: \n" + "\tMr/Mrs :" + person.getName() + "\n"
                + "\tEmail : " + person.getEmail()+"\n"
                + "\tAccount number : " + person.getCard().getAccountNumber); //change in card
        System.out.println("\n------------------------------------Welcome----------------------------------------------\n");
        System.out.println("You have successfully registered as a student in Infinity Academy!!!\n");
        System.out.println("Registration fee : RM" + person.getCard().getTransactionAmount() + "\n"
                          + "Paid fee : RM" + "xxxxx\n");
        System.out.println("Account Balance : " + person.getCard().getBalance() + "\n"); //chnage in card
        System.out.println("\n-----------------------------Thank you for registering-----------------------------------\n");
        System.out.println("Note: This receipt is computer generated and no signature is required.\n\n");
    }

    static void generateCourseReceipt(Person person, String reference) {
        String receiptID = SystemCodes.RPT.toString() + receiptCode;
        System.out.println("\t\t\t\tCourse receipt\n");
        System.out.println("\t\t\t\t\t\t\t\tReceipt ID :" + receiptID);
        System.out.println("\t\t\t\t\t\t\t\tDate : " + Transaction.createDate());
        System.out.println("\t\t\t\t\t\t\t\tReference ID : " + reference);
        System.out.println("Duration of payment : " + "xxxx\n");  //not done yet
        System.out.println("Payment method : " + "Online\n");
        System.out.println("Receipt for: \n" + "\tMr/Mrs" + person.getName() + "\n"
                + "\tEmail : " + person.getEmail() +"\n"
                + "\tAccount number : " + person.getCard().getAccountNumber  +"\n"); //change in card
        System.out.println("\n------------------------------------Welcome----------------------------------------------\n");
        System.out.println("You have suceesfully purchased the courses in Infinity Academy\n");
        System.out.printf("%-45s  %-13s  %-30s\n","Course","Course Fees","Field of courses");
        ((Student)person).listReservations();
        System.out.println("\n-------------------------------Enjoy your courses-----------------------------------------\n");
        System.out.println("Total : RM" + "xxx");
        System.out.println("Account Balance : " +person.getCard().getBalance() + "\n");
        System.out.println("Note: This receipt is computer generated and no signature is required.");
    }
}
