import java.util.Scanner;

public class DiceCombinations {

    // using tabulation method
    // bottom up approach
    private static void combinations(int n) {        
        long[] ways = new long[n+1];
        // base case
        ways[0] = 1;

        // loop of n
        for (int i = 1; i <= n; i++) {
            // loop of dice-numbers/coins
            for (int j = 1; j <= 6; j++) {
                if (i-j >= 0) {
                    // for non negative indexes
                    ways[i] = (ways[i] + ways[i-j]) % 1000000007;
                }
            }
        }

        // print the result
        System.out.println(ways[n]);
    }

    public static void main(String[] args) {

        // Initializing scanner
        Scanner sc = new Scanner(System.in);
        
        // taking input
        int n = sc.nextInt();

        // closing input stream
        sc.close();

        // solve
        combinations(n);
        
    }
}
