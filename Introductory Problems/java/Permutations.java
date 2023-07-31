import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class Permutations {
    private static int n;

    private static void printBeautifulPermutations() throws IOException {
        // fast output
        // BufferedOutputStream is safer and consistent rather then BufferedWriter
        OutputStream bsOut = new BufferedOutputStream(System.out);
        // BufferedWriter bwOut = new BufferedWriter(new OutputStreamWriter(System.out));

        // corner case
        if (n == 2 || n == 3) {
            bsOut.write("NO SOLUTION".getBytes());
            // Flush the buffer to print the output.
            bsOut.flush();
            return;
        }

        // difference
        int count = 2;

        // first print evens then odds
        for(int i = 1; i <= n; i++) {
            count = count > n ? 1 : count;
            bsOut.write((count + " ").getBytes());
            count += 2;
        }

        // Flush the buffer to print the output.
        bsOut.flush(); 

        return;
    }
    
    public static void main(String[] args) throws IOException {
        // reader
        Scanner read = new Scanner(System.in);
        n = read.nextInt();
        read.close();

        // solve
        printBeautifulPermutations();

        return;
    }

}
