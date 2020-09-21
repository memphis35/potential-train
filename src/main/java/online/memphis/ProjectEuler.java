package online.memphis;

import online.memphis.util.CalendarUtil;
import online.memphis.util.DigitHolder;
import online.memphis.util.EulerUtil;
import online.memphis.util.Numbers;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class ProjectEuler {

    private static long count = 0;
    /*
     * --- 1. Multiples of 3 and 5 ---
     * If we list all the natural numbers below 10 that are
     * multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
     * Find the sum of all the multiples of 3 or 5 below 1000.
     */

    public static int solveTask1() {
        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) sum += i;
        }
        return sum;
    }

    /*
     * --- 2. Even Fibonacci numbers ---
     * By considering the terms in the Fibonacci sequence whose values do not exceed
     * four million, find the sum of the even-valued terms.
     */

    public static long solveTask2() {
        long sum = 0;
        int first = 0;
        int second = 1;
        int third = 0;
        while (second < 4_000_000) {
            third = first + second;
            if (third % 2 == 0) sum += third;
            first = second;
            second = third;
        }
        return sum;
    }

    /*
     * --- 3. Largest prime factor ---
     * The prime factors of 13195 are 5, 7, 13 and 29.
     * What is the largest prime factor of the number 600851475143 ?
     */

    public static long solveTask3() {
        long maxPrimeNumber = 2;
        long mockNumber = 600851475143L;
        while (mockNumber > maxPrimeNumber) {
            if (mockNumber % maxPrimeNumber == 0) {
                mockNumber /= maxPrimeNumber;
            } else {
                maxPrimeNumber++;
            }
        }
        return maxPrimeNumber;
    }

    /*
     * --- 4. Largest palindrome product ---
     * A palindromic number reads the same both ways. The largest
     * palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
     * Find the largest palindrome made from the product of two 3-digit numbers.
     */

    public static int solveTask4() {
        int result = -1;
        int tempResult = -1;
        for (int i = 999, j = 999; i > 99; j--) {
            if (j < i) {
                i--;
                j = 999;
                continue;
            }
            if (checkPalindrome(i * j)) {
                tempResult = i * j;
                result = tempResult > result ? tempResult : result;
            }
        }
        return result;
    }

    private static boolean checkPalindrome(int number) {
        boolean isPalindrome = true;
        int count = 0;
        int copyNumber = number;
        int[] num = new int[6];
        while (copyNumber > 0) {
            num[count] = copyNumber % 10;
            count++;
            copyNumber /= 10;
        }
        for (int i = 0, j = count - 1; i < count; ) {
            if (num[i] != num[j]) {
                isPalindrome = false;
                break;
            } else {
                i++;
                j--;
            }
        }
        return isPalindrome;
    }

    /*
     * --- 5. Smallest multiple ---
     * 2520 is the smallest number that can be divided by each of the numbers
     * from 1 to 10 without any remainder. What is the smallest positive number
     * that is evenly divisible by all of the numbers from 1 to 20?
     */

    public static long solveTask5() {
        long result = 1;
        List<Integer> divisors = calculateDivisors(10);
        for (int i = 0; i < divisors.size(); i++) {
            result *= divisors.get(i);
        }
        return result;
    }

    private static List<Integer> calculateDivisors(int count) {
        List<Integer> divisors = new ArrayList<>();
        for (; count > 1; count--) {
            int div = checkOldDivisors(divisors, count);
            for (int i = 2; div > 1; ) {
                if (div % i == 0) {
                    divisors.add(i);
                    div /= i;
                } else {
                    i++;
                }
            }
        }
        return divisors;
    }

    private static int checkOldDivisors(List<Integer> divisors, int div) {
        for (Integer divisor : divisors) {
            if (div % divisor == 0) div /= divisor;
        }
        return div;
    }

    /*
     * --- 6. Sum square difference ---
     * Find the difference between the sum of the squares of the first one hundred
     * natural numbers and the square of the sum.
     */

    public static int solveTask6(int sequence) {
        int sumOfSquares = 0;
        int squareOfSum = 0;
        for (int i = 1; i <= sequence; i++) {
            sumOfSquares += i * i;
            squareOfSum += i;
        }
        squareOfSum *= squareOfSum;
        return squareOfSum - sumOfSquares;
    }

    /*
     * --- 7. 10001st prime ---
     * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13,
     * we can see that the 6th prime is 13. What is the 10 001st prime number?
     */

    public static int solveTask7(int count) {
        int[] divSet = new int[count];
        int pointer = 0;
        for (int num = 2; divSet[divSet.length - 1] == 0; num++) {
            boolean isPrime = true;
            for (int j = 0; divSet[j] != 0; j++) {
                if (num % divSet[j] == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                divSet[pointer] = num;
                pointer++;
            }
        }
        return divSet[count - 1];
    }

    /*
     * --- 8. Largest product in a series ---
     * Find the thirteen adjacent digits in the 1000-digit number that have
     * the greatest product. What is the value of this product?
     * See ./files/task8 to find a 1000-digit number.
     */

    public static long solveTask8(int length) {
        int[] digits = new int[length];
        int pointer = -1;
        long tempProduct = 0;
        long productOfSum = 0;
        try (Reader fr = new FileReader("./files/task8")) {
            {   //init block
                for (int i = 0; i < length; i++) {
                    digits[i] = fr.read() - 48;
                }
            }
            while (fr.ready()) {
                pointer = pointer == digits.length - 1 ? 0 : pointer + 1;
                digits[pointer] = fr.read() - 48;
                tempProduct = getProduct(digits);
                if (tempProduct > 0 && tempProduct > productOfSum) {
                    productOfSum = tempProduct;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productOfSum;
    }

    private static long getProduct(int[] array) {
        long sum = 1;
        for (int x : array) {
            sum *= x;
            if (x == 0) break;
        }
        return sum;
    }

    /*
     * --- 9. Special Pythagorean triplet ---
     * A Pythagorean triplet is a set of three natural numbers, a < b < c,
     * for which, a^2 + b^2 = c^2. There exists exactly one Pythagorean
     * triplet for which a + b + c = 1000. Find the product abc.
     */

    public static long solveTask9() {
        for (int i = 2; i < 1000; i++) {
            for (int j = i; j < 1000; j++) {
                for (int k = 1000 - i - j; k < 1000; k++) {
                    if (i + j + k < 1000) {
                        continue;
                    } else if (i + j + k == 1000) {
                        if (i * i + j * j == k * k) {
                            return (long) i * j * k;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return 0;
    }

    /*
     * --- 10. Summation of primes
     * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
     * Find the sum of all the primes below two million.
     */

    public static long solveTask10(int endPoint) {
        long sum = 0;
        int pointer = 0;
        int[] primes = new int[1_000_000];
        for (int i = 2; i < endPoint; i++) {
            boolean isPrime = true;
            for (int k = 0; k < primes.length && primes[k] != 0; k++) {
                if (i % primes[k] == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes[pointer++] = i;
                sum += i;
            }
        }
        return sum;
    }

    /*
     * --- 11. Largest product in a grid ---
     * What is the greatest product of four adjacent numbers in the same
     * direction (up, down, left, right, or diagonally) in the 20×20 grid?
     */

    public static int solveTask11(int length) {
        int[][] matrix = EulerUtil.parseMatrixList("./files/task11");
        int product = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int tempProduct = getMatrixProduct(matrix, i, j, length);
                product = (tempProduct > product) ? tempProduct : product;
            }
        }
        return product;
    }

    private static int getMatrixProduct(int[][] matrix, int i, int j, int length) {
        int product = 0;
        int[][] directions = {{0, 1}, {1, 1}, {1, 0}, {-1, 1}};
        for (int z = 0; z < directions.length; z++) {
            int tempProduct = matrix[i][j];
            int tempI = i;
            int tempJ = j;
            for (int k = 1; k < length; k++) {
                tempI += directions[z][0];
                tempJ += directions[z][1];
                if (tempI < 0 || tempJ < 0 || tempI >= matrix.length || tempJ >= matrix[0].length) {
                    tempProduct = 0;
                    break;
                }
                tempProduct *= matrix[tempI][tempJ];
            }
            product = tempProduct > product ? tempProduct : product;
        }
        return product;
    }

    /*
     * --- 12. Highly divisible triangular number ---
     * The sequence of triangle numbers is generated by adding the natural numbers. So the
     * 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten
     * terms would be: 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
     * Let us list the factors of the first seven triangle numbers:
     * 1: 1
     * 3: 1,3
     * ..........
     * 28: 1,2,4,7,14,28
     * We can see that 28 is the first triangle number to have over five divisors.
     * What is the value of the first triangle number to have over five hundred divisors?
     */

    //TODO: BAD SOLUTION WITH TIMEOUT > 10min, NEED TO REFACTOR
    public static long solveTask12() {
        int count = 0;
        long triangleNum = 0;
        int i = 1;
        while (count < 500 & triangleNum < Long.MAX_VALUE) {
            triangleNum += i;
            i++;
            count = getCount(triangleNum);
            if (count > 200) System.out.println("Number = " + triangleNum + " count = " + count);
        }
        return triangleNum;
    }

    private static int getCount(long triangleNum) {
        int count = 1;
        long limit;
        if (triangleNum % 2 == 0) {
            limit = triangleNum / 2;
        } else if (triangleNum % 3 == 0) {
            limit = triangleNum / 3;
        } else if (triangleNum % 5 == 0) {
            limit = triangleNum / 5;
        } else {
            limit = 0;
        }
        for (long i = 2; i <= limit; i++) {
            if (triangleNum % i == 0) count++;
        }
        return count;
    }

    /*
     * --- 13. Large sum ---
     * Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.
     */

    public static long solveTask13() {
        List<int[]> numbers = EulerUtil.getNumbers("./files/task13");
        int[] result = new int[numbers.get(0).length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < numbers.size(); j++) {
                result[i] += numbers.get(j)[i];
            }
        }
        int temp = 0;
        int firstTenDigits = 0;
        for (int z = result.length - 1; z > 0; z--) {
            temp = result[z];
            result[z] %= 10;
            result[z - 1] += temp / 10;
        }
        int count = 10;
        while (temp > 0) {
            temp /= 10;
            count--;
        }
        long res = result[0];
        for (int i = 1; i <= count; i++) {
            res = res * 10 + result[i];
        }
        return res;
    }

    /*
     * --- 14. Longest Collatz sequence ---
     * The following iterative sequence is defined for the set of positive integers:
     * n → n/2 (n is even)
     * n → 3n + 1 (n is odd)
     * Using the rule above and starting with 13, we generate the following sequence:
     * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
     * It can be seen that this sequence (starting at 13 and finishing at 1) contains
     * 10 terms. Although it has not been proved yet (Collatz Problem), it is thought
     * that all starting numbers finish at 1.
     * Which starting number, under one million, produces the longest chain?
     */
    //TODO: Make search by multi-thread realization
    public static int solveTask14() {
        int maxCount = 1;
        int result = 0;
        for (int i = 1_000_000; i > 1; i--) {
            long tempNumber = i;
            int tempCount = 1;
            while (tempNumber != 1) {
                tempNumber = tempNumber % 2 == 0 ? tempNumber / 2 : 3 * tempNumber + 1;
                tempCount++;
            }
            if (tempCount > maxCount) {
                result = i;
                maxCount = tempCount;
            }
        }
        return result;
    }

    static String appendAndDelete(String s, String t, int k) {
        int length = Math.min(s.length(), t.length()), i;
        for (i = 0; i < length; i++) {
            if (s.charAt(i) != t.charAt(i)) break;
        }
        boolean isPossible = s.substring(i).length() + t.substring(i).length() == k;
        return isPossible ? "Yes" : "No";
    }

    /*
     * --- 15. Lattice paths ---
     * Starting in the top left corner of a 2×2 grid, and only being able to move
     * to the right and down, there are exactly 6 routes to the bottom right corner.
     * How many such routes are there through a 20×20 grid?
     */
    //TODO: learh graphs, try to find math formula to calculate paths,
    //      timeout=35 min
    public static long solveTask15(int size) {
        getPaths(size + 1, 0, 0);
        return count;
    }

    private static void getPaths(int length, int i, int j) {
        if (i < length - 1) getPaths(length, i + 1, j);
        if (j < length - 1) getPaths(length, i, j + 1);
        if (i == j && i + j == length * 2 - 2) count++;
        return;
    }

    /*
     * --- 16. Power digit sum ---
     * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
     * What is the sum of the digits of the number 21000?
     */

    public static int solveTask16(int num, int power) {
        int[] result = getBigPower(num, power);
        int sum = 0;
        for (int i = result.length - 1; result[i] != -1; i--) {
            sum += result[i];
        }
        return sum;
    }

    private static int[] getBigPower(int num, int power) {
        int[] result = new int[power];
        Arrays.fill(result, -1);
        result[result.length - 1] = num;
        int tempPower = 1;
        while (tempPower < power) {
            for (int i = result.length - 1; i >= 0 && result[i] >= 0; i--) {
                result[i] *= num;
            }
            for (int i = result.length - 1; i >= 0; i--) {
                int temp = result[i];
                result[i] = temp % 10;
                if (temp > 9) {
                    if (result[i - 1] >= 0) {
                        result[i - 1] += temp / 10;
                    } else {
                        result[i - 1] = temp / 10;
                    }
                } else if (result[i] == -1) {
                    break;
                }
            }
            tempPower++;
        }
        return result;
    }

    /*
     * --- 17. Number letter counts ---
     * If the numbers 1 to 5 are written out in words: one, two, three, four, five,
     * then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.If all the numbers
     * from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
     */

    public static long solveTask17(int limit) {
        long sumOfLetters = 0;
        for (int i = 1; i <= limit; i++) {
            int number = i;
            int tempSum = 0;
            for (int j = 100; number > 0; j *= 10) {
                int temp = number % j;
                if (temp > 0 && temp <= 20) {
                    tempSum += Numbers.getSum(temp);
                } else if (temp > 20 && temp < 100) {
                    tempSum += Numbers.getSum(temp - (temp % 10)) + Numbers.getSum(temp % 10);
                } else if (temp >= 100) {
                    tempSum += (tempSum == 0) ? Numbers.getSum(temp) : Numbers.getSum(temp) + 3;
                }
                number = number - (number % j);
            }
            sumOfLetters += tempSum;
        }
        return sumOfLetters;
    }

    /*
     * --- 18. Maximum path sum I ---
     * Find the maximum total from top to bottom of the triangle (file: task18)):
     */

    public static int solveTask18() {
        List<int[]> pyramid = EulerUtil.parsePyramidList("./files/task18");
        int max = 0;
        int size = pyramid.size();
        for (int i = 1; i < size; i++) {
            int[] previousRow = pyramid.get(i - 1);
            int[] currentRow = pyramid.get(i);
            for (int j = 0; j < currentRow.length; j++) {
                if (j == 0) {
                    currentRow[j] += previousRow[j];
                } else if (j > 0 && j < currentRow.length - 1) {
                    int firstSum = currentRow[j] + previousRow[j - 1];
                    int secondSum = currentRow[j] + previousRow[j];
                    currentRow[j] = firstSum > secondSum ? firstSum : secondSum;
                } else {
                    currentRow[j] += previousRow[j - 1];
                }
                max = currentRow[j] > max ? currentRow[j] : max;
            }
        }
        return max;
    }

    //for solve task18 recursive brute force permutations
    private static int[] getSum(List<int[]> pyramid, int row, int index, int sum, int[] maxSum) {
        if (row >= pyramid.size()) {
            maxSum[0] = Math.max(sum, maxSum[0]);
            return maxSum;
        }
        if (index < pyramid.get(row).length) {
            sum += pyramid.get(row)[index];
        } else {
            return maxSum;
        }
        getSum(pyramid, row + 1, index, sum, maxSum);
        getSum(pyramid, row + 1, index + 1, sum, maxSum);
        return maxSum;
    }

    /*
     * --- 19. Counting Sundays ---
     * How many Sundays fell on the first of the month during the twentieth century
     * (1 Jan 1901 to 31 Dec 2000)?
     */

    public static int solveTask19() throws IllegalAccessException {
        int daysAfter1900start = 1;
        for (CalendarUtil month : CalendarUtil.values()) {
            daysAfter1900start += CalendarUtil.getDays(month, isLeapYear(1900));
        }
        int countSundays = 0;
        for (int i = 1901; i < 2001; i++) {
            for (CalendarUtil month : CalendarUtil.values()) {
                daysAfter1900start += CalendarUtil.getDays(month, isLeapYear(i));
                if (daysAfter1900start % 7 == 0) countSundays++;
            }
        }
        return countSundays;
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /*
     * --- 20. Factorial digit sum ---
     * Find the sum of the digits in the number 100!
     */

    public static int solveTask20(int number) {
        int[] result = getFactorial(number);
        int sum = 0;
        for (int i = 0; result[i] >= 0; i++) {
            sum += result[i];
        }
        return sum;
    }

    private static int[] getFactorial(int number) {
        int[] result = new int[500];
        Arrays.fill(result, -1);
        result[0] = 1;
        for (int i = 2; i <= number; i++) {
            for (int j = 0; result[j] != -1; j++) {
                result[j] *= i;
            }
            recheckArray(result);
        }
        return result;
    }

    private static void recheckArray(int[] array) {
        for (int i = 0; i < array.length - 1 && array[i] >= 0; i++) {
            int temp = array[i];
            if (temp > 0) {
                array[i] = temp % 10;
                if (array[i + 1] == -1 && temp / 10 > 0) {
                    array[i + 1] = temp / 10;
                } else {
                    array[i + 1] += temp / 10;
                }
            }
        }
    }

    /*
     * --- 21. Amicable numbers
     * Let d(n) be defined as the sum of proper divisors of n (numbers less
     * than n which divide evenly into n). If d(a) = b and d(b) = a, where a ≠ b,
     * then a and b are an amicable pair and each of a and b are called amicable numbers.
     * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55
     * and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142;
     * so d(284) = 220. Evaluate the sum of all the amicable numbers under 10000.
     */

    public static int solveTask21(int number) {
        List<Integer> amicableNumbers = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            int firstNum = getSumOfProperDivs(i);
            int secondNum = getSumOfProperDivs(firstNum);
            if (firstNum != secondNum && secondNum == i) amicableNumbers.add(i);
        }
        int sum = 0;
        for (int num : amicableNumbers) {
            sum += num;
        }
        return sum;
    }

    private static int getSumOfProperDivs(int number) {
        return IntStream.iterate(1, num -> num + 1)
                .limit(number / 2)
                .filter((x) -> number % x == 0)
                .sum();
    }

    /*
     * --- 22. Names scores ---
     * Using ./files/task22, a 46K text file containing over five-thousand first names, begin by
     * sorting it into alphabetical order. Then working out the alphabetical value for each name,
     * multiply this value by its alphabetical position in the list to obtain a name score.
     * For example, when the list is sorted into alphabetical order, COLIN, which is worth
     * 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score
     * of 938 × 53 = 49714. What is the total of all the name scores in the file?
     */

    public static int solveTask22() {
        String[] names = EulerUtil.parseFileTask22("files/task22");
        Arrays.sort(names);
        int total = 0;
        for (int i = 1; i < names.length; i++) {
            total += getScore(names[i]) * (i);
        }
        return total;
    }

    private static int getScore(String name) {
        int score = 0;
        for (int i = 0; i < name.length(); i++) {
            score += name.charAt(i) - 64;
        }
        return score;
    }

    /*
     * --- 23. Non-abundant sums ---
     * A number n is called deficient if the sum of its proper divisors is less
     * than n and it is called abundant if this sum exceeds n. By mathematical analysis,
     * it can be shown that all integers greater than 28123 can be written as the sum of
     * two abundant numbers. Find the sum of all the positive integers which cannot be
     * written as the sum of two abundant numbers.
     */

    public static int solveTask23() {
        List<Integer> abundantNumbers = new ArrayList<>();
        for (int i = 1; i < 28123; i++) {
            if (i < getSumOfProperDivs(i)) {
                abundantNumbers.add(i);
            }
        }
        Set<Integer> sumOfAbundant = getSumOfAbundant(abundantNumbers);
        int sum = 0;
        for (int i = 1; i <= 28123; i++) {
            if (!sumOfAbundant.contains(i)) {
                sum += i;
            }
        }
        return sum;
    }

    private static Set<Integer> getSumOfAbundant(List<Integer> numbers) {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i; numbers.get(j) <= 28123 - numbers.get(i); j++) {
                int sum = numbers.get(i) + numbers.get(j);
                if (!result.contains(sum)) result.add(sum);
            }
        }
        return result;
    }

    /*
     * --- 24. Lexicographic permutations ---
     * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
     */

    public static long solveTask24() {
        DigitHolder digits = new DigitHolder(10);
        Stack<Integer> stack = new Stack<>();
        getPermutations(stack, digits);
        long result = 0;
        for (Integer digit : stack) {
            result = (result * 10) + digit;
        }
        return result;
    }

    private static void getPermutations(Stack<Integer> stack, DigitHolder digits) {
        for (int i = 0; i < digits.size(); i++) {
            stack.push(digits.getDigit(i));
            if (digits.size() == 0) {
                count++;
                if (count == 1_000_000) {
                    return;
                } else {
                    digits.insertDigit(stack.pop());
                    return;
                }
            }
            getPermutations(stack, digits);
            if (count == 1_000_000) return;
            digits.insertDigit(stack.pop());
        }
    }

    /*
     * --- 25. 1000-digit Fibonacci number ---
     * What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
     */

    public static int solveTask25() {
        int[] first = new int[1000];
        int[] second = new int[1000];
        int[] temp;
        int index = 2;
        Arrays.fill(first, -1);
        Arrays.fill(second, -1);
        first[0] = second[0] = 1;
        while (second[second.length - 1] <= 0) {
            index++;
            temp = second.clone();
            for (int i = 0; i < first.length && first[i] >= 0; i++) {
                second[i] = first[i] + second[i];
            }
            recheckArray2(second);
            first = temp;
        }
        return index;
    }

    private static void recheckArray2(int[] array) {
        for (int i = 0; i < array.length - 1 && array[i] >= 0; i++) {
            int temp = array[i];
            if (temp > 0) {
                array[i] = temp % 10;
                if (array[i + 1] == -1 && temp / 10 > 0) {
                    array[i + 1] = temp / 10;
                } else {
                    array[i + 1] += temp / 10;
                }
            }
        }
    }

    /*
     * --- 27. Reciprocal cycles ---
     * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
     */

    public static int solveTask26(int number) {
        int maxRepeat = 0;
        int maxNumber = 0;
        for (int i = 2; i <= number; i++) {
            int tempRepeat = getDecimalFraction(1, i);
            if (tempRepeat > maxRepeat) {
                maxRepeat = tempRepeat;
                maxNumber = i;
            }
        }
        return maxNumber;
    }

    private static int getDecimalFraction(int number, int divisor) {
        int remain = number % divisor;
        List<Integer> remains = new ArrayList<>();
        int index;
        do {
            remains.add(remain * 10);
            index = checkFraction(remains, remain * 10);
            remain = (remain * 10) % divisor;
        } while (index < 0);
        return remains.size() - 1 - index;
    }

    private static int checkFraction(List<Integer> remains, int number) {
        int index = -1;
        for (int i = 0; i < remains.size() - 1; i++) {
            if (remains.get(i) == number) {
                index = i;
                break;
            }
        }
        return index;
    }

    /*
     * --- 28. Number spiral diagonals ---
     * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:
     * 21 22 23 24 25
     * 20  7  8  9 10
     * 19  6  1  2 11
     * 18  5  4  3 12
     * 17 16 15 14 13
     * It can be verified that the sum of the numbers on the diagonals is 101.
     * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
     */

    public static int solveTask28() {
        int[][] matrix = new int[1001][1001];
        int end = matrix.length * matrix[0].length;
        int[][] direction = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int directionPointer = 0;
        int x = 0, y = matrix.length - 1;
        for (int i = end; i > 0; i--) {
            matrix[x][y] = end--;
            x += direction[directionPointer][0];
            y += direction[directionPointer][1];
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix.length || matrix[x][y] > 0) {
                x -= direction[directionPointer][0];
                y -= direction[directionPointer][1];
                directionPointer = directionPointer == direction.length - 1 ? 0 : directionPointer + 1;
                x += direction[directionPointer][0];
                y += direction[directionPointer][1];
            }
        }
        int sum = 0;
        for (int i = 0, j = 0; i < matrix.length; i++, j++) {
            sum += (matrix[i][j] + matrix[matrix.length - i - 1][j]);
        }
        return sum - 1;
    }

    /*
     * --- 29. Distinct powers ---
     * Consider all integer combinations of a^b for 2 ≤ a ≤ 5 and 2 ≤ b ≤ 5.
     * If they are then placed in numerical order, with any repeats removed, we get
     * the following sequence of 15 distinct terms:
     * 4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125
     * How many distinct terms are in the sequence generated by a^b for 2 ≤ a ≤ 100 and 2 ≤ b ≤ 100?
     */

    public static int solveTask29() {
        Set<String> result = new TreeSet<>();
        for (int i = 2; i <= 100; i++) {
            getPowersAndSave(i, 100, result);
        }
        return result.size();
    }

    private static void getPowersAndSave(int number, int power, Set<String> set) {
        int[] arrayNumber = generateNumberArray(number, power);
        for (int i = 2; i <= power; i++) {
            getPoweredNumber(arrayNumber, number);
            recheckArray2(arrayNumber);
            String str = getStringFromArrayNumber(arrayNumber);
            if (!set.contains(str)) set.add(str);
        }
    }

    private static int[] generateNumberArray(int number, int power) {
        int length = 0;
        int tempNumber = number;
        while (tempNumber > 0) {
            length++;
            tempNumber /= 10;
        }
        int[] result = new int[(length + 1) * power];
        Arrays.fill(result, -1);
        result[0] = number;
        return result;
    }

    private static void getPoweredNumber(int[] number, int multiplier) {
        for (int i = 0; i < number.length && number[i] >= 0; i++) {
            number[i] *= multiplier;
        }
    }

    private static String getStringFromArrayNumber(int[] number) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < number.length && number[i] >= 0; i++) {
            builder.append(number[i]);
        }
        builder.reverse();
        return builder.toString();
    }

    /*
     * --- 30. Digit fifth powers ---
     * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
     */

    public static int solveTask30() {
        List<Integer> result = new ArrayList<>();
        int[] power = {0, 1, 32, 243, 1024, 3125, 7776, 16807, 32768, 59049};
        int[] number = {1, -1, -1, -1, -1, -1};
        for (int i = 2; i < 1_000_000; i++) {
            number[0]++;
            if (number[0] >= 10) recheckArray2(number);
            int sum = getSum(power, number);
            if (i == sum) {
                System.out.println(i);
                result.add(i);
            }
        }
        return result.stream().reduce(0, (x, y) -> x + y);
    }

    private static int getSum(int[] powers, int[] number) {
        int sum = 0;
        for (int i = 0; i < number.length && number[i] >= 0; i++) {
            sum += powers[number[i]];
        }
        return sum;
    }
}
