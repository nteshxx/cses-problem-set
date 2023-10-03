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
        String[] numbers = read.nextLine().split("\\s+");
        read.close();

        // index array
        int[] indexArray = new int[n+1];
        for (int i = 0; i < n; i++) {
            indexArray[Integer.parseInt(numbers[i])] = i + 1;
        }

        // solve using index array
        int rounds = 1;
        for (int i = 1; i < n; i++) {
            if (indexArray[i] > indexArray[i+1]) {
                rounds++;
            }
        }
        
        // print the solution
        System.out.println(rounds);

        return;
    }

}
