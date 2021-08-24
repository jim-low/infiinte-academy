package payment;

import java.util.regex.Pattern;

public class Card extends Transaction{

    private int cvcNo;
    private String accountNo;
    private double balance;

    public Card(int cvcNo, String accountNo, double balance, double transactionAmount) {
        super(transactionAmount);
        this.cvcNo = cvcNo;
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public static boolean validateAccount(String accountNum, String tempStr) {
        if (!(accountNum.equals(tempStr))
                || (accountNum.length() != 13)
                || (!(Pattern.matches("\\d{4}-{1}\\d{3}-{1}\\d{4}", accountNum)))) {
            return false;
        }
        return true;
    }

    public static boolean validateCvcNo(int cvcNo, int tempNo) {
        if ((cvcNo != tempNo) || !(cvcNo >= 100 && cvcNo <= 999)) {
            return false;
        }
        return true;
    }
    
    public void cashIn(double cash) {
        this.balance += cash;
    }

    public void cashOut(double cash) {
        this.balance -= cash;
    }
}

