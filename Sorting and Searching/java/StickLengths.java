import java.util.Arrays;
import java.util.Scanner;

public class StickLengths {
    
    public static void main(String[] args) {
        // reader
        Scanner read = new Scanner(System.in);

        // read inputs
        int n = read.nextInt();
        read.nextLine();
        String[] stringArray = read.nextLine().split("\\s+");
        long[] numberArray = new long[n];
        for (int i = 0; i < stringArray.length; i++) {
            numberArray[i] = Long.parseLong(stringArray[i]);
        }
        read.close();

        // solve
        long minimumCost = 0;
        // sorting array
        Arrays.sort(numberArray);
        // make all elements equal to the median element
        for (int i = 0; i < numberArray.length; i++) {
            minimumCost += Math.abs(numberArray[i] - numberArray[n/2]);
        }

        // print the solution
        System.out.println(minimumCost);

        return;
    }

}
