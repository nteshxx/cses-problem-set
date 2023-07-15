import java.util.Scanner;

public class WeirdAlgorithm {

    public static void main(String[] args) {    
        // Initializing scanner for taking inputs
        Scanner sc = new Scanner(System.in);
        // integer n
        long n = sc.nextLong();
        sc.close();

        // print n
        System.out.print(n);

        // weird algorithm
        while (n != 1) {
            n = (n % 2 == 0) ? n / 2 : (n * 3) + 1;
            System.out.print(" " + n);
        }
    }
}
