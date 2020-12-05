package Day2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Day2
{
    private static List<String> readFile(String file) {
    List<String> list = Collections.emptyList();
    try {
        list = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
    }catch (IOException e) {
        e.printStackTrace();
    }
    return list;
    }

    private static int task1(List<String> list) {
        int valid = 0;

        // Loop through the list to separate all the strings
        for(String str : list) {
            String[] tmpString = str.split("[\\-\\ \\: ]+");
            char character = tmpString[2].charAt(0); // Character to find
            long count = tmpString[3].chars().filter(ch -> ch == character).count(); // Count all characters in all strings
            // If count is smaller or equal to tmpString[1] and bigger or equal to tmpString[0], add to valid passwords
            if(count <= Integer.parseInt(tmpString[1]) && count >= Integer.parseInt(tmpString[0])) {
                valid++;
            }
        }

        return valid;
    }

    private static int task2(List<String> list) {
        int valid = 0;

        // Loop through the list to separate all the strings
        for(String str : list) {
            String[] tmpString = str.split("[\\-\\ \\: ]+");
            char character = tmpString[2].charAt(0); // Character to find
            String[] splitStr = tmpString[3].split(""); // Split string stored in tmpString index 3
            char c1 = splitStr[Integer.parseInt(tmpString[0]) - 1].charAt(0); // Character at index1
            char c3 = splitStr[Integer.parseInt(tmpString[1]) - 1].charAt(0); // Character at index2
            // If character is present in one (but not both) location (XOR), add to valid
            if(c1 == character ^ c3 == character) {
                    valid++;
            }
        }

        return valid;
    }

    public static void main(String[] args)
    {
        List <String> values = readFile("./src/files/day2.txt"); // Read file into a String list
        System.out.println("Number of correct passwords in task 1: " + task1(values));
        System.out.println("Number of correct passwords in task 2: " + task2(values));
    }
}