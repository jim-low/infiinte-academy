package payment;

import java.util.Random;
import management.Academy;

public interface Payment {
    public static boolean performPayment(String type, double amount) {
        System.out.print("Enter your account number : ");
        String accountNo = Academy.scan.next();

        System.out.print("Enter your CVC number : ");
        int cvcNo = Academy.scan.nextInt();

        System.out.println("Enter the amount to pay (RM) : ");
        double paidAmount = Academy.scan.nextDouble();


        String tempOTP = generateOTP();
        System.out.println(tempOTP + "\n");
        String userOTP = Academy.scan.next();
        if(!userOTP.equals(tempOTP)) {
            return false;
        }

        return amount == paidAmount;
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
}

