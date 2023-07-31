import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppleDivision {

    private static int n;
    private static Long minDiff = Long.MAX_VALUE;
    private static List<Long> wts = new ArrayList<Long>();

    private static Long minDifference(int index, Long crate1, Long crate2) {
        // base case
        if (index == n) {
            return Math.abs(crate1 - crate2);
        }

        // faith
        return Math.min(
            minDifference(index + 1, crate1 + wts.get(index), crate2),
            minDifference(index + 1, crate1, crate2 + wts.get(index))
        );
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for(int i=0; i < n; i++) {
            wts.add(sc.nextLong());
        }
        sc.close();

        // using recursion
        minDiff = minDifference(0, 0L, 0L);

        // print the result
        System.out.println(minDiff);

        return;
    }

}
