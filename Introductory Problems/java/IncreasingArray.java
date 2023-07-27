import java.util.Scanner;

public class IncreasingArray {
    private static int n;

    private static long countMoves(long[] array) {
        long moves = 0;

        for (int i = 0; i < n-1; i++) {
            if (array[i] > array[i+1]) {
                // add the moves
                moves += (array[i] - array[i+1]);
                
                // update the value
                array[i+1] = array[i];
            }
        }

        return moves;
    }
    
    public static void main(String[] args) {
        // reader
        Scanner read = new Scanner(System.in);
        n = read.nextInt();

        long[] array = new long[n];

        for (int i = 0; i < n; i++) {
            array[i] = read.nextLong();
        }
        read.close();

        // solve
        System.out.print(countMoves(array));
        
    }

}
