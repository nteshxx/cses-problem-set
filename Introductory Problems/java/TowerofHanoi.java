import java.util.Scanner;

public class TowerofHanoi {

    private static void towerOfHanoi(int n, char source, char middle, char destination) {
        // base case
        if (n == 1) {
            System.out.println(source + " " + destination);
            return;
        }

        towerOfHanoi(n - 1, source, destination, middle);
        System.out.println(source + " " + destination);
        towerOfHanoi(n - 1, middle, source, destination);
        
        return;
    }

    public static void main(String[] args) {  
        // scanner
        Scanner sc = new Scanner(System.in);
        
        // no. of disks
        int n = sc.nextInt();
        // closing input stream
        sc.close();

        // tower of hanoi has 2^n - 1 steps
        System.out.println((1<<n)-1);
        
        // solve
        towerOfHanoi(n, '1', '2', '3');

        return;
    }    
}
