import java.util.Scanner;

public class WeirdAlgorithm {

    private static void weirdAlgorithm(long n) {
        // print n
        System.out.print(n);
 
        // weird algorithm
        while (n != 1) {
            n = (n % 2 == 0) ? n / 2 : (n * 3) + 1;
            System.out.print(" " + n);
        }

        return;
    }

    public static void main(String[] args) {
        // scanner
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        sc.close();

        // solve
        weirdAlgorithm(n);

        return;
    }
}
