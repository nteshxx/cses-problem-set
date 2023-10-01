import java.util.Scanner;

public class MaximumSubarraySum {
    
    public static void main(String[] args) {
        // reader
        Scanner read = new Scanner(System.in);
        
        // read inputs
        int n = read.nextInt();
        read.nextLine();
        String numberString = read.nextLine();
        read.close();

        // convert string to integer array (optimization)
        String[] stringArray = numberString.split("\\s+");
        long[] numArray = new long[n];
        for (int i = 0; i < stringArray.length; i++) {
            numArray[i] = Long.parseLong(stringArray[i]);
        }

        // initialize
        long overallMaxSum = Integer.MIN_VALUE;
        long maxSumSoFar = 0;

        // solve using kadane's algorithm
        for (int i = 0; i < n; i++) {
            maxSumSoFar = Math.max(maxSumSoFar + numArray[i], numArray[i]);
            overallMaxSum = maxSumSoFar > overallMaxSum ? maxSumSoFar : overallMaxSum;
        }

        // print the solution
        System.out.println(overallMaxSum);

        return;
    }
    
}
