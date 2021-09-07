package payment;

import java.util.Random;
import management.Academy;
import personnel.Instructor;
import personnel.Student;
import system.Course;
import system.SystemCodes;

public interface Payment {
    public static <T> boolean performPayment(Card card, double amount, Class<T> type) {

        String transactionCode = "";
        if (type.equals(Course.class)) {
            String transcode = SystemCodes.CRE.toString();
        } else if (type.equals(Student.class)) {
            String transcode = SystemCodes.STD.toString();
        }
        else if (type.equals(Instructor.class)) {
            String transcode = SystemCodes.INS.toString();
        }

        int counter = 3;
        String accountNo = "";
        while(counter > 0) {
            System.out.print("Enter your account number : ");
            accountNo = Academy.scan.next();
            System.out.print("\nConfirm your account number : ");
            String confirmationAccountNo = Academy.scan.next();

            if(!Card.validateAccount(accountNo, confirmationAccountNo)) {
                System.out.println("Your account number does not match. Please kindly try again.");
                counter--;
            }
        }

        counter = 3;
        int cvcNo = 0;
        while(counter > 0) {
             System.out.print("Enter your CVC number : ");
            cvcNo = Academy.scan.nextInt();
            System.out.print("\nConfirm your CVC number : ");
            int confirmationCvcNo = Academy.scan.nextInt();

            if(!Card.validateCvcNo(cvcNo, confirmationCvcNo)) {
                System.out.println("Your CVC numbers do not match. Please kindly try again.");
                counter--;
            }
        }

        System.out.printf("Enter the amount to pay (RM) : ");
        double paidAmount = Academy.scan.nextDouble();

        counter = 3;
        String otp = generateOTP();
        while (counter > 0) {
            System.out.printf("Your OTP code : %s\n", otp);
            System.out.print("Enter the OTP code as shown above >>> ");
            String confirmationOtp = Academy.scan.next();

            if (!otp.equals(confirmationOtp)) {
                System.out.println("Your OTP codes do not match. Please kindly try again.");
                counter--;
            }
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
        String characters = "ABCDEFGabcdefg1234567"; //character involve
        String otp = "";                     //otp string
        TimeDelay();
        String text = "";
        for (int i = 0; i < 7; i++) { //generate otp
            int index = (int)Math.random()*characters.length();
            otp += characters.charAt(index);
        }
        return otp;
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

