import java.io.*;
import java.util.*;

public class NearestSmallerValues {
    
    public static void main(String[] args) throws IOException {

        // reader
        Scanner sc = new Scanner(System.in);
        
        // writer
        BufferedOutputStream writer = new BufferedOutputStream(System.out);
        StringBuilder result = new StringBuilder();

        // inputs
        int n = sc.nextInt();
        sc.nextLine();

        String[] integers = sc.nextLine().split("\\s+");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(integers[i]);
        }
        
        // solve
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            
            while ((!stack.empty()) && (arr[i] <= arr[stack.peek() - 1])) {
                stack.pop();
            }
                
            int position = stack.isEmpty() ? 0 : stack.peek();
            result.append(position + " ");
            stack.push(i+1);
        }

        writer.write(result.toString().trim().getBytes());
        writer.flush();

        // close reader
        sc.close();
        
        return;

    }

}
