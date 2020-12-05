package Day5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5 {

    private static List<String> readFile(String file) {
        List<String> list = Collections.emptyList();

        try {
            list = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static List<Integer> task1(List<String> list) {
        List<Integer> seatID = new ArrayList<>();

        for(String str : list) { // Loop through list of seats
            String[] tmpStr = str.split("");
            int upper = 127, lower = 0, seatLow = 0, seatUp = 7;

            // Loop through each seat to find it's seat number
            for(int i = 0; i < tmpStr.length; i++) {
                if(tmpStr[i].equals("F")) {
                    upper = (upper - lower) / 2 + lower;
                }
                else if(tmpStr[i].equals("B")) {
                    lower = (upper - lower) / 2 + lower + 1;
                }
                else if(tmpStr[i].equals("L")) {
                    seatUp = (seatUp - seatLow) / 2 + seatLow;
                }
                else if(tmpStr[i].equals("R")) {
                    seatLow = (seatUp - seatLow) / 2 + seatLow + 1;
                }
            }
            seatID.add((upper * 8) + seatLow); // Add seat number to list
        }

        return seatID;
    }

    // Find available seat number
    private static int task2(List<String> file) {
        List<String> seats = new ArrayList<>();
        List<String> emptySeats = new ArrayList<>();
        int availableSeat = 0;

        Task2 task2 = new Task2();

        char[] set1 = { 'F', 'B'};
        int number = 7;
        List<String> row = task2.listOfSeats(set1, number);

        char[] set2 = { 'L', 'R'};
        number = 3;
        List<String> seat = task2.listOfSeats(set2, number);

        // Add seat chars to row chars to find all possible seat combinations
        for(String r : row) {
            for(String s : seat) {
                seats.add(r + s);
            }
        }

        // Loop through strings array and compare to list. If a strings string doesn't exist
        // in list, add to emptySeats array.
        for(String str : seats) {
            if(!file.contains(str)) {
                emptySeats.add(str);
            }
        }

        List<Integer> seatID = task1(emptySeats); // Fill list with all seatID:s
        Collections.sort(seatID); // Sort list

        // Find the seatID with no seats with IDs +1 and -1
        for(Integer i : seatID) {
            if(!seatID.contains(i-1) && !seatID.contains(i+1))
                availableSeat = i;
        }
        return availableSeat; // Return available seat number
    }

    public static void main(String[] args) {
        List<String> file = readFile("./src/files/day5.txt");
        List<Integer> seatID = task1(file);
        Integer highestSeatID = Collections.max(seatID); // Find highest seat ID
        System.out.println("Highest seat ID in task 1: " + highestSeatID);
        System.out.println("Available seat ID in task 2: " + task2(file));


    }
}
