package Day4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class Day4 {
    // Read file and place into a String list.
    private static List<String> readFile(String file) {
        List<String> list = new ArrayList<>();
        StringBuilder str = new StringBuilder(" ");
        try {
            List<String> lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
            // Merge all lines belonging to one passport into one line and place into List.
            for(String line : lines) {
                str.append(line);
                str.append(" ");
                if(line.trim().isEmpty()) {
                  list.add(str.toString());
                  str = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /*
    Method that counts all valid passwords
    */
    private static int task1(List<String> list, String[] words) {
        int values = 0;
        int found = 0;

        // Loop through list and match all words. If all found, increment values
        for(String str : list) {
            for (String word : words) {
                if (str.contains(word)) {
                    found++;
                }
            }
            if(found == 7) {
                values++;
            }
            found = 0;
        }

        return values;
    }

    /*
    Method that counts all valid passports
     */
    private static int task2(List<String> list, String[] words){
        int values = 0;
        int found = 0;

        // Loop that counts how many valid fields each password contain
        for(String str : list) {
            String[] tmpStr = str.split(" ");
            for(String s : tmpStr){
                // Split each index value by delimiter
                String[] sp = s.split(":");
                // Match all valid values
                if(sp[0].equals(words[0]) && sp[1].matches("\\b(?:amb|blu|brn|gry|grn|hzl|oth)\\b")) {
                        found++;
                }
                else if(sp[0].equals(words[1]) && sp[1].matches("[0-9]{9}")) {
                    found++;
                }
                else if(sp[0].equals(words[2]) && sp[1].chars().allMatch(Character::isDigit)) {
                    if(Integer.parseInt(sp[1]) >= 2020 && Integer.parseInt(sp[1]) <= 2030)
                        found++;
                }
                else if(sp[0].equals(words[3]) && sp[1].matches("^.{1}[a-f0-9]{6}")) {
                    found++;
                }
                else if(sp[0].equals(words[4]) && sp[1].chars().allMatch(Character::isDigit)) {
                    if(Integer.parseInt(sp[1]) >= 1920 && Integer.parseInt(sp[1]) <= 2002)
                        found++;
                }
                else if(sp[0].equals(words[5]) && sp[1].chars().allMatch(Character::isDigit)) {
                    if(Integer.parseInt(sp[1]) >= 2010 && Integer.parseInt(sp[1]) <= 2020)
                        found++;
                }
                else if(sp[0].equals(words[6])) {
                    if((sp[1].substring(sp[1].length() - 2)).equals("cm")) {
                        if(sp[1].substring(0,3).chars().allMatch(Character::isDigit)) {
                            if((Integer.parseInt(sp[1].substring(0,3)) >= 150 && Integer.parseInt(sp[1].substring(0,3)) <= 193))
                                found++;
                        }
                    }
                    else if((sp[1].substring(sp[1].length() - 2)).equals("in")) {
                        if(sp[1].substring(0,2).chars().allMatch(Character::isDigit)) {
                            if((Integer.parseInt(sp[1].substring(0,2)) >= 59 && Integer.parseInt(sp[1].substring(0,2)) <= 76))
                                found++;
                        }
                    }
                }
            }
            if(found == 7) {
                values++;
            }
            found = 0;
        }

        return values;
    }

    public static void main(String[] args) {
        List<String> values = readFile("./src/files/day4.txt");
        String[] words = {"ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt"};
        System.out.println("Valid passports in task 1: " + task1(values, words));
        System.out.println("Valid passports in task 2: " + task2(values, words));
    }
}
