import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Day3Part1 {

    static HashMap<Integer, Integer> hashMap = new HashMap(52);
    static int lowerOffset = 'a' -1;
    static int upperOffset = 'A' -1;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new FileReader("inputs/day3.txt"));

        for (int i = 1; i <= 52; i++){
            hashMap.put(i, -1);
        }


        String line;
        int accumulatedScore = 0;
        int counter = 0;
        while (file.hasNextLine()){
            line = file.nextLine();
            while (counter < line.length()/2){
                hashMap.replace(getScore(line.charAt(counter)), accumulatedScore);
                counter++;
            }
            while (hashMap.get(getScore(line.charAt(counter))) != accumulatedScore){
                counter++;
            }
            accumulatedScore += getScore(line.charAt(counter));
            counter = 0;
        }

        System.out.println(accumulatedScore);

    }

    public static int getScore(char input){
        int output = input < lowerOffset ? input - upperOffset + 26 : input - lowerOffset;
        return output;
    }

}