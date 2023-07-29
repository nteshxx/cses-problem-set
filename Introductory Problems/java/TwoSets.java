import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class TwoSets {

    private static void divideSets(int n) throws IOException {
        // fast writer
        OutputStream out = new BufferedOutputStream(System.out);

        StringBuilder set1 = new StringBuilder("");
        StringBuilder set2 = new StringBuilder("");

        long sn = (n * (n + 1)) / 2;

        if ((sn % 2) == 0) {
            // solution exist
            out.write(("YES\n").getBytes());

            boolean iterationFlag = true;
            boolean evenFlag = n % 2 == 0;

            // iterate and build the sets
            for (int i = 1; i <= n; i++) {
                    
                if (iterationFlag) {
                    set1.append(i + " ");
                } else {
                    set2.append(i + " ");
                }

                // switch conditions based on odd and even value of n
                iterationFlag = evenFlag ? (i == (n / 2)) ? false : !iterationFlag : (i % 2 == 0) ? !iterationFlag : iterationFlag;

            }

            out.write(((evenFlag ? n/2 : (n/2 + 1)) + "\n").getBytes());
            out.write((set1.toString().trim() + "\n").getBytes());
            out.write(((n / 2) + "\n").getBytes());
            out.write(set2.toString().trim().getBytes());

        } else {
            // solution doesn't exist
            out.write(("NO").getBytes());
        }

        // print the solution
        out.flush();

        return;
    }

    public static void main(String[] args) throws IOException {
        // reader
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        read.close();

        // solve
        divideSets(n);

        return;
    }
    
}
