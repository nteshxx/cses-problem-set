import java.io.*;
import java.util.*;

public class TasksAndDeadlines {

	private static class Task implements Comparable<Task> {
		int timeRequired, deadline;

		public Task(int a, int d) {
			this.timeRequired = a;
			this.deadline = d;
		}

		@Override
		public int compareTo(Task other) {
			return Integer.compare(timeRequired, other.timeRequired);
		}
	}
    
    public static void main(String[] args) {
        
        // Reader and Writer
        FastIO io = new FastIO();

        int numberOfTasks = io.nextInt();
        Task[] tasks = new Task[numberOfTasks];

        for (int i = 0; i < numberOfTasks; i++) {
            int a = io.nextInt();
            int d = io.nextInt();
            tasks[i] = new Task(a, d);
        }

		// Sorting Task based on their execution time
		Arrays.sort(tasks);

		long maxReward = 0;
		long timeOfExecution = 0;
		for (Task task : tasks) {
			// calculate time of execution
			timeOfExecution += task.timeRequired;
			maxReward += (task.deadline - timeOfExecution);
		}
        
		// print the max reward
		io.println(maxReward);
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
