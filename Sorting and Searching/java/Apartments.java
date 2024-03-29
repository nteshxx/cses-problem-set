import java.io.*;
import java.util.*;

public class Apartments {

    private static void allocateApartments(int diff, int[] requiredSizes, int[] availableSizes) {
        Arrays.sort(requiredSizes);
        Arrays.sort(availableSizes);

        int count = 0;
        int i = 0, j = 0;

        while (i < requiredSizes.length && j < availableSizes.length) {
            // calculate the difference
            int sizeDifference = requiredSizes[i] - availableSizes[j];
            
            if (Math.abs(sizeDifference) <= diff) {
                // acceptable difference
                count++;
                i++;
                j++;
            } else if (sizeDifference > diff) {
                // positive difference
                j++;
            } else {
                // negative difference
                i++;
            }
        }

        // print the solution
        System.out.println(count);

        return;
    }
    
    public static void main(String[] args) throws IOException {
        // reader
        Reader read = new Reader();
        
        int applicants = read.nextInt();
        int apartments = read.nextInt();
        int diff = read.nextInt();
 
        int[] requiredSizes = new int[applicants];
        for (int i = 0; i < applicants; i++) {
            requiredSizes[i] = read.nextInt();
        }
        
        int[] availableSizes = new int[apartments];
        for (int i = 0; i < apartments; i++) {
            availableSizes[i] = read.nextInt();
        }
 
        // solve
        allocateApartments(diff, requiredSizes, availableSizes);
 
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
