import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ChessboardAndQueens {

    private static List<String> solutions = new ArrayList<String>();
    private static Integer n = 8;

    // queens are not attacking each other
    private static boolean isValid(List<StringBuilder> board, int row, int column) {
        int i = 0;
        int j = 0;

        // check for reserved
        if (board.get(row).toString().charAt(column) == '*') {
            return false;
        }

        // check for column
        i = row;
        j = column;
        while (i >= 0) {
            if (board.get(i).toString().charAt(j) == 'Q') {
                return false;
            }
            i--;
        }

        // check diagonal1
        i = row;
        j = column;
        while (i >= 0 && j >= 0) {
            if (board.get(i).toString().charAt(j) == 'Q') {
                return false;
            }
            i--;
            j--;
        }

        // check diagonal2
        i = row;
        j = column;
        while (i >= 0 && j < n) {
            if (board.get(i).toString().charAt(j) == 'Q') {
                return false;
            }
            i--;
            j++;
        }

        return true;
    }

    private static void nQueen(List<StringBuilder> board, int currentRow) {
        // base case
        if (currentRow == n) {
            solutions.add(board.toString());
            return;
        }

        // try placing queen in each column of current row
        for (int column = 0; column < n; column++) {

            // isValid() function to check the board for placing the queen
            // false -> no solution on this branch
            if (isValid(board, currentRow, column)) {

                // placing queen at ith column and currentRow
                board.get(currentRow).setCharAt(column, 'Q');

                // try placing queen in each column of next row
                nQueen(board, currentRow + 1);

                // remove the queen for trying another possibilities
                board.get(currentRow).setCharAt(column, '.');
            }
        }

        return;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<StringBuilder> board = new ArrayList<StringBuilder>();

        // row inputs
        int i = n;
        while (i > 0) {
            board.add(new StringBuilder(sc.next()));
            i--;
        }
        sc.close();

        // solve
        nQueen(board, 0);

        // print answer
        System.out.println(solutions.size());
        // System.out.println(solutions);

        return;
        // end of program
    }

}
