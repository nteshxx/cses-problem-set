import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class NumberSpiral {

    private static long calculateSpiralNumber(long x, long y) {
        // base case
        if (x == y && x == 1) {
            return 1;
        }

        // calculate
        if (x >= y) {
            // if x is greater
            return ((x % 2) == 0) ? (x * x - y + 1) : ((x - 1) * (x - 1) + y);
        } else {
            // if y is greater
            return ((y % 2) == 0) ? ((y - 1) * (y - 1) + x) : (y * y - x + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        // reader
        Scanner read = new Scanner(System.in);
        // writer
        OutputStream ws = new BufferedOutputStream(System.out);
        
        int t = read.nextInt();
        long x, y;

        for (int i = 0; i < t; i++) {
            x = read.nextLong();
            y = read.nextLong();

            // solve
            ws.write((calculateSpiralNumber(x, y) + "\n").getBytes());
        }

        read.close();

        // print result
        ws.flush();

        return;
    }

}
