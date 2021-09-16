package payment;

import personnel.*;
import system.Course;
import system.Session;
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
        System.out.println("Registration fee: RM" + Student.STUDENT_REGISTRATION_FEE);
        System.out.println("Paid fee: RM" + Student.STUDENT_REGISTRATION_FEE);
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
        System.out.println("Registration fee: RM" + Instructor.INSTRUCTOR_REGISTRATION_FEE);
        System.out.println("Paid fee: RM" + Instructor.INSTRUCTOR_REGISTRATION_FEE);
        System.out.printf("Account Balance: RM%.2f\n", card.getBalance());
        System.out.println("\n----------------------------- Thank You For Registering -----------------------------------\n");
        System.out.println("Note: This receipt is computer generated and no signature is required.\n\n");

        ++receiptCode;
    }

    public static void generateCourseReceipt(Student student, Session[] sessions) {
        String receiptID = SystemCodes.RPT.toString() + receiptCode;
        System.out.println("\t\t\t\tCourse Receipt\n");
        System.out.println("\t\t\t\t\t\t\t\tReceipt ID:" + receiptID);
        System.out.println("\t\t\t\t\t\t\t\tDate: " + Transaction.createDate());
        System.out.println("\t\t\t\t\t\t\t\tReference ID: " + student.getID());
        System.out.println("Payment Method: " + "Online\n");
        System.out.println("Receipt for:-");
        System.out.printf("\t%s: %s\n", (student.getGender().equals("Male") ? "Mr" : "Ms"), student.getName());
        System.out.println("\tAccount Number: " + student.getCard().getAccountNumber());
        System.out.println("\n------------------------------------Welcome----------------------------------------------\n");
        System.out.println("You have successfully purchased the following courses in Infinity Academy!!!");
        System.out.printf("   %-40s %s\n", "Course Name", "Course Fee");
        double total = 0;
        for (int i = 0; i < sessions.length; i++) {
            Course course = sessions[i].getCourse();
            System.out.printf("%d. %-40s RM%.2f\n", (i+1), course.getCourseName(), course.getCourseFee());
            double commission = course.getCourseFee() * 0.05;
            sessions[i].getInstructor().getCard().cashIn(commission);
            total += course.getCourseFee();
        }
        System.out.println();
        System.out.printf("Total: RM%.2f\n", total);
        student.getCard().cashOut(total);
        System.out.printf("Account Balance: RM%.2f\n", student.getCard().getBalance());
        System.out.println("\n-------------------------------Enjoy your courses-----------------------------------------\n");
        System.out.println("Note: This receipt is computer generated and no signature is required.");
        ++receiptCode;
    }
}
