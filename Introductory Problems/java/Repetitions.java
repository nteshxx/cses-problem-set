import java.util.Scanner;

public class Repetitions {

    private static int findLongestSubstring(String dna) {
        char currentChar = 'A';
        int currentCount = 0, longestCount = 0;

        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == currentChar) {
                currentCount++;
            } else {
                currentChar = dna.charAt(i);
                currentCount = 1;
            }

            longestCount = Math.max(currentCount, longestCount);
        }

        return longestCount;
    }

    public static void main(String[] args) {
        // reader
        Scanner read = new Scanner(System.in);
        
        String dna = read.next();
        read.close();

        // solve
        System.out.println(findLongestSubstring(dna));

    }
    
}
