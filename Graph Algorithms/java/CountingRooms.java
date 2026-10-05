import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    final int rowDelta;
    final int colDelta;

    Direction(int rowDelta, int colDelta) {
        this.rowDelta = rowDelta;
        this.colDelta = colDelta;
    }
}

class Node {
    int row;
    int col;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }
}

public class CountingRooms {
    private static int n, m;
    private static char[][] buildingMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        StringTokenizer st = new StringTokenizer(line);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        buildingMap = new char[n][m];

        for (int i = 0; i < n; i++) {
            buildingMap[i] = br.readLine().toCharArray();
        }

        int totalRooms = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (buildingMap[row][col] == '.') {
                    totalRooms++;
                    // DFS search / Flood Fill
                    exploreEntireRoom(new Node(row, col));
                }
            }
        }

        System.out.println(totalRooms);
    }

    /**
     * Explores and marks all connected floor tiles belonging to the same room.
     */
    private static void exploreEntireRoom(Node startPoint) {
        Queue<Node> queue = new ArrayDeque<>();

        // Start BFS from the first discovered floor square
        queue.add(startPoint);
        buildingMap[startPoint.row][startPoint.col] = '#';
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Try moving in all 4 directions
            for (Direction direction : Direction.values()) {
                int nextRow = current.getRow() + direction.rowDelta;
                int nextCol = current.getCol() + direction.colDelta;
                if (isInsideGrid(nextRow, nextCol) && buildingMap[nextRow][nextCol] == '.') {
                    queue.add(new Node(nextRow, nextCol));
                    buildingMap[nextRow][nextCol] = '#';
                }
            }
        }
    }

    private static boolean isInsideGrid(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }
}
