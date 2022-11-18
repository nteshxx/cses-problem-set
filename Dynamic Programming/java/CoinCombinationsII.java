import java.util.Scanner;

public class CoinCombinationsII {

    // using tabulation method
    // bottom up approach
    private static void distinctCombinations(int[] coins, int sum) {        
        long[] ways = new long[sum+1];
        // base case
        ways[0] = 1;

        // loop of coins
        for (int coin : coins) {
            // loop of n sums
            for (int i = 1; i <= sum; i++) {
                if (i-coin >= 0) {
                    // for non negative indexes
                    ways[i] = (ways[i] + ways[i-coin]) % 1000000007;
                }
            }
        }

        // print the result
        System.out.println(ways[sum]);
    }

    public static void main(String[] args) {

        // Initializing scanner
        Scanner sc = new Scanner(System.in);
        
        // taking input
        // number of coins
        int n = sc.nextInt();
        // sum
        int sum = sc.nextInt();
        // coins
        int[] coins = new int[n];
        for(int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }
        // closing input stream
        sc.close();
 
        // solve
        distinctCombinations(coins, sum);
        
    }
}
