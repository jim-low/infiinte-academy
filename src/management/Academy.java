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

public class Academy {
    private static Student loggedInStudent = null;
    private static Instructor loggedInInstructor = null;
    private static int loginFlag = LoginFlags.NO_LOGIN;
    private static int choice;

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu();
        System.out.println();
        System.out.print("Your Choice: ");
        choice = scan.nextInt();
        parseChoice();
        System.out.println();
    }

    private static void showMenu() {
        showAcademyBanner();
        switch (loginFlag) {
            case LoginFlags.NO_LOGIN:
                firstLoginMenu();
                break;
        }
    }

    private static void firstLoginMenu() {
        System.out.println("1. Sign Up For An Account In Infinity Academy");
        System.out.println("2. Log In To Infinity Academy");
        System.out.println("3. About Infinity Academy");
        System.out.println("4. Meet The Team");
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
                System.out.println("Performing login, yup you are logged in");
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

    private static void showAcademyBanner() {
        System.out.println("__      __   _                    _         ___       __ _      _ _            _              _");
        System.out.println("\\ \\    / /__| |__ ___ _ __  ___  | |_ ___  |_ _|_ _  / _(_)_ _ (_) |_ _  _    /_\\  __ __ _ __| |___ _ __ _  _");
        System.out.println(" \\ \\/\\/ / -_) / _/ _ \\ '  \\/ -_) |  _/ _ \\  | || ' \\|  _| | ' \\| |  _| || |  / _ \\/ _/ _` / _` / -_) '  \\ || |");
        System.out.println("  \\_/\\_/\\___|_\\__\\___/_|_|_\\___|  \\__\\___/ |___|_||_|_| |_|_||_|_|\\__|\\_, | /_/ \\_\\__\\__,_\\__,_\\___|_|_|_\\_, |");
        System.out.println("                                                                      |__/                               |__/");
    }
}

