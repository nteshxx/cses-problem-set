import java.util.*;

public class PalindromeReorder {

    private static void reorderPalindrome(String str) {
        Map<String, Integer> elementsMap = new HashMap<String, Integer>(27);
        int charsWithOddCount = 0;
        StringBuilder sb = new StringBuilder("");
        String mid = "";
        
        // iterate and count all chars
        for (char c : str.toCharArray()) {
            elementsMap.merge(String.valueOf(c), 1, Integer::sum);
        }

        // count the odd elements
        for (int elementCount : elementsMap.values()) {
            if (elementCount % 2 == 1) {
                charsWithOddCount++;
            }
        }

        if ((str.length() % 2 == 0 && charsWithOddCount == 0) || (str.length() % 2 != 0 && charsWithOddCount == 1)) {
            // build half string
            for (Map.Entry<String, Integer> e : elementsMap.entrySet()) {
                if (e.getValue() % 2 == 0) {
                    sb.append(e.getKey().repeat(e.getValue() / 2));
                } else {
                    mid = e.getKey().repeat(e.getValue());
                }
            }

            // print the solution
            System.out.print(sb.toString() + mid + sb.reverse().toString());

        } else {
            System.out.print("NO SOLUTION");
        }

        return;
    };
    
    public static void main(String[] args) {
        // reader
        Scanner read = new Scanner(System.in);
        String str = read.next();
        read.close();

        // solve
        reorderPalindrome(str);

        return;        
    }
}
