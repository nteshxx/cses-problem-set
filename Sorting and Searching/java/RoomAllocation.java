import java.io.*;
import java.util.*;

public class RoomAllocation {

    private static class Customer {
        private int arrivalDay, departureDay, index;

        public Customer(int arrivalDay, int departureDay, int index) {
            this.arrivalDay = arrivalDay;
            this.departureDay = departureDay;
            this.index = index;
        }
    }

    private static class Room {
        private int departureDay, number;

        public Room(int departureDay, int number) {
            this.departureDay = departureDay;
            this.number = number;
        }
    }

    public static void main(String[] args) throws IOException {

		// Reader
        FastIO io = new FastIO();
		
        // Get total customers
        int totalCustomers = io.nextInt();
        Customer[] customers = new Customer[totalCustomers];

        // Input Customers
        for (int i = 0; i < totalCustomers; i++) {
            int arrival = io.nextInt();
            int departure = io.nextInt();
            customers[i] = new Customer(arrival, departure, i);
        }

        // Sorting customers based on ArrivalDay
        Arrays.sort(customers, Comparator.comparingInt(c -> c.arrivalDay));

        // Sorted map to store departure days
        PriorityQueue<Room> pq = new PriorityQueue<>(Comparator.comparingInt(r -> r.departureDay));

        int minimumRoomsRequired = 0;
        // the room numbers allocated to each customer
		int[] roomAllocationArray = new int[totalCustomers];
        int lastRoomAllocated = 1;

        // add the first customer to the priority queue
		pq.add(new Room(customers[0].departureDay, lastRoomAllocated));
		roomAllocationArray[customers[0].index] = lastRoomAllocated;
        
		for (int i = 1; i < totalCustomers; i++) {

            // find the minimum departure time
			Room min = pq.peek();

            if (min.departureDay < customers[i].arrivalDay) {
                pq.remove();
                pq.add(new Room(customers[i].departureDay, min.number));
                roomAllocationArray[customers[i].index] = min.number;
            } else {
                lastRoomAllocated++;
                pq.add(new Room(customers[i].departureDay, lastRoomAllocated));
                roomAllocationArray[customers[i].index] = lastRoomAllocated;
            }

            // storing max size of departureQueue
            minimumRoomsRequired = Math.max(minimumRoomsRequired, pq.size());
		}

        // print the solution
        StringBuffer roomAllocation = new StringBuffer();
		for (int allocation : roomAllocationArray) {
			roomAllocation.append(allocation).append(" ");
		}

		io.print(minimumRoomsRequired + "\n" + roomAllocation.toString());
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
