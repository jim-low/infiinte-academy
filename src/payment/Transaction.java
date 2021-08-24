package payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

enum TransactionCode {
    CRE,
    STD,
    INS
}

public abstract class Transaction {

    protected String transactionID;
    protected Date dateCreated;
    protected Date expiryDate;
    protected double amount;
    private static String nextTransactionID;

    public Transaction(double transactionAmount) {
        this.amount = transactionAmount;
    }

    public static String createDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }
    
    static void findDifference(String start_date, String end_date) {
  
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            long diff_In_Time = d2.getTime() - d1.getTime();

            // Calucalte time difference in seconds,
            // minutes, hours, years, and days
            long diff_In_Sec = TimeUnit.MILLISECONDS.toSeconds(diff_In_Time) % 60;

            long diff_In_Mins = TimeUnit.MILLISECONDS.toMinutes(diff_In_Time) % 60;

            long diff_In_Hrs = TimeUnit.MILLISECONDS.toHours(diff_In_Time) % 24;
            
            long diff_In_Days = TimeUnit.MILLISECONDS.toDays(diff_In_Time) % 365;

            System.out.print("Difference" + " between two dates is: ");

            // Print result
            System.out.println(diff_In_Days + " days, "
                    + diff_In_Hrs + " hours, " 
                    + diff_In_Mins + " minutes, "
                    + diff_In_Sec + " seconds");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

