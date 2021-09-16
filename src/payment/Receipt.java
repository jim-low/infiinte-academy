package payment;

import personnel.*;
import system.Course;
import system.SystemCodes;

public class Receipt {
    private static int receiptCode = 1000;

    public static void generateRegistrationReceipt(Student student) {
        String receiptID = SystemCodes.RPT.toString() + receiptCode;
        String reference = student.getID();
        Card card = student.getCard();

        System.out.println("\t\t\t\tRegistration Receipt (Student)\n");
        System.out.println("\t\t\t\t\t\t\t\tReceipt ID: " + receiptID);
        System.out.println("\t\t\t\t\t\t\t\tDate: " + Transaction.createDate());
        System.out.println("\t\t\t\t\t\t\t\tReference ID: " + reference);
        System.out.println("Payment method: " + "Online Payment\n");
        System.out.println("Receipt for:-");

        if (student.getGender().equals("Male")) {
            System.out.print("\tMr: ");
        }
        else {
            System.out.print("\tMrs: ");
        }
        System.out.println(student.getName());

        System.out.println("\tAccount Number: " + card.getAccountNumber());
        System.out.println("\n------------------------------------ Welcome ----------------------------------------------");
        System.out.println();
        System.out.println("You have successfully registered as a Student in Infinity Academy!!!\n");
        System.out.println("Registration fee: RM" + card.getTransactionAmount());
        System.out.println("Paid fee: RM" + "xxxxx");
        System.out.printf("Account Balance: RM%.2f\n", card.getBalance());
        System.out.println("\n----------------------------- Thank You For Registering -----------------------------------\n");
        System.out.println("Note: This receipt is computer generated and no signature is required.\n\n");
        ++receiptCode;
    }

    public static void generateRegistrationReceipt(Instructor instructor) {
        String receiptID = SystemCodes.RPT.toString() + receiptCode;
        String reference = instructor.getID();
        Card card = instructor.getCard();

        System.out.println("\t\t\t\tRegistration Receipt (Instructor)\n");
        System.out.println("\t\t\t\t\t\t\t\tReceipt ID: " + receiptID);
        System.out.println("\t\t\t\t\t\t\t\tDate: " + Transaction.createDate());
        System.out.println("\t\t\t\t\t\t\t\tReference ID: " + reference);
        System.out.println("Payment method: " + "Online Payment\n");
        System.out.println("Receipt for:-");

        if (instructor.getGender().equals("Male")) {
            System.out.print("\tMr: ");
        }
        else {
            System.out.print("\tMrs: ");
        }
        System.out.println(instructor.getName());

        System.out.println("\tAccount Number: " + card.getAccountNumber());
        System.out.println("\n------------------------------------ Welcome ----------------------------------------------\n");
        System.out.println("You have successfully registered as an Instructor in Infinity Academy!!!\n");
        System.out.println("Registration fee: RM" + card.getTransactionAmount());
        System.out.println("Paid fee: RM" + "xxxxx");
        System.out.printf("Account Balance: RM%.2f\n", card.getBalance());
        System.out.println("\n----------------------------- Thank You For Registering -----------------------------------\n");
        System.out.println("Note: This receipt is computer generated and no signature is required.\n\n");

        ++receiptCode;
    }

    private static void generateCourseReceipt(Person person, String reference) {
        String receiptID = SystemCodes.RPT.toString() + receiptCode;
        System.out.println("\t\t\t\tCourse receipt\n");
        System.out.println("\t\t\t\t\t\t\t\tReceipt ID :" + receiptID);
        System.out.println("\t\t\t\t\t\t\t\tDate : " + Transaction.createDate());
        System.out.println("\t\t\t\t\t\t\t\tReference ID : " + reference);
        System.out.println("Duration of payment : " + "xxxx\n");  //not done yet
        System.out.println("Payment method : " + "Online\n");
        System.out.println("Receipt for: \n" + "\tMr/Mrs" + person.getName() + "\n"
                + "\tEmail : " + person.getEmail() +"\n"
                + "\tAccount number : " + person.getCard().getAccountNumber()  +"\n"); //change in card
        System.out.println("\n------------------------------------Welcome----------------------------------------------\n");
        System.out.println("You have suceesfully purchased the courses in Infinity Academy\n");
        System.out.printf("%-45s  %-13s  %-30s\n","Course","Course Fees","Field of courses");
        ((Student)person).listReservations();
        System.out.println("\n-------------------------------Enjoy your courses-----------------------------------------\n");
        System.out.println("Total : RM" + "xxx");
        System.out.println("Account Balance : " +person.getCard().getBalance() + "\n");
        System.out.println("Note: This receipt is computer generated and no signature is required.");
        ++receiptCode;
    }
}
