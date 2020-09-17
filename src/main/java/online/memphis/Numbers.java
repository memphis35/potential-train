package online.memphis;

import java.util.HashMap;
import java.util.Map;

public class Numbers {

    public static Map<Integer, Integer> numbers = new HashMap<>();

    static {
        numbers.put(0, 0);
        numbers.put(1, 3); //one
        numbers.put(2, 3); //two
        numbers.put(3, 5); //three
        numbers.put(4, 4); //four
        numbers.put(5, 4); //five
        numbers.put(6, 3); //six
        numbers.put(7, 5); //seven
        numbers.put(8, 5); //eight
        numbers.put(9, 4); //nine
        numbers.put(10, 3); //ten
        numbers.put(11, 6); //eleven
        numbers.put(12, 6); //twelve
        numbers.put(13, 8); //thirteen
        numbers.put(14, 8); //fourteen
        numbers.put(15, 7); //fifteen
        numbers.put(16, 7); //sixteen
        numbers.put(17, 9); //seventeen
        numbers.put(18, 8); //eighteen
        numbers.put(19, 8); //nineteen
        numbers.put(20, 6); //twenty
        numbers.put(30, 6); //thirty
        numbers.put(40, 5); //forty
        numbers.put(50, 5); //fifty
        numbers.put(60, 5); //sixty
        numbers.put(70, 7); //seventy
        numbers.put(80, 6); //eighty
        numbers.put(90, 6); //ninety
        numbers.put(100, 10); //one hundred
        numbers.put(200, 10); //two hundred
        numbers.put(300, 12); //three hundred
        numbers.put(400, 11); //four hundred
        numbers.put(500, 11); //five hundred
        numbers.put(600, 10); //six hundred
        numbers.put(700, 12); //seven hundred
        numbers.put(800, 12); //eight hundred
        numbers.put(900, 11); //nine hundred
        numbers.put(1000, 11); //one thousand
    }

    static int getSum(int number) {
        return numbers.get(number);
    }
}
