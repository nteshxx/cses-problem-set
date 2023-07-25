import java.util.Scanner;
 
public class GridPaths {
 
    private static int n = 7;
    private static int paths = 0;
    private static char[] reservedSteps = new char[n*n];
 
    private static void traverseGrid(boolean[][] grid, int row, int column, int steps) {
 
        // goal
        // reached the destination after traversing complete grid
        if (row == n-1 && column == 0) {
            if (steps == n*n-1) {
                paths++;
            }
 
            // optimization 2
            // reached the destination without traversing complete grid
            return;
        }
 
        // optimization 1
        // if already visited
        if ((grid[row][column])
        // optimization 3
        // hit the wallS with unvisited blocks on both side
            || ((row == 0 || row == 6) && column > 0 && column < 6 && !grid[row][column - 1] && !grid[row][column + 1])   // UP DOWN WALLS
            || ((column == 0 || column == 6) && row > 0 && row < 6 && !grid[row - 1][column] && !grid[row + 1][column])   // LEFT RIGHT
        // optimization 4
        // hit a block where unvisited blocks on both side on block on forward path
            || (row > 0 && row < 6 && column > 0 && column < 6 && grid[row - 1][column] && grid[row + 1][column] && !grid[row][column - 1] && !grid[row][column + 1])   // UP DOWN
            || (row > 0 && row < 6 && column > 0 && column < 6 && grid[row][column - 1] && grid[row][column + 1] && !grid[row - 1][column] && !grid[row + 1][column]))  // LEFT RIGHT
        {
            return;
        }
 
        // move as per the instructed path
        if (reservedSteps[steps] != '?') {
            switch(reservedSteps[steps]) {
                case 'U':
                    if (row > 0 && !grid[row - 1][column]) {
                        steps++;
                        grid[row][column] = true;
                        traverseGrid(grid, row-1, column, steps);
                    }
                    break;
                case 'D':
                    if (row < n-1 && !grid[row + 1][column]) {
                        steps++;
                        grid[row][column] = true;
                        traverseGrid(grid, row+1, column, steps);
                    }
                    break;
                case 'L':
                    if (column > 0 && !grid[row][column - 1]) {
                        steps++;
                        grid[row][column] = true;
                        traverseGrid(grid, row, column-1, steps);
                    }
                    break;
                case 'R':
                    if (column < n-1 && !grid[row][column + 1]) {
                        steps++;
                        grid[row][column] = true;
                        traverseGrid(grid, row, column+1, steps);
                    }
                    break;
            }
            
            // backtrack
            grid[row][column] = false;
 
            // reduce the step
            steps--;
            
            return;
        }
 
        // increase the step
        steps++;
 
        // move UP
        if (row > 0 && !grid[row - 1][column]) {
            grid[row][column] = true;
            traverseGrid(grid, row - 1, column, steps);
        }
 
        // move DOWN
        if (row < n-1 && !grid[row + 1][column]) {
            grid[row][column] = true;
            traverseGrid(grid, row + 1, column, steps);
        }
 
        // move LEFT
        if (column > 0 && !grid[row][column - 1]) {
            grid[row][column] = true;
            traverseGrid(grid, row, column - 1, steps);
        }
 
        // move RIGHT
        if (column < n-1 && !grid[row][column + 1]) {
            grid[row][column] = true;
            traverseGrid(grid, row, column + 1, steps);
        }
 
        // backtrack
        grid[row][column] = false;
 
        // reduce the step
        steps--;
        
        return;
 
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        sc.close();
 
        for (int i = 0; i < path.length(); i++) {
            reservedSteps[i] = path.charAt(i);
        }
 
        boolean[][] grid = new boolean[n][n];
 
        // solve
        traverseGrid(grid, 0, 0, 0);
 
        // print the total paths
        System.out.println(paths);
 
    }
 
}
