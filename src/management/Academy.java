package management;

import java.util.Scanner;

import personnel.Instructor;
import personnel.Student;

interface LoginFlags {
    final static int NO_LOGIN = 0;
    final static int ADMIN_LOGIN = 1;
    final static int STUDENT_LOGIN = 2;
    final static int INSTRUCTOR_LOGIN = 3;
}

interface Menu {
    static void mainMenu() {
        System.out.println("1. Register An Account In Infinity Academy");
        System.out.println("2. Log In To Infinity Academy");
        System.out.println("3. About Infinity Academy");
        System.out.println("4. Meet The Team");
    }

    static void instructorMenu() {
        System.out.println("1. Reserve a class session");
        System.out.println("2. List reserved sessions");
        System.out.println("3. Change reserved session information");
        System.out.println("4. Remove session");
    }

    static void studentMenu() {
        System.out.println("1. Reserve an instructor's class");
        System.out.println("2. List reserved classes");
        System.out.println("3. Change class");
        System.out.println("4. Remove class");
    }
}

public class Academy {
    private static Student loggedInStudent = null;
    private static Instructor loggedInInstructor = null;
    private static int loginFlag = LoginFlags.NO_LOGIN;
    private static int choice;
    private static boolean sessionEnd = false;

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (!sessionEnd) {
            showAcademyBanner();
            showMenu();
            System.out.println();
            System.out.print("Your Choice: ");
            choice = scan.nextInt();
            parseChoice();
            System.out.println();
        }
    }

    private static void showMenu() {
        switch (loginFlag) {
            case LoginFlags.NO_LOGIN:
                Menu.mainMenu();
                break;
            case LoginFlags.INSTRUCTOR_LOGIN:
                Menu.instructorMenu();
                break;
            case LoginFlags.STUDENT_LOGIN:
                Menu.studentMenu();
                break;
        }
    }

    private static void parseChoice() {
        switch (loginFlag) {
            case LoginFlags.NO_LOGIN:
                parseFirstTimeLogin();
                break;
        }
    }

    private static void parseFirstTimeLogin() {
        switch (choice) {
            case 1:
                Registration.performRegistration();
                break;
            case 2:
                logIn();
                break;
            case 3:
                System.out.println("Infinity Academy is an academy where you learn infinite things");
                break;
            case 4:
                System.out.println("our team has 4 people, you may visit us on our github project at: https://github.com/jim-low/infinity-academy");
                System.out.println("# shame less self sponser");
                break;
        }
    }

    private static void logIn() {
        String accountType = Registration.promptAccountType();
        String[] credentials = promptAccountCredentials();

        if (accountType.equals("Student")) {
            loginStudent(credentials);
        }
        else if (accountType.equals("Instructor")) {
            loginInstructor(credentials);
        }
    }

    private static void loginInstructor(String[] credentials) {
        loggedInInstructor = Instructor.search(credentials[0], credentials[1]);
        if (loggedInInstructor == null) {
            System.out.printf("Could not find instructor with name '%s' in our database.\n", credentials[0]);
            loginFlag = LoginFlags.NO_LOGIN;
        }
        else {
            loginFlag = LoginFlags.INSTRUCTOR_LOGIN;
        }
    }

    private static void loginStudent(String[] credentials) {
        loggedInStudent = Student.search(credentials[0], credentials[1]);
        if (loggedInStudent == null) {
            System.out.printf("Could not find student with name '%s' in our database.\n", credentials[0]);
            loginFlag = LoginFlags.NO_LOGIN;
        }
        else {
            loginFlag = LoginFlags.STUDENT_LOGIN;
        }
    }

    private static String[] promptAccountCredentials() {
        System.out.print("Enter your name: ");
        String name = scan.nextLine();

        System.out.print("Enter your password: ");
        String password = new String(System.console().readPassword());
        return new String[]{ name, password };
    }

    private static void showAcademyBanner() {
        System.out.println("__      __   _                    _         ___       __ _      _ _            _              _");
        System.out.println("\\ \\    / /__| |__ ___ _ __  ___  | |_ ___  |_ _|_ _  / _(_)_ _ (_) |_ _  _    /_\\  __ __ _ __| |___ _ __ _  _");
        System.out.println(" \\ \\/\\/ / -_) / _/ _ \\ '  \\/ -_) |  _/ _ \\  | || ' \\|  _| | ' \\| |  _| || |  / _ \\/ _/ _` / _` / -_) '  \\ || |");
        System.out.println("  \\_/\\_/\\___|_\\__\\___/_|_|_\\___|  \\__\\___/ |___|_||_|_| |_|_||_|_|\\__|\\_, | /_/ \\_\\__\\__,_\\__,_\\___|_|_|_\\_, |");
        System.out.println("                                                                      |__/                               |__/");
    }
}

