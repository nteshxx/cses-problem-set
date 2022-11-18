import java.util.Arrays;
import java.util.Scanner;

public class MinimizingCoins {
    
    // using tabulation method
    // bottom up approach
    private static void minimumCoins(int[] coins, int sum) {        
        
        int[] count = new int[sum+1];
 
        // fill count with max values i.e. sum+1
        Arrays.fill(count, sum+1);
 
        // base case
        count[0] = 0;
 
        // loop of n sums
        for (int i = 1; i <= sum; i++) {
            // loop of coins
            for (int coin: coins) {
                if (coin == i) {
                    // ith sum == coin
                    count[i] = 1;
                    break;
                }
                if (i-coin >= 0) {
                    // for non negative indexes
                    count[i] = Math.min(count[i], 1 + count[i-coin]);
                }
            }
        }
 
        // if no ways to make the sum print -1 else print minimum number of coins
        int result = count[sum] == sum+1 ? -1 : count[sum];
        // print the result
        System.out.println(result);
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
        minimumCoins(coins, sum);
 
    }
 
}
