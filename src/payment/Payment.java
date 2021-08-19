package payment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;
import management.Academy;

public interface Payment {
    public static boolean performPayment(String type, double amount) {
        paymentInterface();
        String accountNo = inputAccountNo();
        int intNo = inputcvcNo();
        System.out.printf("\n\nYOUR ACCOUNT HAS BEEN ACTIVATED IN OUR ACEDEMY\n");

        System.out.printf("Enter the amount to pay (RM) : ");
        double paidAmount = Academy.scan.nextDouble();

        otpValidation();
        System.out.println("\nThank you for using our payment system.\n");

        return amount == paidAmount;

    }

    public static String inputAccountNo() {
        String accountNo, tempStr;
        do {
            System.out.printf("\n\nPlease enter your account number [xxxx-xxx-xxxx] : ");
            accountNo = Academy.scan.next();
            System.out.printf("\nPlease comfirm your accountNo : ");
            tempStr = Academy.scan.next();
        } while (!(accountNo.equals(tempStr))
                || (accountNo.length() != 13)
                || (!(Pattern.matches("\\d{4}-{1}\\d{3}-{1}\\d{4}", accountNo))));
        return accountNo;
    }

    public static int inputcvcNo() {
        int cvcNo, tempNo;
        do {
            System.out.printf("\n\nPlease enter your cvcNo [xxx] : ");
            cvcNo = Academy.scan.nextInt();
            System.out.printf("\nPlease comfirm your cvcNo : ");
            tempNo = Academy.scan.nextInt();
        } while ((cvcNo != tempNo) || !(cvcNo >= 100 && cvcNo <= 999));
        TimeDelay();
        System.out.println("\nThank you for your cvcNo comfirmation\n");
        return cvcNo;
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
        String ch = "ABCDEFGabcdefg1234567"; //character involve
        String otp = "";                     //otp string
        Random random = new Random();
        System.out.println("Generating OTP\n");
        //TimeDelay();
        char[] text = new char[7];           //the length of the otp is 7 characters
        for (int i = 0; i < text.length; i++) { //generate otp
            text[i] = ch.charAt(random.nextInt(ch.length()));
            otp += text[i];
        }
        return otp;
    }

    public static boolean otpValidation() {
        String otp = generateOTP();
        String inputOtp = "";
        do {
            System.out.printf("\n\nOTP Account validation\n" + "=======================\n\n"
                    + "Please enter the values as given >>> " + otp + "\nEnter here : ");
            inputOtp = Academy.scan.next();
        } while (!otp.equals(inputOtp));
        return (otp.equals(inputOtp));
    }

    public static void paymentInterface() {
        System.out.println("*************************\n"
                + "*    Payment Platform   *\n"
                + "*     [INFINITY BANK]   *\n"
                + "*************************\n");
        getdate();
    }

    public static void getdate() {
        DateFormat dfor = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date obj = new Date();
        System.out.println(dfor.format(obj));
    }

    public static void TimeDelay() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        System.out.println("In progress...");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        System.out.println("Done\n");
    }
}

