import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
 
public class NestedRangesCheck {
    
    static class Range {
        int start;
        int end;
        int index;
 
        Range(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        // Reader
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
 
        // Get total customers
        int totalLines = Integer.parseInt(read.readLine());
 
        // Array for storing ranges
        Range[] ranges = new Range[totalLines];
 
        for (int i = 0; i < totalLines; i++) {
            // Get Start and End point of ranges
            String[] points = read.readLine().split(" ");
            ranges[i] = new Range(Integer.parseInt(points[0]), Integer.parseInt(points[1]), i);
        }
 
        // Sort ranges by start, and by end descending if starts are equal
        Arrays.sort(ranges, Comparator.comparingInt((Range r) -> r.start).thenComparingInt(r -> -r.end));
    
        // Arrays for storing results
        int[] containsResult = new int[totalLines];
        int[] containedByResult = new int[totalLines];
 
        // Sweep line for "contains"
        int maxEnd = Integer.MIN_VALUE;
        for (int i = 0; i < totalLines; i++) {
            if (ranges[i].end <= maxEnd) {
                containsResult[ranges[i].index] = 1;
            }
            maxEnd = Math.max(maxEnd, ranges[i].end);
        }
 
        // Sweep line for "containedBy"
        int minEnd = Integer.MAX_VALUE;
        for (int i = totalLines - 1; i >= 0; i--) {
            if (ranges[i].end >= minEnd) {
                containedByResult[ranges[i].index] = 1;
            }
            minEnd = Math.min(minEnd, ranges[i].end);
        }
 
        // Print results
        StringBuffer result = new StringBuffer();
        for (int value : containedByResult) {
           result.append(value).append(" ");
        }
        result.append("\n");
        for (int value : containsResult) {
            result.append(value).append(" ");
        }
 
        System.out.print(result.toString());
    }
}
