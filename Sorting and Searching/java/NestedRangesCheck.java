import java.io.*;
import java.util.*;

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
        FastIO io = new FastIO();
 
        // Get total customers
        int totalLines = io.nextInt();
 
        // Array for storing ranges
        Range[] ranges = new Range[totalLines];
 
        for (int i = 0; i < totalLines; i++) {
            // Get Start and End point of ranges
            ranges[i] = new Range(io.nextInt(), io.nextInt(), i);
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
