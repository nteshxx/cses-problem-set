import java.util.Scanner;

public class MissingNumber {
    private static int n;

    private static int findMissingNumber(boolean[] numbers) {
        for (int i = 1; i <= n; i++) {
            if (!numbers[i]) {
                // missing number found
                return i;
            }
        }

        // not found
        return -1;
    }

    public static void main(String[] args) {
        // Reader
        Scanner read = new Scanner(System.in);
        n = read.nextInt();

        boolean[] numbers = new boolean[n+1];

        for (int i = 1; i <= n-1; i++) {
            numbers[read.nextInt()] = true;
        }
        read.close();

        // print
        System.out.print(findMissingNumber(numbers));

    }
}
