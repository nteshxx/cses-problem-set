import java.io.*;
import java.util.*;

public class FerrisWheel {

    private static void allocateGondolas(int childrens, long maxWeight, Integer[] weights) {
        Arrays.sort(weights);

        int count = 0;
        int i = 0, j = childrens - 1;

        while (i <= j) {
            // calculate the total weight of two childrens
            long totalWeight = weights[i] + weights[j];
            
            if (totalWeight <= maxWeight) {
                // acceptable weight for single gondola
                count++;
                i++;
                j--;
            } else {
                // allocate last child to single gondola
                count++;
                j--;
            }
        }

        // print the solution
        System.out.println(count);

        return;
    }
    
    public static void main(String[] args) throws IOException {
        // reader
        Scanner read = new Scanner(System.in);
        
        int childrens = read.nextInt();
        long maxWeight = read.nextInt();
        read.nextLine();
 
        // Reading weights of children (this optimization is necessary to pass all test cases)
		String[] weightsStr = read.nextLine().split(" ");
		
        // converting the weights to Long (part of optimization)
        Integer[] weights = new Integer[childrens];
        for (int i = 0; i < childrens; i++) {
			weights[i] = Integer.parseInt(weightsStr[i]);
		}
 
        // solve
        allocateGondolas(childrens, maxWeight, weights);

        // close stream
        read.close();
 
        return;
    }
}
