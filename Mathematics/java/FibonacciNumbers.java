import java.util.Scanner;

public class FibonacciNumbers {
    private static final long modValue = 1000000007;

    private static long[] matrixMultiply(long[] a, long[] b) {
        long[] result = new long[4];
        result[0] = (a[0] * b[0] + a[1] * b[2]) % modValue;
        result[1] = (a[0] * b[1] + a[1] * b[3]) % modValue;
        result[2] = (a[2] * b[0] + a[3] * b[2]) % modValue;
        result[3] = (a[2] * b[1] + a[3] * b[3]) % modValue;
        return result;
    }

    private static long[] matrixPower(long[] matrix, long exponent) {
        if (exponent == 1) {
            return matrix;
        }
        if (exponent % 2 == 0) {
            long[] halfPower = matrixPower(matrix, exponent / 2);
            return matrixMultiply(halfPower, halfPower);
        } else {
            long[] halfPower = matrixPower(matrix, (exponent - 1) / 2);
            long[] temp = matrixMultiply(halfPower, halfPower);
            return matrixMultiply(temp, matrix);
        }
    }

    public static long calculateNthFibonacciNumber(long n) {
        // base case
        if (n == 0 || n == 1) {
            return n;
        }

        long[] baseMatrix = {1, 1, 1, 0};
        long[] poweredMatrix = matrixPower(baseMatrix, n - 1);
        
        return poweredMatrix[0];
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        long n = read.nextLong();
        long result = calculateNthFibonacciNumber(n);

        System.out.println(result);

        read.close();
    }
}
