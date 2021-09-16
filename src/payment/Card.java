package payment;

import java.util.InputMismatchException;
import java.util.regex.Pattern;
import management.Academy;

public class Card extends Transaction {
    private int cvcNo;
    private String accountNo;
    private double balance;

    public Card(double transactionAmount,String accountNo,int cvcNo,double balance) {
        super(transactionAmount);
        this.cvcNo = cvcNo;
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public static boolean validateAccount(String accountNum, String confirmationAccountNum) {
        boolean isSame = accountNum.equals(confirmationAccountNum);
        boolean isValidLength = accountNum.length() == 13;
        boolean isMatch = Pattern.matches("\\d{4}-{1}\\d{3}-{1}\\d{4}", accountNum);
        return isSame && isValidLength && isMatch;
    }

    public static boolean validateAccount(String accountNum) {
        boolean isValidLength = accountNum.length() == 13;
        boolean isMatch = Pattern.matches("\\d{4}-{1}\\d{3}-{1}\\d{4}", accountNum);
        return isValidLength && isMatch;
    }

    public static boolean validateCvcNo(int cvcNo, int confirmationCvcNum) {
        boolean isSame = (cvcNo == confirmationCvcNum);
        boolean isWithin = (cvcNo >= 100 && cvcNo <= 999);
        return isSame && isWithin;
    }

    public static boolean validateCvcNo(int cvcNo) {
        return (cvcNo >= 100 && cvcNo <= 999);
    }

    public static String promptAccountNumber() {
        System.out.print("Enter your account number: ");
        String accountNumber = Academy.scan.next();
        Academy.scan.nextLine();

        return accountNumber;
    }

    public static int promptCvcNumber() {
        Integer cvc = null;
        while (cvc == null) {
            try {
                System.out.print("Enter your CVC number: ");
                cvc = Academy.scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Incorrect input! Please try again.");
                System.out.println();
                Academy.scan.nextLine();
            }
        }
        return cvc;
    }

    public static Card setupCard() {
        System.out.println("Enter your card details:-");

        String cardNum = promptAccountNumber();

        int cardCvc = promptCvcNumber();

        double balance = Payment.generateRandomAmount(1000, 2000);
        return validateAccount(cardNum) && validateCvcNo(cardCvc) ? new Card(0, cardNum, cardCvc, balance) : null;
    }

    public void cashIn(double cash) {
        this.balance += cash;
    }

    public void cashOut(double cash) {
        if(this.balance < cash){
            return;
        }
        this.balance -= cash;
    }

    public int getCvcNo() {
        return cvcNo;
    }

    public String getAccountNumber() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }
}

