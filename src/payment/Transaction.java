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

    public double getTransactionAmount() {
        return amount;
    }
}
