package br.com.batista.utils;

import java.math.*;
import java.util.concurrent.*;

public class PriceUtils {

    public static double applyFee (double basePrice) {
        double randomPercentage = ThreadLocalRandom.current().nextDouble(0.05, 0.15);
        double total = basePrice * (1 + randomPercentage);
        return BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
