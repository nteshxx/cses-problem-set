import java.util.Arrays;
import java.util.Scanner;

public class MissingCoinSum {

    // using greedy approach
    private static long findSmallestNonRepresentableSum(long[] coins) {
        long smallestSum = 1;
        // sort coins array
        Arrays.sort(coins);

        for (long coin : coins) {
            if (coin <= smallestSum) {
                smallestSum += coin;
            } else {
                break;
            }
        }
        
        return smallestSum;
    }
    
    public static void main(String[] args) {
        // reader
        Scanner read = new Scanner(System.in);
        
        // read inputs
        int n = read.nextInt();
        read.nextLine();
        String[] stringArray = read.nextLine().split("\\s+");
        long[] coins = new long[n];
        for (int i = 0; i < stringArray.length; i++) {
            coins[i] = Long.parseLong(stringArray[i]);
        }
        read.close();

        // solve
        System.out.println(findSmallestNonRepresentableSum(coins));

        return;
    }

}
