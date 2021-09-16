package payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;


public abstract class Transaction {
    protected Calendar dateCreated;
    protected Calendar expiryDate;
    protected double amount;

    public Transaction(double transactionAmount) {
        this.amount = transactionAmount;
        this.dateCreated = Calendar.getInstance();
        this.expiryDate = Calendar.getInstance();
        this.expiryDate.set(Calendar.DAY_OF_MONTH, this.dateCreated.get(Calendar.DAY_OF_MONTH)+14);
    }

    public static String createDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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
