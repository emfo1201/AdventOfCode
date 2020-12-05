package Day5;

import java.util.ArrayList;
import java.util.List;

class Task2 {

    Task2() {}

    /*
        Method that finds all possible ticket combinations and return those that
        does not exist in the read file.
     */
    List<String> listOfSeats(char[] set, int number) {
        List<String> row = new ArrayList<>();
        int n = set.length;
        char[] set2 = {'R', 'L'};
        int m = set2.length;
        // Find all possible combinations of B and F
        addAll(set, "", n, number, row);

        return row;
    }

    /*
        Redundant method that adds all combinations to a list array
     */
    private void addAll(char[] set, String prefix, int n, int k, List<String> list) {
        // Every time k reaches 0, add the prefix string to list array
        if(k == 0) {
            list.add(prefix);
            return;
        }

        // Loop through all possible combinations
        for(int i = 0; i < n; i++) {
            String newPrefix = prefix + set[i];
            addAll(set, newPrefix, n, k-1, list);
        }
    }
}
