import java.util.Scanner;
import java.util.TreeMap;

public class Towers {

    public static void main(String[] args) {

        // Reader
        Scanner sc = new Scanner(System.in);
 
        // towers with frequency
        TreeMap<Integer, Integer> towerMap = new TreeMap<>();
 
        // skip reading n cubes value
        int n = sc.nextInt();
        sc.nextLine();
 
        // read all cubes
        String[] cubesArray = sc.nextLine().split("\\s+");
 
        Integer cube;
            
        for (int i = 0; i < n; i++) {

            // pick next cube
            cube = Integer.parseInt(cubesArray[i]);
 
            // find the tower that is greater than or equal to the current block
            Integer leastHigherTower = towerMap.higherKey(cube);
 
            // debug
            // System.out.println("cube: " + cube + " placed on: " + leastHigherTower);
            
            if (leastHigherTower != null) {

                towerMap.put(leastHigherTower, towerMap.getOrDefault(leastHigherTower, 0) - 1);
                
                if (towerMap.get(leastHigherTower) == 0) {
                    towerMap.remove(leastHigherTower);
                }

            }

            towerMap.put(cube, towerMap.getOrDefault(cube, 0) + 1);

        }

        // calculate towers value by iterating over frequency
        Integer totalTowers = 0;
        for (Integer value : towerMap.values()) {
            totalTowers += value;
        }

        // Output number of towers
        System.out.println(totalTowers);

        // close reader
        sc.close();
        
        return;

    }

}
