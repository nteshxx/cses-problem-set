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
        
        FastIO io = new FastIO();

        // input n
        int n = io.nextInt();
        
        // Set for storing unique ends in sorted order for compression
        Set<Integer> uniqueEnds = new TreeSet<>();

        // For storing ranges with their index
        Range[] ranges = new Range[n];

        // Input ranges
        for (int i = 0; i < n; i++) {
            int start = io.nextInt();
            int end = io.nextInt();
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

        for (int i = n-1; i >= 0; i--) {
            containedByCount[ranges[i].index] += query(compressedEndMap.get(ranges[i].end));
            update(count, compressedEndMap.get(ranges[i].end), 1);
        }

        // reset Fenwick Tree
        Arrays.fill(fenwickTree, 0);

        for (int i = 0; i < n; i++) {
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
 
        io.print(result.toString());
        io.close();

        return;
    }

    public static class FastIO extends PrintWriter {
		private InputStream stream;
		private byte[] buf = new byte[1 << 16];
		private int curChar, numChars;

		// standard input
		public FastIO() { this(System.in, System.out); }
		public FastIO(InputStream i, OutputStream o) {
			super(o);
			stream = i;
		}
		// file input
		public FastIO(String i, String o) throws IOException {
			super(new FileWriter(o));
			stream = new FileInputStream(i);
		}

		// throws InputMismatchException() if previously detected end of file
		private int nextByte() {
			if (numChars == -1) throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) { throw new InputMismatchException(); }
				if (numChars == -1) return -1;  // end of file
			}
			return buf[curChar++];
		}

		// to read in entire lines, replace c <= ' '
		// with a function that checks whether c is a line break
		public String next() {
			int c;
			do { c = nextByte(); } while (c <= ' ');
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = nextByte();
			} while (c > ' ');
			return res.toString();
		}
		public int nextInt() {  // nextLong() would be implemented similarly
			int c;
			do { c = nextByte(); } while (c <= ' ');
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = nextByte();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') throw new InputMismatchException();
				res = 10 * res + c - '0';
				c = nextByte();
			} while (c > ' ');
			return res * sgn;
		}
		
        public double nextDouble() { return Double.parseDouble(next()); }
	}
}
