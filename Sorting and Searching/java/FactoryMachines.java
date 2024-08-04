import java.io.*;
import java.util.*;

public class FactoryMachines {

    public static void main(String[] args) throws IOException {

		// Reader
        FastIO io = new FastIO();
		
        int machineCount = io.nextInt();
        int target = io.nextInt();

        int[] machineTimes = new int[machineCount];

		for (int i = 0; i < machineCount; i++) {
			machineTimes[i] = io.nextInt();
		}

		// Binary Search on time to make proucts equal or greater than target
		long low = 0;
		long high = (long) 1e18;
		long mid = 0;

		long minimumTime = 0;

		while (low <= high) {
			mid = (low + high) / 2;

			// calulate number of product manufactured in minmum time at midtime
			long productsManufactured = 0;
			for (int i = 0; i < machineTimes.length; i++) {
				productsManufactured += (mid / machineTimes[i]);

				if (productsManufactured >= target) {
					break;
				}
			}

			// update minimumTime and low and high to look for next optimal minimumTime
			if (productsManufactured >= target) {
				minimumTime = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		io.print(minimumTime);
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
