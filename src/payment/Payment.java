package payment;

import static java.lang.Math.random;
import java.util.Random;

public interface Payment {

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

}

