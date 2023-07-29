import java.util.Scanner;

public class BitStrings {
    
    private static long modValue = 1000000007;

    private static void possibleBitStrings(int n) {
        long possibleBitStrings = 1;

        for (int i = 0; i < n; i++) {
            possibleBitStrings = (2 * possibleBitStrings) % modValue;
        }

        // print the solution
        System.out.print(possibleBitStrings);

        return;
    }
    
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        read.close();

        // solve
        possibleBitStrings(n);

        return;
    }
}
