import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Day3Part2 {

    static HashMap<Integer, Integer> hashMap = new HashMap(52);
    static int lowerOffset = 'a' -1;
    static int upperOffset = 'A' -1;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new FileReader("inputs/day3.txt"));

        for (int i = 1; i <= 52; i++){
            hashMap.put(i, 0);
        }


        String line;
        int accumulatedScore = 0;
        int counter = 0;
        int groupcounter = 0;
        int currentBadge;
        int currentScore;
        int currentOccurance;
        int groupMax;
        while (file.hasNextLine()){
            groupcounter++;
            groupMax = 0 + (groupcounter >= 2 ? 1 : 0) + (groupcounter == 3 ? 1 : 0);
            line = file.nextLine();
            while (counter < line.length()){
                currentScore = getScore(line.charAt(counter));
                currentOccurance = hashMap.get(currentScore);
                if (currentOccurance == groupMax) hashMap.replace(currentScore, currentOccurance + 1);
                counter++;
            }
            if (groupcounter == 3){
                groupcounter = 0;
                 currentBadge = hashMap.entrySet().stream().toList().stream().max(new Comparator<Map.Entry<Integer, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                }).get().getKey();
                 accumulatedScore += currentBadge;
                for (int i = 1; i <= 52; i++){
                    hashMap.replace(i, 0);
                }

            }

            counter = 0;
        }

        System.out.println(accumulatedScore);

    }

    public static int getScore(char input){
        int output = input < lowerOffset ? input - upperOffset + 26 : input - lowerOffset;
        return output;
    }

}