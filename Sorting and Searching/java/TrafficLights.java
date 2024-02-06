import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class TrafficLights {
    
    public static void main(String[] args) throws IOException {

        // reader
        Scanner sc = new Scanner(System.in);

        // inputs
        int streetLength = sc.nextInt();
        int numberOfTrafficLights = sc.nextInt();
        sc.nextLine();

        String[] positionOfTrafficLights = sc.nextLine().split("\\s+");

        // writer
        BufferedOutputStream writer = new BufferedOutputStream(System.out);

        // solve
        NavigableSet<Integer> street = new TreeSet<>(List.of(0, streetLength));
        TreeMap<Integer, Integer> passages = new TreeMap<>();

        for (int i = 0; i < numberOfTrafficLights; i++) {
            Integer trafficLightPosition = Integer.parseInt(positionOfTrafficLights[i]);

            Integer nextLightPositon = street.higher(trafficLightPosition);
            Integer previousLightPosition = nextLightPositon != null ? street.lower(nextLightPositon) : null;

            if (previousLightPosition != null && nextLightPositon != null) {
                Integer passageToBeDivided = nextLightPositon - previousLightPosition;
                Integer leftDivision = trafficLightPosition - previousLightPosition;
                Integer rightDivision = nextLightPositon - trafficLightPosition;

                passages.put(passageToBeDivided, passages.getOrDefault(passageToBeDivided, 1) - 1);
                if (passages.getOrDefault(passageToBeDivided, 0) == 0) {
                    passages.remove(passageToBeDivided);
                }

                passages.put(leftDivision, passages.getOrDefault(leftDivision, 0) + 1);
                passages.put(rightDivision, passages.getOrDefault(rightDivision, 0) + 1);
            }

            street.add(trafficLightPosition);

            writer.write((passages.lastKey() + " ").getBytes());

        }

        writer.flush();

        // close reader
        sc.close();
        
        return;

    }

}
