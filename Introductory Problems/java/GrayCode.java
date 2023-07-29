import java.io.*;
import java.util.*;

public class GrayCode {

    private static String[] generateGrayCodeStrings(int n) {
        // base case
        if (n == 1) {
            String[] bitStrings1 = new String[2];
            bitStrings1[0] = "0";
            bitStrings1[1] = "1";
            return bitStrings1;
        }

        // calculate for n-1
        String[] bitStringsNMinus1 = generateGrayCodeStrings(n - 1);
        
        // bitStrings for n
        String[] bitStringsN = new String[((int) Math.pow(2, n))];
        
        // Using the reflect binary code property of gray code
        // append image of n-1
        for (int i = 0; i < bitStringsNMinus1.length; i++) {
            bitStringsN[i] = "0" + bitStringsNMinus1[i];
        }
        
        // append reflected image of n-1
        for (int i = bitStringsNMinus1.length - 1; i >= 0; i--) {
            bitStringsN[(bitStringsN.length - 1) - i] = "1" + bitStringsNMinus1[i];
        }

        // result
        return bitStringsN;

    }
    
    public static void main(String[] args) throws IOException {
        // reader
        Scanner read = new Scanner(System.in);

        int n = read.nextInt();
        read.close();

        // solve
        String[] bitStrings = generateGrayCodeStrings(n);

        // writer
        OutputStream out = new BufferedOutputStream(System.out);

        // print the solutions
        for (String bitString : bitStrings) {
            out.write((bitString + "\n").getBytes());
        }
        out.flush();

        return;
    }

}
