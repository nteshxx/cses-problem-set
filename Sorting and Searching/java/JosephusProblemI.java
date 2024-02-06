import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JosephusProblemI {

    public static void main(String[] args) throws IOException {

        // Reader
        Scanner sc = new Scanner(System.in);

        // get number of childrens
        int numberOfChildrens = sc.nextInt();

        // close reader
        sc.close();

        // Writer
        BufferedOutputStream writer = new BufferedOutputStream(System.out);
        StringBuffer removalOrder = new StringBuffer();

        // TreeSet for storing n childrens
        TreeSet<Integer> childrens = new TreeSet<>();
        childrens.addAll(IntStream.rangeClosed(1, numberOfChildrens).boxed().collect(Collectors.toList()));
        Iterator<Integer> iterator = childrens.iterator();

        // flag for deciding other child
        boolean otherChild = false;
        
       // remove every other child from the circle until list is empty
        while (!childrens.isEmpty()) {
            // Reset iterator if it is reached to the end
            iterator = iterator.hasNext() ? iterator : childrens.iterator();
            
            if (otherChild) {
                removalOrder.append(iterator.next()).append(" ");
                iterator.remove();
            } else {
                iterator.next();
            }

            // changing flag for other child
            otherChild = !otherChild;

        }

        // print removal order
        writer.write(removalOrder.toString().getBytes());
        writer.flush();

        return;

    }

}
