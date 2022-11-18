import java.util.Arrays;
import java.util.Scanner;

public class RemovingDigits {

    private static void removeDigits(int n) {
        
        // Initializing array
        int steps[] = new int[n+1];
        // set all to max i.e. n+1
        Arrays.fill(steps, n+1);
        // base case
        steps[0] = 0;

        // Initializing temp variables
        int forNum = 0;
        int lastDigit = 0;

        // loop for n numbers
        for (int i = 1; i <= n; i++) {
            if (n <= 9) {
                // case number is a single digit
                steps[i] = 1;
                continue;
            }
            forNum = i;
            while(forNum != 0) {
                lastDigit = forNum%10;
                steps[i] = Math.min(steps[i], 1+steps[i - lastDigit]);
                forNum /= 10;
            }
        }

        System.out.println(steps[n]);
    }

    public static void main(String[] args) {

        // Initializing scanner
        Scanner sc = new Scanner(System.in);
        
        // taking input
        int n = sc.nextInt();
        // closing input stream
        sc.close();

        // solve
        removeDigits(n);
        
    }
}
