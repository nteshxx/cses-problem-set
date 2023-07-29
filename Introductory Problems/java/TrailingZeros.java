import java.util.Scanner;

public class TrailingZeros {

    private static void countTrailingZeroes(long n) {
        long zeroes = 0;

        // iterate and calculate
        for (int denominator = 5; denominator <= n; denominator *= 5) {
            zeroes += (n / denominator);
        }

        // print the solution
        System.out.println(zeroes);

        return;
    }
    
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        long n = read.nextLong();
        read.close();

        // solve
        countTrailingZeroes(n);

        return;
    }
}
