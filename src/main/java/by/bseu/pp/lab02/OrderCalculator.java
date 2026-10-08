package by.bseu.pp.lab02;

/**
 * Utility methods for calculating and validating simple order data.
 *
 * <p>Implement every TODO method without changing its signature.</p>
 */

public class OrderCalculator {

    public static double calculateSubtotal(double[] prices, int[] quantities) {
        if (prices == null || quantities == null) {
            throw new IllegalArgumentException("prices and quantities must not be null");
        }
        if (prices.length != quantities.length) {
            throw new IllegalArgumentException("prices and quantities must have the same length");
        }
        if (prices.length == 0) {
            throw new IllegalArgumentException("arrays must not be empty");
        }

        double subtotal = 0.0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < 0) {
                throw new IllegalArgumentException("price must be >= 0");
            }
            if (quantities[i] <= 0) {
                throw new IllegalArgumentException("quantity must be > 0");
            }
            subtotal += prices[i] * quantities[i];
        }
        return subtotal;
    }

    public static double determineDiscountRate(int previousOrdersCount) {
        if (previousOrdersCount < 0) {
            throw new IllegalArgumentException("previousOrdersCount must be >= 0");
        }
        if (previousOrdersCount >= 100) {
            return 0.15;
        }
        if (previousOrdersCount >= 20) {
            return 0.07;
        }
        return 0.00;
    }

    public static double applyDiscount(double subtotal, double discountRate) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("subtotal must be >= 0");
        }
        if (discountRate < 0.0 || discountRate > 1.0) {
            throw new IllegalArgumentException("discountRate must be in [0.0, 1.0]");
        }
        return subtotal * (1.0 - discountRate);
    }

    public static double addTax(double amount, double taxRate) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be >= 0");
        }
        if (taxRate < 0.0 || taxRate > 1.0) {
            throw new IllegalArgumentException("taxRate must be in [0.0, 1.0]");
        }
        return amount * (1.0 + taxRate);
    }

    public static double calculateTotal(
            double[] prices,
            int[] quantities,
            int previousOrdersCount,
            double taxRate) {
        double subtotal = calculateSubtotal(prices, quantities);
        double discountRate = determineDiscountRate(previousOrdersCount);
        double afterDiscount = applyDiscount(subtotal, discountRate);
        return addTax(afterDiscount, taxRate);
    }

    public static boolean isWithinCreditLimit(double total, double creditLimit) {
        if (total < 0) {
            throw new IllegalArgumentException("total must be >= 0");
        }
        if (creditLimit < 0) {
            throw new IllegalArgumentException("creditLimit must be >= 0");
        }
        return total <= creditLimit;
    }

    public static int countOrdersWithinLimit(double[] totals, double creditLimit) {
        if (totals == null) {
            throw new IllegalArgumentException("totals must not be null");
        }
        if (creditLimit < 0) {
            throw new IllegalArgumentException("creditLimit must be >= 0");
        }

        int count = 0;
        for (int i = 0; i < totals.length; i++) {
            if (totals[i] < 0) {
                throw new IllegalArgumentException("total must be >= 0");
            }
            if (totals[i] <= creditLimit) {
                count++;
            }
        }
        return count;
    }
}
