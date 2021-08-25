package management;

import java.util.Scanner;

import personnel.Instructor;
import personnel.Person;
import personnel.Student;
import system.Course;
import system.Session;
import system.Slot;

interface LoginFlags {
    final static int NO_LOGIN = 0;
    final static int STUDENT_LOGIN = 1;
    final static int INSTRUCTOR_LOGIN = 2;
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
            case LoginFlags.INSTRUCTOR_LOGIN:
                parseInstructorChoice();
                break;
            case LoginFlags.STUDENT_LOGIN:
                parseStudentChoice();
                break;
        }
    }

    private static void parseInstructorChoice() {
        switch (choice) {
            case 1:
                Session createdSession = Session.createSession(loggedInInstructor);
                loggedInInstructor.addReservation(createdSession);
                break;
            case 2:
                loggedInInstructor.listReservation();
                break;
            case 3:
                // 1. get index
                // 2. confirm object at index
                // 3. create new session
                // 4. set new session
                break;
            case 4:
                Session selectedSession = promptSession(loggedInInstructor, Instructor.class);
                char confirmation = confirmSession(selectedSession);

                if (confirmation == 'y') {
                    System.out.println("Session Removal Aborted");
                    return;
                }

                loggedInInstructor.removeReservation(selectedSession);
                break;
        }
    }

    private static char confirmSession(Session selectedSession) {
        System.out.println(selectedSession.toString());
        System.out.print("Confirm current Session? ");
        char confirm = scan.next().charAt(0);
        return confirm;
    }

    private static <T> Session promptSession(Object person, Class<T> type) {
        Student student = null;
        Instructor instructor = null;

        if (type.equals(Student.class)) {
            student = ((Student)person);
            student.listReservation();
        }
        else {
            instructor = ((Instructor)person);
            instructor.listReservation();
        }

        System.out.print("Enter the Session to remove(0 to abort): ");
        int selection = scan.nextInt();

        return student == null ? instructor.getReservation(selection - 1) : student.getReservation(selection - 1);
    }

    private static void parseStudentChoice() {
        switch (choice) {
            case 1:
                Session.listReservedSessions();
                System.out.println("Select your preferred session: ");
                int selection = scan.nextInt();

                Session selectedSession = Session.getReservedSession(selection - 1);
                System.out.println(selectedSession.toString());
                char confirmation = confirmSession(selectedSession);

                if (confirmation != 'y') {
                    System.out.println("Aborted.");
                    return;
                }

                loggedInStudent.addReservation(selectedSession);
                break;
            case 2:
                loggedInStudent.listReservation();
                break;
            case 4:
                Session selectedSession = promptSession(loggedInStudent, Student.class);
                char confirmation = confirmSession(selectedSession);

                if (confirmation == 'y') {
                    System.out.println("Aborted.");
                    return;
                }

                loggedInStudent.removeReservation(selectedSession);
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
            loggedInStudent = Person.search(credentials[0], credentials[1], Student.class);
            loginFlag = loggedInStudent != null ? LoginFlags.STUDENT_LOGIN : LoginFlags.NO_LOGIN;
        }
        else if (accountType.equals("Instructor")) {
            loggedInInstructor = Person.search(credentials[0], credentials[1], Student.class);
            loginFlag = loggedInInstructor != null ? LoginFlags.INSTRUCTOR_LOGIN : LoginFlags.NO_LOGIN;
        }

        if (loginFlag == LoginFlags.NO_LOGIN) {
            System.out.printf("Could not find registration with name '%s' in our database.\n", credentials[0]);
            return;
        }

        System.out.println("Successfully logged in!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    private static String[] promptAccountCredentials() {
        System.out.print("Enter your email: ");
        String email = scan.nextLine();

        System.out.print("Enter your password: ");
        String password = new String(System.console().readPassword());
        return new String[]{ email, password };
    }

    private static void showAcademyBanner() {
        System.out.println("__      __   _                    _         ___       __ _      _ _            _              _");
        System.out.println("\\ \\    / /__| |__ ___ _ __  ___  | |_ ___  |_ _|_ _  / _(_)_ _ (_) |_ _  _    /_\\  __ __ _ __| |___ _ __ _  _");
        System.out.println(" \\ \\/\\/ / -_) / _/ _ \\ '  \\/ -_) |  _/ _ \\  | || ' \\|  _| | ' \\| |  _| || |  / _ \\/ _/ _` / _` / -_) '  \\ || |");
        System.out.println("  \\_/\\_/\\___|_\\__\\___/_|_|_\\___|  \\__\\___/ |___|_||_|_| |_|_||_|_|\\__|\\_, | /_/ \\_\\__\\__,_\\__,_\\___|_|_|_\\_, |");
        System.out.println("                                                                      |__/                               |__/");
    }
}

