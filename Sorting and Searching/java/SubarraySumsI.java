import java.io.*;
import java.util.*;

public class SubarraySumsI {

    public static int subArraySumEqualsK(int target, int[] arr) {
        int ans = 0;

        // solve
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(0L, 1L);

        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // prefix sum
            if (hashMap.containsKey(sum - target)) { // if subarray exists
                ans += hashMap.get(sum - target); // increment ans by frequency
            }
            hashMap.put(sum, hashMap.getOrDefault(sum, 0L) + 1); // update frequency in map for current subarry sum
        }

        return ans;
    }
    
    public static void main(String[] args) throws IOException {

        // reader
        Scanner sc = new Scanner(System.in);
        
        // writer
        BufferedOutputStream writer = new BufferedOutputStream(System.out);

        // inputs
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();

        String[] integers = sc.nextLine().split("\\s+");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(integers[i]);
        }
        
        // solve
        int result = subArraySumEqualsK(k, arr);

        writer.write((result + "").getBytes());
        writer.flush();

        // close reader
        sc.close();
        
        return;

    }

}
