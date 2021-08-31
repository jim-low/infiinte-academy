package payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;


public abstract class Transaction {
    protected String transactionID;
    protected Date dateCreated;
    protected Date expiryDate;
    protected double amount;
    private static String nextTransactionID;

    public Transaction(double transactionAmount) {
        this.amount = transactionAmount;
    }

    public static String getNextTransactionID() {
        return nextTransactionID;
    }

    public static void setNextTransactionID(String nextTransactionID) {
        Transaction.nextTransactionID = nextTransactionID;
    }

    public static String createDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }
    
    public static void calculateDateDifference(String startDate, String endDate) {
  
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            Date d1 = sdf.parse(startDate);
            Date d2 = sdf.parse(endDate);

            // Calucalte time difference
            long diffInTime = d2.getTime() - d1.getTime();
            // Calucalte time difference in seconds,
            // minutes, hours, years, and days
            long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInTime) % 60;

            long diffInMins = TimeUnit.MILLISECONDS.toMinutes(diffInTime) % 60;

            long diffInHrs = TimeUnit.MILLISECONDS.toHours(diffInTime) % 24;
            
            long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInTime) % 365;

            System.out.print("Difference" + " between two dates is: ");

            // Print result
            System.out.println(diffInDays + " days, "
                    + diffInHrs + " hours, " 
                    + diffInMins + " minutes, "
                    + diffInSec + " seconds");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

