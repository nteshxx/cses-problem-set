import java.util.Scanner;

public class GridPaths {
    
    private static void gridTraveler(int size, Character[][] maze) {
        
        // Initialize 2d array
        int[][] waysToTravel = new int[size+1][size+1];

        // base case : ways to travel 1x1 grid = 1
        waysToTravel[1][1] = 1;

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                
                // traps
                if (maze[i-1][j-1].equals('*')) {
                    waysToTravel[i][j] = 0;
                    continue;
                }

                // number of ways adds up from upper row and left column
                waysToTravel[i][j] += (waysToTravel[i-1][j] + waysToTravel[i][j-1]) % 1000000007;

            }
        }

        // print the result
        System.out.println(waysToTravel[size][size]);

    }

    public static void main(String[] args) {
        // Initializing scanner
        Scanner sc = new Scanner(System.in);
        
        // taking input
        // size of nxn grid
        int size = sc.nextInt();
        
        String inputLine;
        // input grid maze
        Character[][] maze = new Character[size][size];
        for(int i = 0; i < size; i++) {
            inputLine = sc.next();
            for (int j = 0; j < size; j++) {
                maze[i][j] = inputLine.charAt(j);
            }
        }
        
        // closing input stream
        sc.close();

        // solve
        gridTraveler(size, maze);

    }
}
