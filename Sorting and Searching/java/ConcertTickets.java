import java.io.*;
import java.util.*;

public class ConcertTickets {

	public static void main(String[] args) throws IOException {
		Reader io = new Reader();
		PrintWriter pw = new PrintWriter(System.out);

		int ticketNum = io.nextInt();
		int peopleNum = io.nextInt();
		
        // No multiset in java so we'll have to use a TreeMap
		NavigableMap<Integer, Integer> ticketMultiset = new TreeMap<>();
		Map.Entry<Integer, Integer> val;

        // Get and store the tickets in ticketMultiset
		for (int i = 0; i < ticketNum; i++) {
			int priceOfTicket = io.nextInt();

			if (ticketMultiset.containsKey(priceOfTicket)) {
				ticketMultiset.put(priceOfTicket, ticketMultiset.get(priceOfTicket) + 1);
			} else {
				ticketMultiset.put(priceOfTicket, 1);
			}
		}

        //pw.println("Tickets: " + ticketMultiset.toString());

		for (int i = 0; i < peopleNum; i++) {
			int customerMaxPrice = io.nextInt();
			// Returns a key-value mapping associated with the greatest key strictly less than the given key, or null
			val = ticketMultiset.lowerEntry(customerMaxPrice + 1);

			if (val != null) {
				pw.println(val.getKey());

				if (val.getValue() == 1) {
					ticketMultiset.remove(val.getKey());
				} else {
					ticketMultiset.put(val.getKey(), val.getValue() - 1);
				}
			} else {
				pw.println(-1);
			}
		}

		io.close();
		pw.close();

        return;
	}

    public static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
