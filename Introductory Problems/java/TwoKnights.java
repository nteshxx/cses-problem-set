import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class TwoKnights {

    private static void printPossibleCombinations(int k) throws IOException { 
        // fast writer
        OutputStream out = new BufferedOutputStream(System.out);
        
        // iterate and solve for each k * k grids
        for (long i = 1; i <= k; i++) {
            long allPossibleWays = i * i * (i * i -1) / 2;
            long invalidCombinations = 2 * 2 * (i - 2) * (i - 1);
            out.write(((allPossibleWays - invalidCombinations) + "\n").getBytes());
        }

        // print the solutions
        out.flush();
        
        return;
    }

    public static void main(String[] args) throws IOException {
        Scanner read = new Scanner(System.in);
        int k = read.nextInt();
        read.close();

        // solve
        printPossibleCombinations(k);

        return;
    }
    
}
