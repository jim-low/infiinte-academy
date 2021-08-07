package management;

import payment.Payment;
import personnel.Instructor;
import personnel.Student;

public interface Registration {
    public static void performRegistration() {
        // 1. prompt account type
        // 2. payment
        // 3. add into list
        String accountType = promptAccountType();
        // boolean paid = true;
        // if (paid)
        //     Instructor.add(new Instructor());
        // Student.add(new Student());
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
}

