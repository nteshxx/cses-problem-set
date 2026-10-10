import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Optional;

public class Labyrinth {
    private static final char START = 'A';
    private static final char END = 'B';
    private static final char FLOOR = '.';

    private static int n;
    private static int m;
    private static char[][] grid;

    private static class GridNode {
        private int row;
        private int col;

        public GridNode(int row, int col) {
            this.row = row; this.col = col;
        }

        public int getRow() {
            return this.row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return this.col;
        }

        public void setCol(int col) {
            this.col = col;
        }
    }

    private enum Move {
        UP(-1, 0, 'U'),
        DOWN(1, 0, 'D'),
        LEFT(0, -1, 'L'),
        RIGHT(0, 1, 'R');

        final int rowD;
        final int colD;
        final char step;

        Move(int rowD, int colD, char step) {
            this.rowD = rowD;
            this.colD = colD;
            this.step = step;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        n = Integer.parseInt(input.split(" ")[0]);
        m = Integer.parseInt(input.split(" ")[1]);
        grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // solve
        Optional<GridNode> pathExist = Optional.empty();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (grid[row][col] == START) {
                    pathExist = BFS(new GridNode(row, col));
                    break;
                }
            }
        }

        // backtrack and build path
        String shortestPath = "";
        if (pathExist.isPresent()) {
            shortestPath = getShortestPath(pathExist.get());
        }

        System.out.println(pathExist.isPresent() ? "YES" : "NO");
        if (pathExist.isPresent()) System.out.println(shortestPath.length());
        if (pathExist.isPresent()) System.out.print(shortestPath);
    }

    private static String getShortestPath(GridNode current) {
        StringBuilder shortestPath = new StringBuilder();
        while (grid[current.getRow()][current.getCol()] != START) {
            char prevStep = grid[current.getRow()][current.getCol()];
            shortestPath.append(prevStep);

            int prevRow = current.getRow();
            int prevCol = current.getCol();
            switch (prevStep) {
                case 'U':
                    prevRow += Move.DOWN.rowD;
                    prevCol += Move.DOWN.colD;
                    break;
                case 'D':
                    prevRow += Move.UP.rowD;
                    prevCol += Move.UP.colD;
                    break;
                case 'L':
                    prevRow += Move.RIGHT.rowD;
                    prevCol += Move.RIGHT.colD;
                    break;
                case 'R':
                    prevRow += Move.LEFT.rowD;
                    prevCol += Move.LEFT.colD;
                    break;
            }
            current.setRow(prevRow);
            current.setCol(prevCol);
        }

        shortestPath.reverse();
        return shortestPath.toString();
    }

    public static boolean isInsideGrid(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }

    public static Optional<GridNode> BFS(GridNode start) {
        ArrayDeque<GridNode> queue = new ArrayDeque<>();

        // start BFS with start point A
        queue.add(start);

        while (!queue.isEmpty()) {
            GridNode current = queue.poll();
            for (Move nextMove : Move.values()) {
                int nextRow =  current.getRow() + nextMove.rowD;
                int nextCol =  current.getCol() + nextMove.colD;
                if (isInsideGrid(nextRow, nextCol) && grid[nextRow][nextCol] == END) {
                    grid[nextRow][nextCol] = nextMove.step;
                    return Optional.of(new GridNode(nextRow, nextCol));
                }
                if (isInsideGrid(nextRow, nextCol) && grid[nextRow][nextCol] == FLOOR) {
                    queue.add(new GridNode(nextRow, nextCol));
                    grid[nextRow][nextCol] = nextMove.step;
                }
            }
        }
        return Optional.empty();
    }
}
