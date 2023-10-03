import java.util.Scanner;

public class CollectingNumbers {
    
    // using bruteforce method
    // private static int totalNumberOfRoundsToCollect(int[] numbers) {
    //     int currentNumber = 1;
    //     int rounds = 0;
    //     while (currentNumber <= numbers.length) {
    //         for (int i = 0; i < numbers.length; i++) {
    //             currentNumber = numbers[i] == currentNumber ? currentNumber + 1 : currentNumber;
    //         }
    //         rounds++;
    //     }
    //     return rounds;
    // }

    public static void main(String[] args) {
        // reader
        Scanner read = new Scanner(System.in);
        
        // read inputs
        int n = read.nextInt();
        read.nextLine();
        String[] numbeStrings = read.nextLine().split("\\s+");
        read.close();

        // index array
        int[] numbers = new int[n+1];
        for (int i = 0; i < n; i++) {
            numbers[Integer.parseInt(numbeStrings[i])] = i + 1;
        }

        // solve using index array
        int rounds = 1;
        for (int i = 1; i < n; i++) {
            if (numbers[i] > numbers[i+1]) {
                rounds++;
            }
        }
        
        // print the solution
        System.out.println(rounds);

        return;
    }

}
