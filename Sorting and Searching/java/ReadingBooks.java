import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class ReadingBooks {
    
    public static void main(String[] args) {
        
        // Reader
        FastIO io = new FastIO();

        int numberOfBooks = io.nextInt();

        int[] readTimes = new int[numberOfBooks];
        // input book reading times
        for (int i = 0; i < numberOfBooks; i++) {
            readTimes[i] = io.nextInt();
        }

        // Sorting reading times in ascending order
        Arrays.sort(readTimes);

        long totalTimeRequired = 0;
        
        for (int i = 0; i < readTimes.length - 1; i++) {
            totalTimeRequired += readTimes[i];
        }

        long totalTimeRequiredN = readTimes[numberOfBooks-1];
        totalTimeRequired = totalTimeRequiredN >= totalTimeRequired 
            ? totalTimeRequiredN * 2 
            : totalTimeRequired + totalTimeRequiredN;


        // print the solution
        io.println(totalTimeRequired);
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
