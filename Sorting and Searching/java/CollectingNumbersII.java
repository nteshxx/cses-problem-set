import java.io.*;

public class CollectingNumbersII {

    public static void main(String[] args) throws IOException {
        // reader
        Reader read = new Reader();

        // writer
        OutputStream out = new BufferedOutputStream(System.out);

        // read inputs
        int n = read.nextInt();
        int m = read.nextInt();
        String[] numbers = ("0 " + read.nextLine()).split("\\s+");

        // index array
        int[] indexArray = new int[n + 2];
        for (int i = 0; i <= n; i++) {
            indexArray[Integer.parseInt(numbers[i])] = i;
        }
        indexArray[n+1] = n+1;

        // solve using index array
        int rounds = 1;
        for (int i = 1; i < n; i++) {
            if (indexArray[i] > indexArray[i + 1]) {
                rounds++;
            }
        }

        // numbers to swap
        int a, b = 0;

        while (m > 0) {
            // accept the numbers
            a = read.nextInt();
            b = read.nextInt();

            int r = Integer.parseInt(numbers[a]);
            int s = Integer.parseInt(numbers[b]);

            String temp = numbers[a];
            numbers[a] = numbers[b];
            numbers[b] = temp;

            if (indexArray[r - 1] <= indexArray[r] && indexArray[r - 1] > b)
                rounds++;
            if (indexArray[r - 1] > indexArray[r] && indexArray[r - 1] <= b)
                rounds--;
            if (indexArray[r] <= indexArray[r + 1] && b > indexArray[r + 1])
                rounds++;
            if (indexArray[r] > indexArray[r + 1] && b <= indexArray[r + 1])
                rounds--;

            indexArray[r] = b;

            if (indexArray[s - 1] <= indexArray[s] && indexArray[s - 1] > a)
                rounds++;
            if (indexArray[s - 1] > indexArray[s] && indexArray[s - 1] <= a)
                rounds--;
            if (indexArray[s] <= indexArray[s + 1] && a > indexArray[s + 1])
                rounds++;
            if (indexArray[s] > indexArray[s + 1] && a <= indexArray[s + 1])
                rounds--;

            indexArray[s] = a;

            // print the solution
            out.write((rounds + "\n").getBytes());
            m--;
        }

        out.flush();
        read.close();

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
        
        public String nextLine() throws IOException {
            byte[] buf = new byte[1024]; // Increase the buffer size as needed
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
                if (cnt == buf.length) {
                    // If the buffer is full, double its size
                    byte[] newBuf = new byte[buf.length * 2];
                    System.arraycopy(buf, 0, newBuf, 0, buf.length);
                    buf = newBuf;
                }
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
