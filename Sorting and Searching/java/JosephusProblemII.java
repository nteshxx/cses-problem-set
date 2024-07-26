import java.util.*;
import java.io.*;
import java.util.stream.*;

public class JosephusProblemII {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputs = br.readLine().trim();
        int n = Integer.parseInt(inputs.split(" ")[0]);
        int k = Integer.parseInt(inputs.split(" ")[1]);

        TreeSet<Integer> s = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toCollection(TreeSet::new));
        
        int ind = k % n;

        while (n-- > 0) {
            int y = findByOrder(s, ind);
            bw.write(y + " ");
            s.remove(y);
            if (n > 0) {
                ind = (ind % n + k) % n;
            }
        }
        
        bw.flush();
        bw.close();

        br.close();

        return;
    }

    private static int findByOrder(TreeSet<Integer> set, int index) {
        Iterator<Integer> it = set.iterator();
        for (int i = 0; i < index; i++) {
            it.next();
        }
        return it.next();
    }
}
