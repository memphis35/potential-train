package online.memphis.model;

import java.util.ArrayList;
import java.util.List;

public class Fraction {
    private final int numerator;
    private final int denominator;
    private final List<Integer> holder = new ArrayList<>();

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        while (numerator > 0) {
            holder.add(numerator % 10);
            numerator /= 10;
        }
        while (denominator > 0) {
            int remain = denominator % 10;
            if (!holder.contains(remain)) {
                holder.add(remain);
            } else {
                holder.remove((Integer) remain);
            }
            denominator /= 10;
        }
    }

    public boolean canBeCancelled() {
        return holder.size() == 2;
    }

    public boolean isCurious() {
        double cancelledValue = (holder.get(0) * 1.0) / (holder.get(1) * 1.0);
        double decimalValue = (numerator * 1.0) / (denominator * 1.0);
        return cancelledValue == decimalValue;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }
}
