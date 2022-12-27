import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Day2Part2 {

    static int aOffset = Character.getNumericValue('A');
    static int xOffset = Character.getNumericValue('X');

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new FileReader("inputs/day2.txt"));


        String line;
        int accumulatedScore = 0;
        while (file.hasNextLine()){
            line = file.nextLine();
            accumulatedScore += getScore(line.charAt(0), line.charAt(2));
        }

        System.out.println(accumulatedScore);

    }

    public static int getScore(char opp, char outcome){
        int outcomeInt = Character.getNumericValue(outcome) - xOffset;
        int oppInt = Character.getNumericValue(opp) - aOffset;

        int yoursInt = outcomeInt == 1 ? oppInt : Math.floorMod(oppInt + (outcomeInt - 1),  3);


        return (Math.floorMod(yoursInt - oppInt,3) == 2 ? 0 : ((Math.floorMod(yoursInt - oppInt,3) + 1)*3)) + yoursInt + 1;
    }
}