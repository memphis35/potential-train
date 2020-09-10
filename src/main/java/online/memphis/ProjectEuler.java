package online.memphis;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProjectEuler {

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
}
