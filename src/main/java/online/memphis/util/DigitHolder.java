package online.memphis.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DigitHolder {

    List<Integer> digits;

    public DigitHolder(int size) {
        digits = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            digits.add(i);
        }
    }

    public int getDigit(int position) {
        int digit = digits.get(position);
        digits.remove(position);
        return digit;
    }

    public void insertDigit(int digit) {
        digits.add(digit);
        Collections.sort(digits);
    }

    public int size() {
        return digits.size();
    }
}
