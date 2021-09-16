package payment;

import java.util.Random;
import management.Academy;

public interface Payment {
    public static <T> boolean performPayment(Card card, double amount) throws InterruptedException {
        String accountNumber = Card.promptAccountNumber();
        if (!Card.validateAccount(card.getAccountNumber(), accountNumber)) {
            System.out.println("Your account number does not match.");
            return false;
        }

        int cvcNumber = Card.promptCvcNumber();
        if (!Card.validateCvcNo(card.getCvcNo(), cvcNumber)) {
            System.out.println("Your CVC number does not match.");
            return false;
        }

        System.out.println();
        System.out.printf("Your Current Balance: RM%.2f\n", card.getBalance());
        System.out.printf("Your Registration Fee: RM%.2f\n", amount);
        System.out.print("Confirm payment? (y/n) ");
        if (Academy.scan.next().charAt(0) != 'y') {
            System.out.println("Aborting Payment.");
            return false;
        }

        card.cashOut(amount);

        System.out.println();
        timeDelay(2);
        System.out.println("In progress...");
        timeDelay(2);
        System.out.println("Done\n");

        String otp = generateOTP();
        System.out.println("Enter the OTP Code as shown: " + otp);
        System.out.print("OTP Code: ");
        String enteredOTP = Academy.scan.next();
        if (!enteredOTP.equals(otp)) {
            System.out.println("OTP Codes do not match. Aborting Payment");
            return false;
        }

        return true;
    }

    public static double generateRandomAmount(double min, double max) {
        Random r = new Random();
        return (min + (max - min) * r.nextDouble());
    }

    public static double generateRandomAmount() {
        final double MIN = 250;
        final double MAX = 1750;
        Random r = new Random();
        return (MIN + (MAX - MIN) * r.nextDouble());
    }

    public static String generateOTP() {
        String characters = "ABCDEFGabcdefg1234567"; //character involve
        String otp = "";                     //otp string
        for (int i = 0; i < 7; i++) { //generate otp
            int index = (int)(Math.random()*characters.length());
            otp += characters.charAt(index);
        }
        return otp;
    }

    public static void timeDelay(int seconds) throws InterruptedException {
        try {
            Thread.sleep(seconds * 1000);
        }
        catch (Exception e) {
            System.out.println("Something bad happened :(");
            System.out.println("But thats not my problem");
        }
    }
}

