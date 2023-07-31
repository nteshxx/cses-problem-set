import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CreatingStrings {
    
    private static Set<String> permutations = new TreeSet<String>();

    private static void permutate(String string, int fixedIndex, int lastIndex) {
        // base case
        if (fixedIndex == lastIndex) {
            permutations.add(string);
            return;
        }

        // For same level and different fixedIndex possible chars
        for(int i=fixedIndex; i <= lastIndex; i++) {
            // swap the fixedIndex char with ith char
            string = swap(string, fixedIndex, i);
            // do recursive call for next level
            permutate(string, fixedIndex + 1, lastIndex);

            // undo the swap and backtrack
            string = swap(string, fixedIndex, i);
        }

        return;
    }

    private static String swap(String string, int index1, int index2) {
        StringBuilder swappedString = new StringBuilder(string);
        swappedString.setCharAt(index1, string.charAt(index2));
        swappedString.setCharAt(index2, string.charAt(index1));
        return swappedString.toString();
    }

    public static void main(String[] args) throws IOException {
        // reader
        Scanner sc = new Scanner(System.in);
        String string = sc.next();
        sc.close();

        // writer
        OutputStream out = new BufferedOutputStream(System.out);

        // solve
        permutate(string, 0, string.length() - 1);
        out.write((permutations.size() + "\n").getBytes());
        for(String permutation : permutations) {
            out.write((permutation + "\n").getBytes());
        }

        // print the solution
        out.flush();

        return;
    }
}
