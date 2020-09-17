package online.memphis;

import java.io.*;
import java.util.*;

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
    //TODO: TO REALIZE A BINARY TREE, ANSWER IS 1074
    public static int solveTask18() {
        List<int[]> pyramid = EulerUtil.parsePyramidList("./files/task18");
        int[] upperRow = pyramid.get(0);
        for (int i = 1; i < pyramid.size(); i++) {
            int[] lowerRow = pyramid.get(i);
            int[] sumRow = new int[pow(2, i)];
            for (int up = 0, k = 0, low = 0; up < upperRow.length; up++) {
                sumRow[k++] = upperRow[up] + lowerRow[low];
                sumRow[k++] = upperRow[up] + lowerRow[low + 1];
                if (up % 2 != 0) low++;
            }
            upperRow = sumRow;
        }
        int sum = 0;
        for (int number : upperRow) {
            sum += number;
        }
        return sum;
    }

    private static int pow(int number, int power) {
        int result = number;
        while (power-- > 1) {
            result *= number;
        }
        return result;
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
            for (int j = 0; result[j] >= 0; j++) {
                result[j] *= i;
            }
            recheckArray(result);
        }
        return result;
    }

    private static void recheckArray(int[] array) {
        for (int i = 0; array[i] >= 0; i++) {
            int temp = array[i];
            if (temp > 0) {
                array[i] = temp % 10;
                if (array[i + 1] == -1) {
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
        return 0;
    }
}
