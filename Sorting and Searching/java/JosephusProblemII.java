import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JosephusProblemII {

    public static void main(String[] args) throws IOException {

        // Reader
        Scanner sc = new Scanner(System.in);

        // read inputs
        String[] inputs = sc.nextLine().split("\\s+");
        int numberOfChildrens = Integer.parseInt(inputs[0]);
        int numbersToSkip = Integer.parseInt(inputs[1]);

        // close reader
        sc.close();

        // Writer
        BufferedOutputStream writer = new BufferedOutputStream(System.out);
        StringBuffer removalOrder = new StringBuffer();

        // Initialize TreeSet with n childrens
        TreeSet<Integer> childrens = new TreeSet<>();
        childrens.addAll(IntStream.rangeClosed(1, numberOfChildrens).boxed().collect(Collectors.toList()));
        
        // Iterator for iterating over childrens in ascending order
        Iterator<Integer> iterator = childrens.iterator();

        // for keeping count of current child
        int currentChild = 0;

        // calculate first child to be removed
        int currentSkip = numbersToSkip % childrens.size();

        // remove every other child from the circle until list is empty
        while (!childrens.isEmpty()) {
            
            // Reset iterator if it is reached to the end
            iterator = iterator.hasNext() ? iterator : childrens.iterator();

            if (currentChild == currentSkip) {
                
                // print and remove currentChild
                removalOrder.append(iterator.next()).append(" ");
                iterator.remove();

                // reset counter for current child
                currentChild = 0;

                // calculate next child to be removed
                currentSkip = childrens.isEmpty() ? 0 : numbersToSkip % childrens.size();

            } else {

                // skip currentChild
                iterator.next();

                // update currentChild count
                currentChild++;

            }
            
        }

        // print removal order
        writer.write(removalOrder.toString().getBytes());
        writer.flush();

        return;

    }

}
