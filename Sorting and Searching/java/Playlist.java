import java.util.*;

public class Playlist {
    
    public static void main(String[] args) {
        // reader
        Scanner read = new Scanner(System.in);
        
        // read inputs
        int n = read.nextInt();
        read.nextLine();
        String[] songId = read.nextLine().split("\\s+");
        read.close();
        
        // for storing unique songIds with index
        HashMap<String, Integer> uniqueSongs = new HashMap<>();

        // for storing longest sequence of unique songs
        int longestSequence = 0;
        int startIndex = 0;

        // solve
        for (int i = 0; i < n; i++) {   
            // duplicate is found
            if (uniqueSongs.containsKey(songId[i])) {
                // adjust startIndex
                startIndex = Math.max(startIndex, uniqueSongs.get(songId[i]));
            }

            // calculate longestSequence
            longestSequence = Math.max(longestSequence, (i + 1 - startIndex));

            // add or update the songId in uniqueSongs
            uniqueSongs.put(songId[i], i + 1);
        }

        // print the solution
        System.out.print(longestSequence);

        return;
    }

}
