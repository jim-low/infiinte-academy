package payment;

import java.util.regex.Pattern;

public class Card extends Transaction{

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

    public static boolean validateCvcNo(int cvcNo, int confirmationCvcNum) {
        boolean isSame = (cvcNo == confirmationCvcNum);
        boolean isWithin = (cvcNo >= 100 && cvcNo <= 999);
        return isSame && isWithin;
    }
    
    public void cashIn(double cash) {
        this.balance += cash;
    }

    public void cashOut(double cash) {
        if(this.balance < cash){
            return;
        }
        this.balance -= cash;
        //cash out
    }
}

