package online.memphis.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

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
}
