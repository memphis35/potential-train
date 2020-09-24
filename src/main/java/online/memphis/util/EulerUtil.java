package online.memphis.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EulerUtil {

    public static int[][] parseMatrixList(String filename) {
        List<String> matrix = getMatrixList(filename);
        int[][] result = new int[matrix.size()][matrix.get(0).split(" ").length];
        for (int i = 0; i < matrix.size(); i++) {
            String[] nums = matrix.get(i).split(" ");
            for (int j = 0; j < nums.length; j++) {
                result[i][j] = parseInt(nums[j]);
            }
        }
        return result;
    }

    private static List<String> getMatrixList(String filename) {
        List<String> nums = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.ready()) {
                nums.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nums;
    }

    private static int parseInt(String num) {
        if (num.startsWith("0")) num = num.replaceFirst("0", "");
        return Integer.parseInt(num);
    }

    public static List<int[]> getNumbers(String filename) {
        List<int[]> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.ready()) {
                String num = reader.readLine();
                int[] number = new int[num.length()];
                for (int i = 0; i < num.length(); i++) {
                    number[i] = num.charAt(i) - 48;
                }
                result.add(number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<int[]> parsePyramidList(String filename) {
        List<String> pyramid = getMatrixList(filename);
        List<int[]> result = new ArrayList<>();
        Iterator<String> iter = pyramid.iterator();
        while (iter.hasNext()) {
            String[] array = iter.next().split(" ");
            int[] numbers = new int[array.length];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = parseInt(array[i]);
            }
            result.add(numbers);
        }
        return result;
    }

    public static String[] parseFileTask22(String filename) {
        String[] result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            result = reader.readLine().split("\\W+");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void printArray(int[] array) {
        StringBuilder builder = new StringBuilder();
        for (int x : array) {
            builder.append(x);
        }
        System.out.println(builder.toString());
    }

    public static void recheckArray(int[] array) {
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

    public static void recheckArray(long[] array) {
        for (int i = 0; i < array.length - 1 && array[i] >= 0; i++) {
            long temp = array[i];
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

    public static void normalizeList(List<Integer> selfPowered) {
        int temp;
        for (int i = 0; i < selfPowered.size(); i++) {
            temp = selfPowered.get(i);
            if (temp > 9) {
                selfPowered.set(i, temp % 10);
                if (i == selfPowered.size() - 1) {
                    selfPowered.add(temp / 10);
                } else {
                    selfPowered.set(i + 1, selfPowered.get(i + 1) + temp / 10);
                }
            }
        }
    }
}
