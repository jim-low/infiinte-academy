package management;

import personnel.*;
import payment.Payment;
import payment.Receipt;

import java.io.IOException;

import payment.Card;

public interface Registration {
    public static void performRegistration() throws InterruptedException, IOException {
        String accountType = promptAccountType();
        System.out.println();
        Person person = setupPerson();

        System.out.println();
        System.out.println("You will now be going through the payment process.");
        double amount = accountType.equals("Student") ? Student.STUDENT_REGISTRATION_FEE : Instructor.INSTRUCTOR_REGISTRATION_FEE;
        boolean paid = Payment.performPayment(person.getCard(), amount);

        if (!paid) {
            return;
        }

        System.out.println();
        System.out.println("Payment Successful!");

        if (accountType.equals("Student")) {
            Student student = new Student(person);
            Student.add(student);
            Payment.timeDelay(1);
            System.out.println("Generating Receipt...");
            Payment.timeDelay(2);
            Academy.clearScreen();
            Receipt.generateRegistrationReceipt(student);
        } else {
            Instructor instructor = new Instructor(person);
            Instructor.add(instructor);
            Payment.timeDelay(1);
            System.out.println("Generating Receipt...");
            Payment.timeDelay(2);
            Academy.clearScreen();
            Receipt.generateRegistrationReceipt(instructor);
        }
    }

    public static String promptAccountType() {
        System.out.println("Select Account Type:-");
        System.out.println("1. Instructor");
        System.out.println("2. Student");
        System.out.print("Your Account Type: ");

        int type = Academy.scan.nextInt();
        while (type < 1 || type > 2) {
            System.out.println("Invalid Selection. Please try again.");
            System.out.print("Your Account Type: ");
            type = Academy.scan.nextInt();
        }

        return type == 1 ?  "Instructor" : "Student";
    }

    private static Person setupPerson() throws IOException {
        System.out.print("Enter your name: ");
        String name = Academy.scan.next();

        System.out.print("Enter your email: ");
        Academy.scan.nextLine();
        String email = Academy.scan.next();

        System.out.print("Enter your password: ");
        String password = Academy.scan.next();

        System.out.println();
        System.out.println("Select your gender: ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.print("Your gender: ");
        Academy.scan.nextLine();
        String gender = Academy.scan.nextInt() == 1 ? "Male" : "Female";

        System.out.println();
        Card card = Card.setupCard();

        if (card == null) {
            System.out.println("Card Details Are Invalid!!");
            System.out.println();
            card = Card.setupCard();
        }

        return new Person(name, password, gender, email, card);
    }
}

