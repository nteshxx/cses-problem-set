import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class RestaurantCustomers {

    public static void main(String[] args) throws IOException {
		// Reader
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		// Map for string arrival and leaving times in sorted order
        TreeMap<Integer, Integer> timesMap = new TreeMap<>();
		
        // Get total customers
        int totalCustomers = Integer.parseInt(read.readLine());

		for (int i = 0; i < totalCustomers; i++) {
			// Get Arrival and Leaving time strings
            StringTokenizer customer = new StringTokenizer(read.readLine());
            // Arrival time
			timesMap.put(Integer.parseInt(customer.nextToken()), 1);
			// Leaving time
            timesMap.put(Integer.parseInt(customer.nextToken()), -1);
		}
        // System.out.println("TimesMap: " + timesMap.toString());

		int maxCustomers = 0;
		int currentCustomers = 0;
		for (int time : timesMap.values()) {
			currentCustomers += time;
			maxCustomers = Math.max(maxCustomers, currentCustomers);
		}

        // print the solution
		System.out.println(maxCustomers);
        
        return;
	}

}
