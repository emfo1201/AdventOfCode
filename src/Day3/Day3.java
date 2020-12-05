package Day3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Day3 {
    private static List<String> readFile(String file) {
        List<String> list = Collections.emptyList();
        try {
            list = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        }catch(IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static int task1(List<String> list) {
        int values = 0;
        int index = 0;

        // Find number of trees when you step three steps to the right and one down
        for(String str : list) {
            if(index > str.length() -1)
                index -= str.length();
            if(str.charAt(index) == '#')
                values++;
            index += 3;
        }

        return values;
    }

    private static int task2(List<String> list) {
        int values = 0;
        int index = 0;
        int row = 1;

        // Find number of trees when you step one step to the right and one down
        for(String str : list) {
            if(index > str.length() -1)
                index -= str.length();
            if(str.charAt(index) == '#')
                values++;
            index ++;
        }

        System.out.println("Number of trees 1 + 1: " + values);
        index = 0;
        values = 0;

        // Find number of trees when you step 5 steps to the right and one down
        for(String str : list) {
            if(index > str.length() -1)
                index -= str.length();
            if(str.charAt(index) == '#')
                values ++;
            index += 5;
        }

        System.out.println("Number of trees 5 + 1: " + values);
        index = 0;
        values = 0;

        // Find number of trees when you step 7 steps to the right and one down
        for(String str : list) {
            if(index > str.length() -1)
                index -= str.length();
            if(str.charAt(index) == '#')
                values ++;
            index += 7;
        }

        System.out.println("Number of trees 7 + 1: " + values);
        index = 0;
        values = 0;

        //Find number of trees when you step 1 step to the right and two down
        for(String str : list) {
            if(row % 2 != 0) {
                if (index > str.length() - 1)
                    index -= str.length();
                if (str.charAt(index) == '#')
                    values ++;
                index++;
            }
            row++;
        }

        System.out.println("Number of trees 1 + 2: " + values);

        return values;
    }


    public static void main(String[] args) {
        List<String> values = readFile("./src/files/day3.txt");
        System.out.println("Number of trees in task 1: " + task1(values));
        System.out.println("Number of trees in task 2: " + task2(values));
    }
}
