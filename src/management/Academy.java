package management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import payment.Receipt;

import personnel.*;
import system.*;

interface LoginFlags {
    final static int NO_LOGIN = 0;
    final static int STUDENT_LOGIN = 1;
    final static int INSTRUCTOR_LOGIN = 2;
    final static int EXIT = 3;
}

interface Menu {
    static void mainMenu() {
        System.out.println("1. Register An Account In Infinity Academy");
        System.out.println("2. Log In To Infinity Academy");
        System.out.println("3. Exit");
    }

    static void instructorMenu() {
        System.out.println("1. Reserve a class session");
        System.out.println("2. List reserved sessions");
        System.out.println("3. Change reserved session information");
        System.out.println("4. Remove session");
        System.out.println("5. Log Out");
    }

    static void studentMenu() {
        System.out.println("1. Reserve an instructor's class");
        System.out.println("2. List reserved classes");
        System.out.println("3. Change class");
        System.out.println("4. Remove class");
        System.out.println("5. Log Out");
    }
}

public class Academy {
    private static Student loggedInStudent = null;
    private static Instructor loggedInInstructor = null;
    private static int loginFlag = LoginFlags.NO_LOGIN;
    private static int choice;

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException, IOException {
        while (loginFlag != LoginFlags.EXIT) {
            clearScreen();
            showAcademyBanner();
            showMenu();
            System.out.println();
            System.out.print("Your Choice: ");
            choice = scan.nextInt();
            System.out.println();
            parseChoice();
            pressEnter();
        }
    }

    public static void pressEnter() {
        if (loginFlag == LoginFlags.EXIT) {
            return;
        }
        System.out.println();
        System.out.println();
        System.out.print("Press Enter To Continue");
        scan.nextLine();
        scan.nextLine();
    }

    public static void clearScreen() throws InterruptedException, IOException {
        try {
            // windows clear screen command
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch (InterruptedException | IOException e) {
            // linux clear screen command
            new ProcessBuilder("clear").inheritIO().start().waitFor();
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

    private static void parseChoice() throws InterruptedException, IOException {
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
                if (createdSession == null) {
                    System.out.println("Aborted");
                    return;
                }
                loggedInInstructor.addReservation(createdSession);
                System.out.println();
                System.out.println("Class Session Successfully Placed!");
                break;
            case 2:
                loggedInInstructor.listReservations();
                break;
            case 3:
                Session selectedEditSession = selectEditSession(Instructor.class);
                if (selectedEditSession == null) {
                    System.out.println("Aborted.");
                    return;
                }

                Session newSession = Session.createSession(loggedInInstructor);
                if (!confirmSession(newSession)) {
                    System.out.println("Aborted.");
                    return;
                }

                loggedInInstructor.editReservation(selectedEditSession, newSession);
                System.out.println("Class Session Successfully Edited!");
                break;
            case 4:
                Session selectedRemoveSession = promptSession(Instructor.class);
                if (!confirmSession(selectedRemoveSession)) {
                    System.out.println("Aborted.");
                    return;
                }

                loggedInInstructor.removeReservation(selectedRemoveSession);
                System.out.println("Class Session Successfully Removed!");
                break;
            case 5:
                logOut();
                break;
        }
    }

    private static <T> Session selectEditSession(Class<T> type) {
        Session selectedSession = promptSession(type);
        if (selectedSession == null) {
            return null;
        }

        System.out.println();
        return confirmSession(selectedSession) ? selectedSession : null;
    }

    private static boolean confirmSession(Session session) {
        if (session == null) {
            return false;
        }

        System.out.println(session.toString());
        System.out.print("Confirm session? ");
        char confirm = scan.next().charAt(0);
        return confirm == 'y';
    }

    private static <T> Session promptSession(Class<T> type) {
        Student student = null;
        Instructor instructor = null;
        boolean hasClasses = false;

        if (type.equals(Student.class)) {
            student = loggedInStudent;
            hasClasses = student.listReservations();
        }
        else if (type.equals(Instructor.class)) {
            instructor = loggedInInstructor;
            hasClasses = instructor.listReservations();
        }

        if (!hasClasses) {
            return null;
        }

        System.out.print("Enter Session number(0 to abort): ");
        int selection = scan.nextInt();

        return student != null ? student.getReservation(selection - 1) : instructor.getReservation(selection - 1);
    }

    private static void parseStudentChoice() {
        switch (choice) {
            case 1:
                Session[] chosenSessions = reserveStudentClasses();
                if(chosenSessions.length != 0){
                    Receipt.generateCourseReceipt(loggedInStudent, chosenSessions);
                }
                break;

            case 2:
                loggedInStudent.listReservations();
                break;
            case 3:
                Session selectedEditSession = promptSession(Student.class);
                if (!confirmSession(selectedEditSession)) {
                    return;
                }
                loggedInStudent.getCard().cashIn(selectedEditSession.getCourse().getCourseFee());

                Session newSelectedSession = selectReservedSession();
                if (!confirmSession(newSelectedSession)) {
                    System.out.println("Aborted.");
                    return;
                }

                loggedInStudent.editReservation(selectedEditSession, newSelectedSession);
                loggedInStudent.getCard().cashOut(newSelectedSession.getCourse().getCourseFee() * 1.5);
                System.out.println("Successfully Edited Class!");
                break;
            case 4:
                Session selectedRemoveSession = promptSession(Student.class);
                System.out.println();
                if (!confirmSession(selectedRemoveSession)) {
                    System.out.println("Aborted.");
                    return;
                }

                loggedInStudent.removeReservation(selectedRemoveSession);
                System.out.println("Successfully Removed Class!");
                break;
            case 5:
                logOut();
                break;
        }
    }

    private static Session[] reserveStudentClasses() {
        ArrayList<Session> sessions = new ArrayList<>();
        char choice = 'Y';
        while(Character.toUpperCase(choice) == 'Y'){
            Session selectedSession = selectReservedSession();
            System.out.println();
            if (!confirmSession(selectedSession)) {
                System.out.println("Aborted.");
                return null;
            }
            loggedInStudent.addReservation(selectedSession);
            sessions.add(selectedSession);
            System.out.println("Successfully Added Class!");
            
            System.out.print("Do you want to continue reserving more classes? (Y/N): ");
            choice = scan.next().charAt(0);
        }
        return sessions.toArray(new Session[0]);
    }

    private static void logOut() {
        loggedInInstructor = null;
        loggedInStudent = null;
        loginFlag = LoginFlags.NO_LOGIN;
    }

    private static Session selectReservedSession() {
        Session.listReservedSessions();
        System.out.print("Select your preferred session: ");
        int selection = scan.nextInt();
        return Session.getReservedSession(selection - 1);
    }

    private static void parseFirstTimeLogin() throws InterruptedException, IOException {
        switch (choice) {
            case 1:
                Registration.performRegistration();
                break;
            case 2:
                logIn();
                break;
            case 3:
                exitSystem();
        }
    }

    private static void logIn() {
        String accountType = Registration.promptAccountType();
        System.out.println();
        String[] credentials = promptAccountCredentials();

        if (accountType.equals("Student")) {
            loggedInStudent = Student.search(credentials[0], credentials[1], Student.class);
            loginFlag = loggedInStudent != null ? LoginFlags.STUDENT_LOGIN : LoginFlags.NO_LOGIN;
        }
        else if (accountType.equals("Instructor")) {
            loggedInInstructor = Instructor.search(credentials[0], credentials[1], Instructor.class);
            loginFlag = loggedInInstructor != null ? LoginFlags.INSTRUCTOR_LOGIN : LoginFlags.NO_LOGIN;
        }

        if (loginFlag == LoginFlags.NO_LOGIN) {
            System.out.printf("Could not find registration with email '%s' in our database.\n", credentials[0]);
            return;
        }

        System.out.println("Successfully logged in!");
    }

    private static String[] promptAccountCredentials() {
        System.out.print("Enter your email: ");
        String email = scan.next();

        System.out.print("Enter your password: ");
        String password = scan.next();
        return new String[]{ email, password };
    }

    private static void showAcademyBanner() {
        System.out.println("__      __   _                    _         ___       __ _      _ _            _              _");
        System.out.println("\\ \\    / /__| |__ ___ _ __  ___  | |_ ___  |_ _|_ _  / _(_)_ _ (_) |_ _  _    /_\\  __ __ _ __| |___ _ __ _  _");
        System.out.println(" \\ \\/\\/ / -_) / _/ _ \\ '  \\/ -_) |  _/ _ \\  | || ' \\|  _| | ' \\| |  _| || |  / _ \\/ _/ _` / _` / -_) '  \\ || |");
        System.out.println("  \\_/\\_/\\___|_\\__\\___/_|_|_\\___|  \\__\\___/ |___|_||_|_| |_|_||_|_|\\__|\\_, | /_/ \\_\\__\\__,_\\__,_\\___|_|_|_\\_, |");
        System.out.println("                                                                      |__/                               |__/");

        if (loginFlag == LoginFlags.NO_LOGIN) {
            return;
        }

        Person loggedInPerson = loggedInStudent == null ? loggedInInstructor : loggedInStudent;
        System.out.println();
        System.out.printf("                    Name: %s\n", loggedInPerson.getName());
        System.out.println();
    }

    private static void exitSystem() {
        System.out.println(" ________             __                                                         __");
        System.out.println("/_  __/ /  ___ ____  / /__  __ _____  __ __  _  _____ ______ __  __ _  __ ______/ /");
        System.out.println(" / / / _ \\/ _ `/ _ \\/  '_/ / // / _ \\/ // / | |/ / -_) __/ // / /  ' \\/ // / __/ _ \\");
        System.out.println("/_/ /_//_/\\_,_/_//_/_/\\_\\  \\_, /\\___/\\_,_/  |___/\\__/_/  \\_, / /_/_/_/\\_,_/\\__/_//_/");
        System.out.println("                          /___/                         /___/");
        loginFlag = LoginFlags.EXIT;
    }
}

