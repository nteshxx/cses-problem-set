import java.io.*;
import java.util.*;

public class SumofThreeValues {
    
    public static void main(String[] args) {
        
        // reader
        FastIO io = new FastIO();

        int n = io.nextInt();
        int targetSum = io.nextInt();
        int[] numbers = new int[n];

        // Storing number Indexes
        Map<Integer, Integer> numberMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            numbers[i] = io.nextInt();
			numberMap.put(numbers[i], i+1);
        }

		String answer = "";
		for (int i = 0; i < numbers.length; i++) {
			for (int j =  i+1; j < numbers.length; j++) {
				if (numberMap.containsKey(targetSum - numbers[i] - numbers[j])
					&& (numberMap.get(targetSum - numbers[i] - numbers[j]) != (i+1))
					&& (numberMap.get(targetSum - numbers[i] - numbers[j]) != (j+1))) {
					// update answer
					answer = (i+1) + " " + (j+1) + " " + numberMap.get(targetSum - numbers[i] - numbers[j]);
					break;
				}
			}
			if (!answer.equals("")) {
				break;
			}
		}

		// print the solution
		answer = answer.equals("") ? "IMPOSSIBLE" : answer;
		io.print(answer);

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
		
        public double nextDouble() { 
			return Double.parseDouble(next());
		}

		// Read a complete line
		public String nextLine() {
			int c = nextByte();
			while (c == '\n' || c == '\r') {
				c = nextByte(); // skip the end of line characters
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = nextByte();
			} while (c != '\n' && c != '\r' && c != -1);
			return res.toString();
		}
	}

}
