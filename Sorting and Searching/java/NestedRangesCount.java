import java.io.*;
import java.util.*;

public class NestedRangesCount {
    
    // Range Structure
    private static class Range {
        int start, end, index;

        Range(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }

    // Fenwick Tree
    private static int[] fenwickTree = new int[200001];

    // Fenwick Tree update function
    private static void update(int n, int i, long delta) {
        for (; i <= n; i += i & (-i)) {
            fenwickTree[i] += delta;
        }
    }

    // Fenwick Tree query function
    private static int query(int i) {
        int sum = 0;
        for (; i > 0; i -= i & (-i)) {
            sum += fenwickTree[i];
        }
        return sum;
    }

    // Compressing end of Ranges for optimizing Fenwick Tree
    private static Map<Integer, Integer> compressedEndMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));

        // input n
        int n = Integer.parseInt(br.readLine());
        
        // Set for storing unique ends in sorted order for compression
        Set<Integer> uniqueEnds = new TreeSet<>();

        // For storing ranges with their index
        Range[] ranges = new Range[n];

        // Input ranges
        for (int i = 0; i < n; i++) {
            String[] points = br.readLine().split(" ");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            ranges[i] = new Range(start, end, i);
            uniqueEnds.add(end);
        }

        // compressing ends for optimizing fenwick tree
        int count = 0;
        for (int end : uniqueEnds) {
            compressedEndMap.put(end, ++count);
        }

        // Sort ranges by start, and by end descending if starts are equal
        Arrays.sort(ranges, Comparator.comparingInt((Range r) -> r.start).thenComparingInt(r -> -r.end));
        
        // Arrays for storing results
        int[] containedByCount = new int[n];
        int[] containsCount = new int[n];

        update(count, compressedEndMap.get(ranges[n-1].end), 1);

        for (int i = n-2; i >= 0; i--) {
            containedByCount[ranges[i].index] += query(compressedEndMap.get(ranges[i].end));
            update(count, compressedEndMap.get(ranges[i].end), 1);
        }

        // reset Fenwick Tree
        Arrays.fill(fenwickTree, 0);

        update(count, 1, 1);
        update(count, compressedEndMap.get(ranges[0].end) + 1, -1);

        for (int i = 1; i < n; i++) {
            containsCount[ranges[i].index] += query(compressedEndMap.get(ranges[i].end));
            update(count, 1, 1);
            update(count, compressedEndMap.get(ranges[i].end) + 1, -1);
        }

        // Print results
        StringBuffer result = new StringBuffer();
        for (int value : containedByCount) {
           result.append(value).append(" ");
        }
        result.append("\n");
        for (int value : containsCount) {
            result.append(value).append(" ");
        }
 
        pw.print(result.toString());
        
        pw.flush();
        pw.close();

        return;
    }
}
