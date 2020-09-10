package online.memphis;

import java.io.*;
import java.util.ArrayList;
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
}
