import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
 
public class DigitQueries {
 
    private static char findDigitAtPosition(long k) {
        int n = 1;
        long totalDigits = 0;
 
        while (true) {
            long nextTotalDigits = totalDigits +  (n * 9 * (long) Math.pow(10, n - 1));
            if (k <= nextTotalDigits) {
                long number = (long) Math.pow(10, n - 1) + (k - totalDigits - 1) / n;
                int index = (int) ((k - totalDigits - 1) % n);
                // System.out.println("number: " + number + ", index: " + index);
                return Long.toString(number).charAt(index);
            }
            totalDigits = nextTotalDigits;
            n++;
        }
    }
 
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
 
        long[] queries = new long[n];
        
        for (int i = 0; i < n; i++) {
            queries[i] = sc.nextLong();
        }
 
        sc.close();

        // writer
        OutputStream out = new BufferedOutputStream(System.out);
 
        for (long k : queries) {
            // solve each queries
            out.write((findDigitAtPosition(k) + "\n").getBytes());
        }

        // print the solution
        out.flush();

        return;
    }
}
