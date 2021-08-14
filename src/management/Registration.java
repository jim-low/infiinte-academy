package management;

import personnel.*;

public interface Registration {
    public static void performRegistration() {
        // 1. prompt account type
        // 2. payment
        // 3. add into list
        String accountType = promptAccountType();
        Person basicInfo = setupPerson();
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

    private static Person setupPerson() {
        // Person needs the following:
        // - name
        // - gender (preferrably able to select rather than type)
        // - email
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

