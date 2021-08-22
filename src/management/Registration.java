package management;

import personnel.*;
import system.Course;
import payment.Payment;

public interface Registration {
    public static void performRegistration() {
        String accountType = promptAccountType();
        Person basicInfo = setupPerson();

        System.out.println("You will now be going through the payment process.");
        boolean paid = Payment.performPayment(accountType, Payment.generateRandomAmount());
        if (!paid) {
            System.out.println("Payment Unsuccessful. Account not registered.");
            return;
        }

        if (accountType.equals("Student")) {
            Student student = setupStudent(basicInfo);
            Student.add(student);
        } else {
            Instructor instructor = setupInstructor(basicInfo);
            Instructor.add(instructor);
        }
    }

    private static String promptAccountType() {
        System.out.println("Select Account Type:-");
        System.out.println("1. Instructor");
        System.out.println("2. Student");
        System.out.println();
        System.out.print("Your Account Type: ");

        int type = Academy.scan.nextInt();
        while (type < 1 || type > 2) {
            System.out.println("Invalid Selection. Please try again.");
            System.out.print("Your Account Type: ");
            type = Academy.scan.nextInt();
        }

        return type == 1 ?  "Instructor" : "Student";
    }

    private static Instructor setupInstructor(Person person) {
        System.out.print("Now you will be brought to setup your course.");
        Course course = Course.setupCourse();

        return new Instructor(person, course);
    }

    static void printQualificationsMenu() {
        System.out.println("Select your latest qualification:-");
        System.out.println("1. SPM");
        System.out.println("2. Diploma");
        System.out.println("3. Degree");
        System.out.println("4. Masters");
        System.out.println("5. PhD");
    }

    private static Student setupStudent(Person person) {
        return new Student(person);
    }

    private static Person setupPerson() {
        System.out.print("Enter your name: ");
        String name = Academy.scan.nextLine();

        System.out.println("Select your gender: ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println();
        System.out.print("Your gender: ");
        String gender = Academy.scan.nextInt() == 1 ? "Male" : "Female";

        System.out.print("Enter your email: ");
        String email = Academy.scan.next();

        return new Person(name, gender, email);
    }

}

